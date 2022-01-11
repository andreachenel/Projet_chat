package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bdd.DatabaseManager;
import network.NetworkManager;

public class InterfaceConnexion {
	JFrame interfaceFrame;
	JTextField idField, pseudoField;
	JPasswordField pwdField;
	JButton loginButton, pseudoButton;
	JPanel interfacePanel;
	JLabel connectedMessage, enterId, enterPwd, errorMessage, pseudoMessage;
	Color bordeaux;

	public InterfaceConnexion() {
		interfaceFrame = new JFrame("Log in");
		idField = new JTextField(10);
		pwdField = new JPasswordField();
		pseudoField = new JTextField();
		connectedMessage = new JLabel();
		errorMessage = new JLabel();
		enterId = new JLabel("Identifiant");
		enterPwd = new JLabel("Mot de passe");
		pseudoMessage = new JLabel();
		bordeaux = new Color(64,22,12);
		enterId.setForeground(Color.WHITE);
		enterPwd.setForeground(Color.WHITE);
		connectedMessage.setForeground(Color.WHITE);
		pseudoMessage.setForeground(Color.WHITE);
		
		// Create the panel
		interfacePanel = new JPanel(null);

		interfacePanel.setBackground(bordeaux);

		// Log in
		loginButton = new JButton(new AbstractAction("Log in") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {

				String log = idField.getText();
				String pwd = String.valueOf(pwdField.getPassword());

				errorMessage.setText("");
				connectedMessage.setText("");
				boolean eq = false;
				try {
					eq = DatabaseManager.verifyLogin(log, pwd);
					System.out.println(Boolean.toString(eq));
				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					errorMessage.setText("Erreur, identifiant ou mot de passe incorrect");
				}

				if (eq) // L'ID et le mot de passe concordent
				{

					loginButton.setText("CONNECTED");
					connectedMessage.setText("Connexion réussie ! choisissez un pseudonyme :");
					interfacePanel.add(pseudoField);
					interfacePanel.add(pseudoButton);
					loginButton.setEnabled(false);
					idField.setEnabled(false);
					pwdField.setEnabled(false);

				} else {
					errorMessage.setText("Erreur, identifiant ou mot de passe incorrect");
					loginButton.setText("Not connected");
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
				https: // start.ubuntu-mate.org/

				interfacePanel.add(pseudoButton);

				// broadcast a request to use pseudo. if valid, close the interface
				if (NetworkManager.requestPseudo(pseudo) == 0) {
					pseudoMessage.setText("Pseudo ok");
					pseudoButton.setText("Pseudo valide");
					pseudoButton.setEnabled(false);
					interfaceFrame.setVisible(false);
					interfaceFrame.dispose();

				} else {
					pseudoMessage.setText("Pseudo indisponible");
					pseudoButton.setText("Pseudo invalide");
				}

				interfaceFrame.revalidate();
				interfaceFrame.repaint();
			}

		});

		// Set bounds for every component
		enterId.setBounds(30, 50, 150, 20);
		enterPwd.setBounds(30, 100, 150, 20);
		idField.setBounds(180, 50, 150, 20);
		pwdField.setBounds(180, 100, 150, 20);
		loginButton.setBounds(180, 150, 150, 20);
		pseudoField.setBounds(180, 250, 150, 20);
		connectedMessage.setBounds(350, 150, 500, 30);
		errorMessage.setBounds(350, 150, 500, 30);
		pseudoMessage.setBounds(350, 300, 150, 20);
		pseudoField.setBounds(180, 250, 150, 20);
		pseudoButton.setBounds(180, 300, 150, 20);

		// Add to the Panel
		interfacePanel.add(idField);

		interfacePanel.add(pwdField);
		interfacePanel.add(enterId);
		;
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