import java.sql.SQLException;

import gui.InterfaceConnexion;
import network.NetworkManager;
import threads.ThreadManager;
import threads.UDPListenThread;
import users.UserManager;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		/*

		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;	*/

		//InterfaceDiscussion disc = new InterfaceDiscussion();
		//disc.run();
		
		int connected = -1 ;
		while (connected==-1) {
			connected = NetworkManager.requestPseudo("Pierre") ;
		}
		UserManager.printUserTab();
		
		UDPListenThread lt = new UDPListenThread(NetworkManager.UDPListenPort) ;
		lt.start();

		ThreadManager tm = new ThreadManager() ;
		tm.start();
		
		tm.send("10.1.5.70","Bonsoir");
		tm.send("10.1.5.70","haha");

		//DBM.close();

	}	
}
