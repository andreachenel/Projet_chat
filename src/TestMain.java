import bdd.DatabaseManager;
import gui.InterfaceManager;
import network.NetworkManager;
import users.UserManager;

public class TestMain {
	public static void main (String[] args) {


		UserManager.insertUserAt(0,"TestBot", NetworkManager.getLocalAddress(),NetworkManager.TCPListenPort) ;
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;
		InterfaceManager inter = new InterfaceManager();
		inter.run();
		
//		MainInterface main= new MainInterface();
//		main.run();
		
//
//		int portRecep = NetworkManager.UDPListenPort ;
//
		UserManager.insertUserAt(0,"TestBot", NetworkManager.getLocalAddress(),NetworkManager.TCPListenPort) ;
//		System.out.println(NetworkManager.getLocalAddress());
//		// open UDP listener to handle new users & pseudo change requests
//		UDPListenThread list = new UDPListenThread(portRecep);
//		list.start() ;
//
//		// open TCP Server to handle conversation requests
//		ThreadManager threadManager = new ThreadManager () ;
//		threadManager.start();
//		
////		try {
////			Thread.sleep(10000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
		
		

		//MainInterface inter = new MainInterface();
		//inter.run();


	}	
}
