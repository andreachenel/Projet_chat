import bdd.DatabaseManager;
import network.NetworkManager;
import threads.ThreadManager;
import threads.UDPListenThread;
import users.UserManager;

public class TestMain {
	public static void main (String[] args) {

		UserManager.insertUserAt(0,"TestBot", NetworkManager.getLocalAddress(),NetworkManager.TCPListenPort) ;
		UserManager.printUserTab() ;
		
		int portRecep = NetworkManager.UDPListenPort ;

		// open UDP listener to handle new users & pseudo change requests
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start() ;

		// open TCP Server to handle conversation requests
		ThreadManager threadManager = new ThreadManager () ;
		threadManager.start();


	}	
}
