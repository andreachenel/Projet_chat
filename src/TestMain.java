import bdd.DatabaseManager;
import network.NetworkManager;
import threads.UDPListenThread;
import users.UserManager;

public class TestMain {
	public static void main (String[] args) {

		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;

		NetworkManager.setPorts(2030, 1352);

		int portRecep = NetworkManager.portEnvoi ;
		
		UserManager.addUser("TestBot", NetworkManager.getLocalAddress(),portRecep) ;
		UserManager.printUserTab() ;
		
		// open UDP listener to handle new users & pseudo change requests
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start() ;

		/*	UDPClient cl = new UDPClient("255.255.255.255") ; // 10.2.255.255
		//UDPClient cl = new UDPClient("localhost") ;
		String ret = cl.broadcast("Salut c'est Andréa") ;
		System.out.println(ret) ; */

		//DBM.close();

	}	
}
