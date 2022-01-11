import bdd.DatabaseManager;
import gui.MainTruc;
import network.NetworkManager;
import threads.ThreadManager;
import threads.UDPListenThread;
import users.UserManager;

public class TestMain {
	public static void main(String[] args) {

		DatabaseManager DBM = new DatabaseManager();
		DBM.create();

		int portRecep = NetworkManager.UDPListenPort ;
		System.out.println("     Starting up TestBot") ;
		
		// Manually connecting : setting ID & pseudo
		UserManager.setMyID("TestBot") ;
		UserManager.insertUserAt(0, "TestBot", NetworkManager.getLocalAddress(), portRecep);
		System.out.println("Local address : "+NetworkManager.getLocalAddress());
		
		// open UDP listener to handle new users & pseudo change requests
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start();

		// open TCP Server to handle conversation requests
		ThreadManager threadManager = new ThreadManager();
		threadManager.start();

		MainTruc mt = new MainTruc();
		mt.run();

	}
}
