import bdd.DatabaseManager;
import network.NetworkManager;
import threads.ThreadManager;
import threads.UDPListenThread;
import users.UserManager;

public class TestMain {
	public static void main (String[] args) {

		//DatabaseManager DBM = new DatabaseManager() ;
		//DBM.create() ;

		int portRecep = NetworkManager.UDPListenPort ;

		UserManager.addUser("TestBot", NetworkManager.getLocalAddress(),portRecep) ;
		UserManager.printUserTab() ;

		// open UDP listener to handle new users & pseudo change requests
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start() ;

		// open TCP Server to handle conversation requests
		ThreadManager threadManager = new ThreadManager () ;
		threadManager.openTCPServer();


		//DBM.close();

	}	
}
