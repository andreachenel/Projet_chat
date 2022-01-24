package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bdd.DatabaseManager;
import threads.ThreadManager;
import users.UserManager;

public class MainTruc {
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr, usrCh;
	JPanel panel;
	JButton send, done, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] usersToChoose;
	JScrollPane jpLeft;
	JScrollPane jpRight;
	JTextArea rTxt, lTxt;

	String selectedUser = null ;
	
	private void updateConversation () {
		rTxt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(), selectedUser));
		rTxt.setCaretPosition(rTxt.getText().length() - 1);
	}

	class Updater extends Thread {
		public void run() {
			while (true) {
				try {
					
					usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
					usrComboBox = new JComboBox<String>(usersToChoose);
					
					if (selectedUser!=null) {
						updateConversation() ;
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
		usrCh = new JLabel("User Choosen");
		done = new JButton("Done");
		logOut = new JButton("Log out");
		refresh = new JButton("Refresh");
		rTxt = new JTextArea(200, 100); // lignes, colonnes
		lTxt = new JTextArea(200, 100);

		interfaceFrame.setBounds(0, 0, 1000, 600);
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane();
		interfaceFrame.add(splitPane);
		interfaceFrame.setSize(450, 400);
		interfaceFrame.getContentPane().setLayout(null);

		// rTxt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(), selectedUser));
		rTxt.setText("Choose a user");
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(usersToChoose);

		// Left part
		jpLeft = new JScrollPane(lTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jpLeft.setBounds(0, 70, 450, 465);
		interfaceFrame.getContentPane().add(jpLeft);

		// Right part
		jpRight = new JScrollPane(rTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jpRight.setBounds(450, 70, 830, 410);
		interfaceFrame.getContentPane().add(jpRight);

		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedUser = usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
				usrCh.setText(selectedUser);
				panel.add(usrCh);
			}
		});

		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceFrame.setVisible(false);
				interfaceFrame.dispose();
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

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
				usrComboBox.setModel(new DefaultComboBoxModel(usersToChoose));
			}
		});

		// Creates the panel
		panel = new JPanel();

		// Set bounds for every component
		coUsr.setBounds(50, 10, 200, 50); // x, y, largeur, hauteur
		msg.setBounds(470, 500, 700, 20);
		send.setBounds(1180, 500, 70, 20);
		usrCh.setBounds(160, 150, 400, 100);
		usrComboBox.setBounds(200, 20, 160, 30);
		done.setBounds(400, 20, 80, 30);
		logOut.setBounds(1100, 20, 150, 30);
		refresh.setBounds(800, 20, 150, 30);

		// Add to the panel
		interfaceFrame.add(coUsr);
		interfaceFrame.add(msg);
		interfaceFrame.add(send);
		interfaceFrame.add(usrCh);
		interfaceFrame.add(done);
		interfaceFrame.add(usrComboBox);
		interfaceFrame.add(logOut);
		interfaceFrame.add(refresh);

		interfaceFrame.getContentPane().add(jpLeft, BorderLayout.CENTER);
		interfaceFrame.getContentPane().add(jpRight, BorderLayout.CENTER);

		interfaceFrame.getContentPane().add(panel, BorderLayout.CENTER);

		// Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(1300, 600); // largeur, hauteur
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
