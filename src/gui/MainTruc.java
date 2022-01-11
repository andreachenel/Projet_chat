package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
=======
>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3

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
<<<<<<< HEAD
	JScrollPane scroll;
	JTextArea txt;
=======
	JScrollPane jpLeft;
	JScrollPane jpRight;
	JTextArea rTxt, lTxt;
>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3

	String selectedUser = "TestBot";

	class Updater extends Thread {
		public void run() {
			while (true) {
				try {

<<<<<<< HEAD
					txt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(),selectedUser)) ;
					txt.setCaretPosition(txt.getText().length()-1);
					Thread.sleep(2000) ;
=======
					rTxt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(), selectedUser));
					rTxt.setCaretPosition(rTxt.getText().length() - 1);

					Thread.sleep(2000);
>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3

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
<<<<<<< HEAD
		txt = new JTextArea(200,100); //lignes, colonnes
		
=======
		rTxt = new JTextArea(200, 100); // lignes, colonnes
		lTxt = new JTextArea(200, 100);
>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3

		interfaceFrame.setBounds(0, 0, 1000, 700);
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
<<<<<<< HEAD
		
		
		interfaceFrame.setSize(600, 600);
		interfaceFrame.getContentPane().setLayout(null);


		txt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(),selectedUser)) ;
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(usersToChoose);

		
		// Scroll part
		scroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(30, 70, 1200, 420);
		interfaceFrame.getContentPane().add(scroll);
		
		
=======

		JSplitPane splitPane = new JSplitPane();
		interfaceFrame.add(splitPane);
		interfaceFrame.setSize(450, 400);
		interfaceFrame.getContentPane().setLayout(null);

		rTxt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(), selectedUser));
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

>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedUser = usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
				usrCh.setText(selectedUser);
				DatabaseManager.retrieveMessages(selectedUser, UserManager.myPseudo());
				panel.add(usrCh);
			}
		});

		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceFrame.setVisible(false);
				interfaceFrame.dispose();
			}
		});
		
//		send.addKeyListener(new KeyAdapter() {
//
//			  public void keyPressed(KeyEvent e) {
//			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
//			    	String message = msg.getText();
//
//					ThreadManager t = new ThreadManager();
//					t.sendTo(selectedUser, message);
//					msg.setText("");
//			    }
//			    }
//			    });
		
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
		msg.setBounds(100, 250, 20, 400);
		send.setBounds(1100, 520, 70, 20);
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
<<<<<<< HEAD
		
		interfaceFrame.getContentPane().add(scroll, BorderLayout.CENTER);
=======

		interfaceFrame.getContentPane().add(jpLeft, BorderLayout.CENTER);
		interfaceFrame.getContentPane().add(jpRight, BorderLayout.CENTER);
>>>>>>> 8b961b87f288d7fe51ec62a77205ea3fda00e1b3

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
