package gui;

import java.awt.*;
import javax.swing.*;
import users.*;

public class InterfaceDiscussion {
	JFrame interfaceFrame;
	JTextField txt;
	JLabel conv, usrCo;
	JPanel panel;

	public InterfaceDiscussion() {
		interfaceFrame = new JFrame("discussion");
		txt = new JTextField (10);
		conv = new JLabel("Conversations : ");

		//Creates the panel
		panel = new JPanel(null);
		
		//for (int i=0; i<length(UserTab); i++);

		// Set bounds for every component
		conv.setBounds(50, 50, 200, 80); //x, y, largeur, hauteur
		txt.setBounds(60, 300, 20, 10);

		//Add to the panel 
		panel.add(conv);
		panel.add(txt);
		
		interfaceFrame.getContentPane().add(panel, BorderLayout.CENTER);

		//Display the window
		interfaceFrame.pack();
		interfaceFrame.setSize(1300,600) ;	 // largeur, hauteur
	}

	public void run () {
		JFrame.setDefaultLookAndFeelDecorated(true);
		interfaceFrame.setVisible(true);
	}

}
