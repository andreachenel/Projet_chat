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
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;
		String id1="TestBot2" ;
		String id2="Pierre2" ;
		String message="bonsoir" ;
		String upd = "INSERT INTO messages values('"+id1+"','"+id2+"','"+message+"','1')" ;
		DBM.retrieveMessages(id1,id2) ;
		//InterfaceConnexion ic = new InterfaceConnexion() ;
		//ic.run() ;
		
//		DatabaseManager.verifyLogin("TestBot", "") ;
//		NetworkManager.requestPseudo("XxPierreDu31xX") ;
//		DBM.printLoginTable();
		DBM.printMessages();
//		
//		UDPListenThread lt = new UDPListenThread(NetworkManager.UDPListenPort) ;
//		lt.start();
//
//		ThreadManager tm = new ThreadManager() ;
//		tm.start();
		
//		tm.sendTo("TestBot","Bonsoir");
//		tm.send("10.1.5.70"," haha");
		
//		System.out.println(NetworkManager.getLocalAddress());
//		
//		ThreadManager tm = new ThreadManager() ;
//		tm.openTCPServer();
//		
		// !! initiateTCP has been modified for testing purposes (delete +10 at TM line 26)

		//DBM.close();*/

	}	
}
