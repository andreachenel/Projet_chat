package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.NetworkManager;
import users.UserManager;

public class PseudoInterface {
	JFrame interfaceFrame;
	JTextField pseudoField;
	JButton validateButton;
	JPanel interfacePanel;
	JLabel enterPseudo, pseudoMessage;
	
	
	public PseudoInterface() {
		interfaceFrame = new JFrame("Pseudo");
		pseudoField = new JTextField(10);
		validateButton = new JButton("Ok");
		interfacePanel = new JPanel();
		enterPseudo = new JLabel("Enter your new pseudo");
		pseudoMessage = new JLabel();
		
		// Create the panel
		interfacePanel = new JPanel(null);

		interfacePanel.setBackground(Color.pink);

		// pseudo
		validateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pseudo = pseudoField.getText();

				// broadcast a request to use pseudo. if valid, change pseudo
				int requestResult = NetworkManager.requestPseudo(pseudo);
				if (requestResult == 0) {
					pseudoMessage.setText("Pseudo ok");
					validateButton.setText("pseudo ok");
					interfaceFrame.setVisible(false);
					interfaceFrame.dispose();

				} else if (requestResult == -1) {
					pseudoMessage.setText("Pseudo indisponible");
					validateButton.setText("invalid pseudo");
				} else {
					pseudoMessage.setText("Not connected");
					}
			}	
			});
		interfaceFrame.revalidate();
		interfaceFrame.repaint();	
		
		// Set bounds for every component
		pseudoField.setBounds(10, 50, 150, 20);
		validateButton.setBounds(10, 100, 150, 20);
		enterPseudo.setBounds(10, 10, 300, 20);
		pseudoMessage.setBounds(10, 350, 150, 20);
		
		//Add to the panel
		interfaceFrame.add(enterPseudo);
		interfaceFrame.add(validateButton);
		interfaceFrame.add(pseudoField);
		interfaceFrame.add(pseudoMessage);
		interfaceFrame.getContentPane().add(interfacePanel, BorderLayout.CENTER);

		// Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(300, 200);
	}

	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);

	}	
	}
