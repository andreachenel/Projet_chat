import java.sql.SQLException;

import bdd.DatabaseManager;
import gui.InterfaceConnexion;
import network.NetworkManager;
import threads.ThreadManager;
import threads.UDPListenThread;
import users.UserManager;

import network.*;
import threads.*;
import gui.*;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		//InterfaceDiscussion disc = new InterfaceDiscussion();
		//disc.run();
		UserManager.setMyID("1");
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;
		/*int connected = -1 ;
		
		while (connected==-1) {
			connected = NetworkManager.requestPseudo("Pierre") ;
		}*/
		
		DatabaseManager.changePseudo("Pierre") ;
		DBM.printLoginTable();
		
		UserManager.printUserTab();
		
		/*UDPListenThread lt = new UDPListenThread(NetworkManager.UDPListenPort) ;
		lt.start();

		ThreadManager tm = new ThreadManager() ;
		tm.start();
		
		tm.send("10.1.5.70","Bonsoir");
		tm.send("10.1.5.70","haha");
		InterfaceDiscussion disc = new InterfaceDiscussion();
		disc.run();*/
		
//		System.out.println(NetworkManager.getLocalAddress());
//		
//		ThreadManager tm = new ThreadManager() ;
//		tm.openTCPServer();
//		
		// !! initiateTCP has been modified for testing purposes (delete +10 at TM line 26)

		//DBM.close();

	}	
}
