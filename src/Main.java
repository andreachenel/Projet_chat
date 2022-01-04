import java.sql.SQLException;

import bdd.DatabaseManager;
import gui.*;
import network.NetworkManager;
import users.*;

public class Main {
	public static void main (String[] args) throws ClassNotFoundException, SQLException {

		/*InterfaceConnexion inter = new InterfaceConnexion() ;
		inter.run();
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;	*/
		InterfaceDiscussion disc = new InterfaceDiscussion();
		disc.run();

//		NetworkManager.setPorts(2030, 1352);
//		int portRecep = NetworkManager.portRecep;
//		int portEnvoi = NetworkManager.portEnvoi ;

		// request pseudo until it is accepted
		/*int res = -1 ;
		while (res==-1) {
			res = NetworkManager.requestPseudo("Chat", portEnvoi, portRecep);
			portRecep+=1 ;
		}*/

		// changing pseudo
		/*System.out.println("Now lets change my pseudo to Chat2") ;

		res = NetworkManager.requestPseudo("Chat2", portEnvoi, portRecep);
		if (res==0) {
			UserManager.setUserAt(0,new User("Chat2",NetworkManager.getLocalAddress(),portRecep)) ;
		}
		UserManager.printUserTab();*/






		/*	UDPClient cl = new UDPClient("255.255.255.255") ; // 10.2.255.255
		//UDPClient cl = new UDPClient("localhost") ;
		String ret = cl.broadcast("Salut c'est Andréa") ;
		System.out.println(ret) ; */

		//DBM.close();

	}	
}
