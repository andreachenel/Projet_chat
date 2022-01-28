package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import data.DatabaseManager;
import network.NetworkManager;

public class InterfaceConnexion {
	JFrame interfaceFrame;
	JTextField idField, pseudoField;
	JPasswordField pwdField;
	JButton loginButton, pseudoButton;
	JPanel interfacePanel;
	JLabel connectedMessage, enterId, enterPwd, errorMessage, pseudoMessage;
	Color red;

	public InterfaceConnexion() {
		interfaceFrame = new JFrame("Log in / Register");
		idField = new JTextField(10);
		pwdField = new JPasswordField();
		pseudoField = new JTextField();
		connectedMessage = new JLabel();
		errorMessage = new JLabel();
		enterId = new JLabel("Username");
		enterPwd = new JLabel("Password");
		pseudoMessage = new JLabel();
		loginButton = new JButton("Log in / Register");
		red = new Color(64, 22, 12);
		enterId.setForeground(Color.WHITE);
		enterPwd.setForeground(Color.WHITE);
		connectedMessage.setForeground(Color.WHITE);
		pseudoMessage.setForeground(Color.WHITE);
		errorMessage.setForeground(Color.WHITE);

		// Create the panel
		interfacePanel = new JPanel(null);

		interfacePanel.setBackground(red);

		// Log in
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				String log = idField.getText();
				String pwd = String.valueOf(pwdField.getPassword());

				errorMessage.setText("");
				connectedMessage.setText("");
				int eq = -1;
				try {
					eq = DatabaseManager.verifyLogin(log, pwd);
					System.out.println("verifylogin result : "+ Integer.toString(eq));
				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					errorMessage.setText("Unknown error");
				}

				if (eq==-1) {
					errorMessage.setText("Error, incorrect password");
					loginButton.setText("Not connected");					
				} else {
					if (eq==0) {
						loginButton.setText("CONNECTED");						
					} else {
						errorMessage.setText("Unknown ID - new account created !") ;
					}
					connectedMessage.setText("Successful connection ! Choose a pseudo :");
					interfacePanel.add(pseudoField);
					interfacePanel.add(pseudoButton);
					loginButton.setEnabled(false);
					idField.setEnabled(false);
					pwdField.setEnabled(false);					
				}

				interfaceFrame.revalidate();
				interfaceFrame.repaint();
			}

		});

		// Choose a pseudo
		pseudoButton = new JButton(new AbstractAction("Check pseudo") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {
				String pseudo = pseudoField.getText();
				//https: // start.ubuntu-mate.org/

				interfacePanel.add(pseudoButton);

				// broadcast a request to use pseudo. if valid, close the interface
				int requestResult = NetworkManager.requestPseudo(pseudo);
				if (requestResult == 0) {
					pseudoMessage.setText("Pseudo ok");
					pseudoButton.setText("Pseudo valide");
					pseudoButton.setEnabled(false);
					interfaceFrame.setVisible(false);
					interfaceFrame.dispose();

				} else if (requestResult == -1) {
					pseudoMessage.setText("Pseudo indisponible");
					pseudoButton.setText("Pseudo invalide");
				} else {
					pseudoMessage.setText("Not connected");

					/*
					 * // bypass connection (for testing purposes) UserManager.insertUserAt(0,
					 * pseudo, NetworkManager.getLocalAddress(), NetworkManager.TCPListenPort);
					 * DatabaseManager.changePseudo(pseudo); interfaceFrame.setVisible(false);
					 * interfaceFrame.dispose();
					 */

				}

				interfaceFrame.revalidate();
				interfaceFrame.repaint();
			}

		});

		// Set bounds for every component
		enterId.setBounds(30, 50, 150, 20);
		enterPwd.setBounds(30, 100, 150, 20);
		idField.setBounds(170, 50, 180, 20);
		pwdField.setBounds(170, 100, 180, 20);
		loginButton.setBounds(170, 150, 180, 20);
		pseudoField.setBounds(180, 250, 150, 20);
		connectedMessage.setBounds(350, 180, 500, 30);
		errorMessage.setBounds(350, 150, 500, 30);
		pseudoMessage.setBounds(350, 300, 150, 20);
		pseudoField.setBounds(180, 250, 150, 20);
		pseudoButton.setBounds(180, 300, 150, 20);

		// Add to the Panel
		interfacePanel.add(idField);

		interfacePanel.add(pwdField);
		interfacePanel.add(enterId);
		interfaceFrame.repaint();
		interfacePanel.add(enterPwd);
		interfacePanel.add(loginButton);
		interfacePanel.add(connectedMessage);
		interfacePanel.add(errorMessage);
		interfacePanel.add(pseudoMessage);

		interfaceFrame.getContentPane().add(interfacePanel, BorderLayout.CENTER);

		// Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(800, 400);
	}

	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);

	}

	public JTextField getidField() {
		return this.idField;
	}

	public JTextField getpwdField() {
		return this.pwdField;
	}
}