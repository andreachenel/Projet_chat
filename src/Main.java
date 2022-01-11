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

//		DBM.printLoginTable();
		// DBM.printMessages();
//		while (true) {
//			try {
//				Thread.sleep(5000);
//				DatabaseManager.newMessage("Hi", "TestBot", "new");
//				System.out.println(DatabaseManager.retrieveMessages("Pierre", "TestBot")) ;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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
