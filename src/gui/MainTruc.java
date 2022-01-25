package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bdd.DatabaseManager;
import threads.ThreadManager;
import users.UserManager;

public class MainTruc {
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr;
	JPanel panel;
	JButton send, select, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] usersToChoose;
	JScrollPane scroll;
	JTextArea txt;

	String selectedUser = null;

	private void updateConversation() {
		ResultSet rs = (ResultSet) DatabaseManager.retrieveMessages(UserManager.myPseudo(), selectedUser);
		String result = "";
		try {
			while (rs.next()) {
				String sender = rs.getString("id1");

				// if message sent by us, put it on the right in blue
				if (sender.equals(UserManager.getMyID())) {
					result += (rs.getString("time") + "    								" + rs.getString("message")
							+ "\n");

					// put it on the left in red
				} else {
					result += (rs.getString("time") + "    " + rs.getString("message") + "\n");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txt.setText(result);
	}

	private void updateConnectedUsers() {
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox.setModel(new DefaultComboBoxModel(usersToChoose));
	}

	class Updater extends Thread {
		public void run() {
			while (true) {
				try {
					if (selectedUser != null) {
						updateConversation();
					}

					Thread.sleep(1000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public MainTruc() {
		interfaceFrame = new JFrame("Chat");
		msg = new JTextField(10);
		coUsr = new JLabel("Connected Users : ");
		send = new JButton("Send");
		select = new JButton("Select");
		logOut = new JButton("Log out");
		refresh = new JButton("Refresh");
		send.setEnabled(false);		

		txt = new JTextArea(200, 100); // lignes, colonnes

		interfaceFrame.setLayout(new BorderLayout());
		// interfaceFrame.setBounds(0, 0, 1000, 700);
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		interfaceFrame.getContentPane().setLayout(null);

		txt.setFont(new Font("Serif", Font.PLAIN, 25));
		txt.setText("Choose a user");

		interfaceFrame.setSize(600, 600);
		interfaceFrame.getContentPane().setLayout(null);

		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(usersToChoose);

		// Scroll part
		scroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(30, 70, 1200, 420);
		interfaceFrame.getContentPane().add(scroll);

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedUser = usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
				txt.setFont(new Font("Serif", Font.PLAIN, 15));
				txt.setCaretPosition(txt.getText().length() - 1);
				send.setEnabled(true);		
			}
		});

		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceFrame.setVisible(false);
				interfaceFrame.dispose();
			}
		});

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateConnectedUsers();
				txt.setCaretPosition(txt.getText().length() - 1);
			}
		});

		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = msg.getText();

				ThreadManager t = new ThreadManager();
				t.sendTo(selectedUser, message);
				msg.setText("");
			}
		});

		// Creates the panel
		panel = new JPanel();

		// Set bounds for every component
		coUsr.setBounds(50, 10, 200, 50); // x, y, largeur, hauteur
		msg.setBounds(100, 520, 1000, 20);
		send.setBounds(1100, 520, 70, 20);
		usrComboBox.setBounds(200, 20, 160, 30);
		select.setBounds(400, 20, 80, 30);
		logOut.setBounds(1100, 20, 150, 30);
		refresh.setBounds(800, 20, 150, 30);

		// Add to the panel
		interfaceFrame.add(coUsr);
		interfaceFrame.add(msg);
		interfaceFrame.add(send);
		interfaceFrame.add(select);
		interfaceFrame.add(usrComboBox);
		interfaceFrame.add(refresh);
		interfaceFrame.add(logOut);

		interfaceFrame.getContentPane().add(scroll, BorderLayout.CENTER);
		interfaceFrame.getContentPane().add(panel, BorderLayout.CENTER);
		// interfaceFrame.pack();

		// Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(1300, 600);
		interfaceFrame.revalidate();
		interfaceFrame.repaint();

	}

	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);
		Updater u = new Updater();
		u.start();

	}

	public String getUserChoosen() {
		return usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
	}

}
