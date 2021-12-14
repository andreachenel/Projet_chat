import java.sql.SQLException;
import bdd.DatabaseManager;
import gui.InterfaceConnexion;
import threads.UDPClientThread;
import network.UserManager;
import network.NetworkManager;
import network.User;
import threads.UDPListenThread;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TestMain {
	public static void main (String[] args) {
		
		int portRecep = 2010 ;
		UserManager usrmanager = new UserManager("TestBot", portRecep) ;
		
		UDPListenThread list = new UDPListenThread(portRecep);
		list.start() ;
		
		
		
		
		
		/*	UDPClient cl = new UDPClient("255.255.255.255") ; // 10.2.255.255
		//UDPClient cl = new UDPClient("localhost") ;
		String ret = cl.broadcast("Salut c'est Andréa") ;
		System.out.println(ret) ; */
		
		//DBM.close();
		
		//A faire : éviter superposition "pseudo indisponible"/"dispo"
	}	
}
