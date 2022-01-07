package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import bdd.DatabaseManager;
import network.NetworkManager;

public class InterfaceManager {

	
	public InterfaceManager() {
	
	}
	static public void run() {
		DatabaseManager DBM = new DatabaseManager();
		DBM.create();
		
		System.out.println("je crée connex");
		MainInterface inter = new MainInterface();

		inter.interfaceFrame.revalidate();
		inter.interfaceFrame.repaint();
		InterfaceConnexion connex = new InterfaceConnexion();
		connex.run();
		connex.interfaceFrame.addWindowListener(new WindowListener() {
		
			@Override
		public void windowClosed(WindowEvent arg0) {
			inter.run();	
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
		
		inter.interfaceFrame.addWindowListener(new WindowListener() {
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
