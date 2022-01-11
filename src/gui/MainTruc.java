package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.ResultSet;

import bdd.DatabaseManager;
import network.*;
import threads.*;

import users.UserManager;

public class MainTruc {
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr, usrCh;
	JPanel panel;
	JButton send, done, logOut, refresh;
	JComboBox<String> usrComboBox;
	String[] usersToChoose;
	JScrollPane scroll;
	JTextArea txt;

	String selectedUser = "TestBot" ;

	
	class Updater extends Thread {
		public void run() {
			while (true) {
				try {

					txt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(),selectedUser)) ;
					txt.setCaretPosition(txt.getText().length()-1);
					Thread.sleep(2000) ;

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
		txt = new JTextArea(200,100); //lignes, colonnes
		

		interfaceFrame.setBounds(0, 0, 1000, 700);
		interfaceFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		interfaceFrame.setSize(600, 600);
		interfaceFrame.getContentPane().setLayout(null);


		txt.setText(DatabaseManager.retrieveMessages(UserManager.myPseudo(),selectedUser)) ;
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);
		usrComboBox = new JComboBox<String>(usersToChoose);

		
		// Scroll part
		scroll = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(30, 70, 1200, 420);
		interfaceFrame.getContentPane().add(scroll);
		
		
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
						//usrComboBox = new JComboBox<String>(usersToChoose);
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
		
		interfaceFrame.getContentPane().add(scroll, BorderLayout.CENTER);

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
		Updater u = new Updater () ;
		u.start();
		
	}

	public String getUserChoosen() {
		return usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
	}
	
}
