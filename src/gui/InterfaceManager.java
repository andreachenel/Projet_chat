package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import bdd.DatabaseManager;

public class InterfaceManager {

	public InterfaceManager() {

	}

	static public void run() {
		DatabaseManager DBM = new DatabaseManager();
		DBM.create();

		InterfaceConnexion connex = new InterfaceConnexion();
		connex.run();
		connex.interfaceFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				MainInterface inter = new MainInterface();
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

	}

}
