package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import users.UserManager;

public class MainInterface {
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr, usrCh;
	JPanel panel;
	JButton send, done, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] usersToChoose;
	// JScrollPane scPane;
	

	public MainInterface() {
		interfaceFrame = new JFrame("Chat");
		msg = new JTextField(10);
		coUsr = new JLabel("Connected Users : ");
		send = new JButton("Send");
		usrCh = new JLabel("User Choosen");
		done = new JButton("Done");
		logOut = new JButton("Log out");
		refresh = new JButton("Refresh");
		// scPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

	//	interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(usersToChoose);

		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedUser = "You selected " + usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
				panel.add(usrCh);
				usrCh.setText(selectedUser);
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

			}
		});

		// Creates the panel
		panel = new JPanel();
		
		//scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Set bounds for every component
		coUsr.setBounds(150, 10, 200, 80); // x, y, largeur, hauteur
		msg.setBounds(650, 480, 500, 20);
		send.setBounds(1180, 480, 70, 20);
		usrCh.setBounds(160, 150, 400, 100);
		usrComboBox.setBounds(150, 100, 160, 30);
		done.setBounds(190, 150, 80, 20);
		logOut.setBounds(1100, 20, 100, 30);
		refresh.setBounds(20, 20, 150, 30);
		//scPane.setBounds(0, 0, 1300, 600);

		// Add to the panel
		interfaceFrame.add(coUsr);
		interfaceFrame.add(msg);
		interfaceFrame.add(send);
		interfaceFrame.add(usrCh);
		interfaceFrame.add(done);
		interfaceFrame.add(usrComboBox);
		interfaceFrame.add(logOut);
		interfaceFrame.add(refresh);
		

		interfaceFrame.getContentPane().add(panel, BorderLayout.CENTER);
		//interfaceFrame.getContentPane().add(scPane,BorderLayout.CENTER);

		// Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(1300, 600); // largeur, hauteur
		interfaceFrame.revalidate();
		interfaceFrame.repaint();

	}

	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);
	}

	public String getUserChoosen() {
		return usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
	}

}
