import java.sql.SQLException;

import bdd.DatabaseManager;
import gui.InterfaceManager;
import gui.*;
import threads.ThreadManager;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DatabaseManager DBM = new DatabaseManager();
		DBM.create();
		InterfaceManager inter = new InterfaceManager();
		inter.run();
		
//		MainTruc mt = new MainTruc();
//		mt.run();

		String id1 = "TestBot";
		String id2 = "Pierre";
		String message = "omg";
		// String upd = "INSERT INTO messages
		// values('"+id1+"','"+id2+"','"+message+"','1')" ;
		// DBM.update("DELETE FROM messages");
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);
		DatabaseManager.newMessage("Hi", "TestBot", message);

//		DBM.printLoginTable();
		// DBM.printMessages();

//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		ThreadManager tm = new ThreadManager();
//		DBM.printLoginTable();
//		tm.sendTo("TestBot", "ça confctionne");

		// InterfaceConnexion connex = new InterfaceConnexion();
		// connex.run();

		// InterfaceDiscussion disc = new InterfaceDiscussion();
		// disc.run();
	}
}

// notes sur respect MVC : 
//	- 
//	- send devrait être dans NetworkManager
//  - L'interface ne peut pas appeler databaseManager (model) : passer par networkmanager (controller)
//	- MODEL : databaseManager, userManager
// 	- CONTROLLER : 
// 	- VIEW :
