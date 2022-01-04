import java.sql.SQLException;

import network.NetworkManager;
import threads.ThreadManager;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		/*InterfaceConnexion inter = new InterfaceConnexion() ;
		inter.run();

		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;	*/

		//InterfaceDiscussion disc = new InterfaceDiscussion();
		//disc.run();

		ThreadManager tm = new ThreadManager() ;
		tm.send(NetworkManager.getLocalAddress(),"Bonsoir");
		// !! initiateTCP has been modified for testing purposes (delete +10 at TM line 26)

		//DBM.close();

	}	
}
