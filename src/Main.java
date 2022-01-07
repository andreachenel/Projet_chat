import java.sql.SQLException;
import com.mysql.jdbc.ResultSet;

import bdd.DatabaseManager;
import users.UserManager;
import network.*;
import threads.*;

import gui.*;


public class Main {
	
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;
		
		String id1="TestBot" ;
		String id2="Pierre" ;
		String message="omg" ;
		//String upd = "INSERT INTO messages values('"+id1+"','"+id2+"','"+message+"','1')" ;
		// DatabaseManager.newMessage("", "XXPierreDu31xX", message);
		//DBM.update("DELETE FROM messages");
		//InterfaceConnexion ic = new InterfaceConnexion() ;
		//ic.run() ;
		
		DatabaseManager.verifyLogin("Pierre", "mdpDePierre") ;
		NetworkManager.requestPseudo("Hi") ;
//		DBM.printLoginTable();
		//DBM.printMessages();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ThreadManager tm = new ThreadManager() ;
		DBM.printLoginTable() ;
		tm.sendTo("TestBot","ça confctionne") ;
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		NetworkManager.disconnect();
		
		//InterfaceConnexion connex = new InterfaceConnexion();
		//connex.run();
		
		//InterfaceDiscussion disc = new InterfaceDiscussion();
		//disc.run();
	}	
}

// notes sur respect MVC : 
//	- 
//	- send devrait être dans NetworkManager
//  - L'interface ne peut pas appeler databaseManager (model) : passer par networkmanager (controller)
//	- MODEL : databaseManager, userManager
// 	- CONTROLLER : 
// 	- VIEW :
