import java.sql.SQLException;

import bdd.DatabaseManager;
import gui.InterfaceConnexion;
import network.NetworkManager;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		InterfaceConnexion inter = new InterfaceConnexion() ;
		inter.run();
		DatabaseManager DBM = new DatabaseManager() ;
		
		DBM.create() ;	
		
		NetworkManager netmanager = new NetworkManager();
		netmanager.run();
		
		//	DBM.close();
		// trouver comment récup l'adresse et tester avec 2 pcs
		// puis mettre en place le broadcast
	}	
}
