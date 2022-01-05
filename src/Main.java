import java.sql.SQLException;
import users.UserManager;
import network.*;
import threads.*;
import gui.*;
import bdd.*;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {


		InterfaceConnexion connex = new InterfaceConnexion();
		connex.run();
		
		InterfaceDiscussion disc = new InterfaceDiscussion();
		disc.run();
//		
//		UserManager.setMyID("1");
//		
//		DatabaseManager DBM = new DatabaseManager() ;
//		DBM.create() ;
		/*int connected = -1 ;
		
		while (connected==-1) {
			connected = NetworkManager.requestPseudo("Pierre") ;
		}*/
		
//		DatabaseManager.changePseudo("Pierre") ;
//		DBM.printLoginTable();
//		
//		UserManager.printUserTab();
		
		/*UDPListenThread lt = new UDPListenThread(NetworkManager.UDPListenPort) ;
		lt.start();
>>>>>>> refs/remotes/origin/main

		InterfaceDiscussion disc = new InterfaceDiscussion();
<<<<<<< HEAD
		disc.run();
	       
//	
//		int connected = -1 ;
//		while (connected==-1) {
//			connected = NetworkManager.requestPseudo("Pierre") ;
//		}
//		UserManager.printUserTab();
//		
//		UDPListenThread lt = new UDPListenThread(NetworkManager.UDPListenPort) ;
//		lt.start();
//
//		ThreadManager tm = new ThreadManager() ;
//		tm.start();
//		
//		tm.send("10.1.5.70","Bonsoir");
//		tm.send("10.1.5.70","haha");
		
//		InterfaceDiscussion disc = new InterfaceDiscussion();
//		disc.run();
=======
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
