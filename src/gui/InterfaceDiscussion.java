package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import users.*;

public class InterfaceDiscussion {
	JFrame interfaceFrame;
	JTextField msg;
	JLabel coUsr, usrCh;
	JPanel panel;
	JButton envoi, button, done;
	JComboBox<String> usrComboBox;
	String[] usersToChoose;
	

	public InterfaceDiscussion() {
		interfaceFrame = new JFrame("Chat");
		msg = new JTextField (10);
		coUsr = new JLabel("Connected Users : ");
		envoi = new JButton ("^");
		button = new JButton ("blabla");
		usrCh = new JLabel ("User Choosen");
		done = new JButton("Done");
		
		
		
		
		usersToChoose = UserManager.pseudoTab().toArray(new String[UserManager.pseudoTab().size()]);

		usrComboBox = new JComboBox<String>(usersToChoose);
		
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedUser = "You selected " + usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
                panel.add(usrCh);
                usrCh.setText(selectedUser);

            }
        });
    
        
		//Creates the panel
		panel = new JPanel(null);
	

		// Set bounds for every component
		coUsr.setBounds(80, 10, 200, 80); //x, y, largeur, hauteur
		msg.setBounds(650, 480, 500, 20);
		envoi.setBounds(1180, 480, 30, 20);
		button.setBounds(30, 50, 200, 30);
		usrCh.setBounds(90, 150, 400, 100);
		usrComboBox.setBounds(80, 100, 160, 30);
        done.setBounds(120, 150, 80, 20);

		//Add to the panel 
		panel.add(coUsr);
		panel.add(msg);		
		panel.add(envoi);
		panel.add(done);
        panel.add(usrComboBox);
		
		interfaceFrame.getContentPane().add(panel, BorderLayout.CENTER);

		//Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(1300,600) ;	 // largeur, hauteur
	}

	public void run () {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);
	}
	public String getUserChoosen() {
		return usrComboBox.getItemAt(usrComboBox.getSelectedIndex());
	}

}
