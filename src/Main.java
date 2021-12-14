import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import bdd.DatabaseManager;
import gui.InterfaceConnexion;
import threads.Message;
import threads.MessageHandlerThread;
import threads.MessageType;
import threads.UDPClientThread;
import network.*;
import threads.UDPListenThread;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
//		InterfaceConnexion inter = new InterfaceConnexion() ;
//		inter.run();
//		DatabaseManager DBM = new DatabaseManager() ;
//		DBM.create() ;	
		
		int portRecep = 1248 ;
		int portEnvoi = 2010 ;
		
//		usrmanager.addUser("pseudo1", "addr1", 154);
//		usrmanager.addUser("pseudo2", "addr2", 155);
		
		int res = NetworkManager.requestPseudo("Chat", portEnvoi, portRecep, "a");

		if (res==0) {
			UserManager usrmanager = new UserManager("Chat",portRecep) ;
		}
		
		System.out.println("Now lets change my username to Chat2") ;
		
		String oldP = UserManager.findMe().pseudo ;
		int res2 = NetworkManager.requestPseudo("Chat2", portEnvoi, portRecep, oldP);
		if (res==0) {
			UserManager.setUserAt(0,new User("Chat2",NetworkManager.getLocalAddress(),portRecep)) ;
		}
		UserManager.printUserTab();
		
		
		//Interface connexion : est ce qu'on supprimerait pas les champs ID et mdp -> ça peut changer sinon
		
		
		/*	UDPClient cl = new UDPClient("255.255.255.255") ; // 10.2.255.255
		//UDPClient cl = new UDPClient("localhost") ;
		String ret = cl.broadcast("Salut c'est Andréa") ;
		System.out.println(ret) ; */
		
		//DBM.close();
		
	}	
}
