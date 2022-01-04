import java.sql.SQLException;

import bdd.DatabaseManager;
import gui.*;
import network.NetworkManager;
import threads.TCPThread;
import threads.ThreadManager;
import users.*;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		/*InterfaceConnexion inter = new InterfaceConnexion() ;
		inter.run();
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;	*/
		
		//InterfaceDiscussion disc = new InterfaceDiscussion();
		//disc.run();

		ThreadManager tm = new ThreadManager() ;
		tm.initiateTCP(NetworkManager.getLocalAddress());
		// !! initiateTCP has been modified for testing purposes (delete +10 at TM line 26)

		//DBM.close();

	}	
}
