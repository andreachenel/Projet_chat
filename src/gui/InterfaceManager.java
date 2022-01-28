package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import data.DatabaseManager;
import network.NetworkManager;

public class InterfaceManager {

	InterfaceConnexion connexionInt ;
	MainInterface mainInt ;
	
	public InterfaceManager() {
	}

	public void run() {
		DatabaseManager DBM = new DatabaseManager();
		DBM.create();

		mainInt = new MainInterface(); 

		mainInt.interfaceFrame.revalidate();
		mainInt.interfaceFrame.repaint();
		connexionInt = new InterfaceConnexion();
		connexionInt.run();
		connexionInt.interfaceFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				NetworkManager.createUDPListener() ;
				NetworkManager.createTCPServer() ;


				mainInt.run();
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});

		mainInt.interfaceFrame.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg1) {
			}

			@Override
			public void windowClosed(WindowEvent arg1) {
				NetworkManager.disconnect();
				System.out.println("fenetre fermée");
				System.exit(0);
			}

			@Override
			public void windowClosing(WindowEvent arg1) {
				NetworkManager.disconnect();
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg1) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg1) {
			}

			@Override
			public void windowIconified(WindowEvent arg1) {
			}

			@Override
			public void windowOpened(WindowEvent arg1) {
			}
		});

	}

}
