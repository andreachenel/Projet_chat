import java.sql.SQLException;

import network.*;
import threads.*;
import gui.*;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		/*InterfaceConnexion inter = new InterfaceConnexion() ;
		inter.run();

		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;	*/

		InterfaceDiscussion disc = new InterfaceDiscussion();
		disc.run();
//		System.out.println(NetworkManager.getLocalAddress());
//		
//		ThreadManager tm = new ThreadManager() ;
//		tm.openTCPServer();
//		
		// !! initiateTCP has been modified for testing purposes (delete +10 at TM line 26)

		//DBM.close();

	}	
}
