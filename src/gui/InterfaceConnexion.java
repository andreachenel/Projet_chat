package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import network.*;

import bdd.DatabaseManager;

public class InterfaceConnexion {
	JFrame interfaceFrame;
	public JTextField idField, pwdField, pseudoField ;
	JButton loginButton, pseudoButton ;
	JPanel interfacePanel;
	JLabel connectedMessage, enterId, enterPwd, errorMessage, pseudoMessage ;


	public InterfaceConnexion() {
		interfaceFrame = new JFrame("Log in");
		idField = new JTextField (10) ;
		pwdField = new JTextField (10) ;
		pseudoField = new JTextField ();
		connectedMessage = new JLabel();
		errorMessage = new JLabel(); 
		enterId  = new JLabel("Identifiant");
		enterPwd  = new JLabel("Mot de passe");
		pseudoMessage = new JLabel();


		//Create the panel
		interfacePanel = new JPanel(null); 

		loginButton = new JButton (new AbstractAction("Log in") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed (ActionEvent event) {

				String log = idField.getText();
				String pwd = pwdField.getText();

				errorMessage.setText("");
				connectedMessage.setText("");
				boolean eq=false;
				try {
					eq = DatabaseManager.verifyLogin(log, pwd) ;
				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					errorMessage.setText("Erreur, identifiant ou mot de passe incorrect");
				} 

				if (eq) //L'ID et le mot de passe concordent
				{
					loginButton.setText("CONNECTED");
					connectedMessage.setText("Connexion réussie ! choisissez un pseudonyme :");
					interfacePanel.add(pseudoField);
					interfacePanel.add(pseudoButton);	
					loginButton.setEnabled(false);


				} else {
					errorMessage.setText("Erreur, identifiant ou mot de passe incorrect");	
					loginButton.setText("Not connected");
				}
				interfaceFrame.revalidate() ;
				interfaceFrame.repaint() ;
			}

		}) ;

		pseudoButton = new JButton ( new AbstractAction("Check pseudo") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed (ActionEvent event) {
				String pseudo = pseudoField.getText();

				interfacePanel.add(pseudoButton);	

				/* if le pseudo est valide */
				NetworkManager.setPorts(2030, 1352);
				if ((NetworkManager.requestPseudo(pseudo, NetworkManager.portEnvoi , NetworkManager.portRecep))==0)
				{
					pseudoMessage.setText("Pseudo ok");	
					pseudoButton.setText("Pseudo valide");	
					pseudoButton.setEnabled(false);

				} else {
					pseudoMessage.setText("Pseudo indisponible");	
					pseudoButton.setText("Pseudo invalide");
				}
				interfaceFrame.setVisible(true);
				interfaceFrame.revalidate() ;
				interfaceFrame.repaint() ;
			}
		}) ;

		// Set bounds for every component
		enterId.setBounds(30,50,150,20);
		enterPwd.setBounds(30,100,150,20);
		idField.setBounds(180,50,150,20);
		pwdField.setBounds(180,100,150,20);
		loginButton.setBounds(180,150,150,20);
		pseudoField.setBounds(180,250,150,20);
		connectedMessage.setBounds(350,150,500,30);
		errorMessage.setBounds(350,150,500,30);
		pseudoMessage.setBounds(350,300,150,20);
		pseudoField.setBounds(180,250,150,20);
		pseudoButton.setBounds(180,300,150,20);		 

		//Add to the Panel
		interfacePanel.add(idField);
		interfacePanel.add(pwdField);
		interfacePanel.add(enterId);
		interfacePanel.add(enterPwd);
		interfacePanel.add(loginButton);	
		interfacePanel.add(connectedMessage);
		interfacePanel.add(errorMessage);
		interfacePanel.add(pseudoMessage);

		interfaceFrame.getContentPane().add(interfacePanel, BorderLayout.CENTER);

		//Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(800,400) ;	 
	}

	public void run () {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);
	}

	public JTextField getidField () {
		return this.idField ;
	}
	public JTextField getpwdField () {
		return this.pwdField ;
	}
}