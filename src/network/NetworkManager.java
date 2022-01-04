package network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import threads.* ;
import users.User;
import users.UserManager;

public class NetworkManager {

	public static boolean receiveOk = false;
	public static boolean pseudoOk = true;
	public static int portEnvoi = 2000 ;
	public static int portRecep = 1234 ;

	// returns local IP address
	public static String getLocalAddress () {
		Enumeration<NetworkInterface> n = null;
		try {
			n = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		NetworkInterface interf = n.nextElement();
		Enumeration<InetAddress> a = interf.getInetAddresses();
		InetAddress addr = a.nextElement();
		addr = a.nextElement();
		return addr.getHostAddress() ;
	}

	public static void setPorts(int portE, int portR) {
		portEnvoi = portE ;
		portRecep = portR ;
	}

	public static void setPorts(int portR) {
		portRecep = portR ;
	}

	public static int requestPseudo(String pseudo, int portEnvoi, int portRecep) {

		System.out.println("	-> Requesting pseudo "+pseudo) ;

		// Listen to answers
		UDPListenThread listener = new UDPListenThread(portRecep);
		listener.start() ;

		// Request pseudo by broadcasting
		User us = new User(pseudo,NetworkManager.getLocalAddress(),portRecep) ;
		Message requestMessage = new Message(MessageType.REQUESTPSEUDO, us);

		UDPClientThread requestClient = new UDPClientThread("255.255.255.255",requestMessage,portEnvoi);
		requestClient.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		listener.close();

		// Check conditions (has received something & has not received a denial) after some time
		if (receiveOk && pseudoOk) {
			System.out.println("	-> Confirming pseudo "+pseudo);
			Message confirmMessage = new Message(MessageType.CONFIRMPSEUDO, us);
			UDPClientThread confirmClient = new UDPClientThread("255.255.255.255",confirmMessage,portEnvoi);
			confirmClient.start();
			UserManager.addUser("Chat",NetworkManager.getLocalAddress(),portRecep) ;
			return 0 ;

		} else {
			System.out.printf("Conditions pas ok : receiveOK %b pseudoOK %b\n",receiveOk,pseudoOk);
			return -1 ;
		}
	}
}
