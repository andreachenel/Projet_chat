import bdd.DatabaseManager;
import network.NetworkManager;
import threads.*;
import users.UserManager;
import gui.*;

public class TestMain {
	public static void main (String[] args) {

		//DatabaseManager DBM = new DatabaseManager() ;
		//DBM.create() ;

		int portRecep = NetworkManager.UDPListenPort ;

		UserManager.insertUserAt(0,"TestBot", NetworkManager.getLocalAddress(),portRecep) ;
		System.out.println(NetworkManager.getLocalAddress());
		// open UDP listener to handle new users & pseudo change requests
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start() ;

		// open TCP Server to handle conversation requests
		ThreadManager threadManager = new ThreadManager () ;
		threadManager.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainInterface inter = new MainInterface();
		inter.run();


	}	
}
