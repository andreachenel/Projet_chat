package network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import bdd.DatabaseManager;

import threads.UDPClientThread;
import threads.UDPListenThread;
import users.User;
import users.UserManager;

public class NetworkManager {

	public static boolean pseudoOk = true;

	// Useful ports for UDP & TCP communication
	public static int UDPListenPort = 2000;
	public static int UDPRequestPort = 2001;
	public static int TCPListenPort = 2002;
	public static int TCPPort = 2003;

	// returns local IP address
	public static String getLocalAddress() {
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
		return addr.getHostAddress();
	}

	public static int requestPseudo(String pseudo) {

		System.out.println("	-> Requesting pseudo " + pseudo);

		// Listen to answers
		UDPListenThread listener = new UDPListenThread(UDPRequestPort);
		listener.start();

		// Request pseudo by broadcasting
		User us = new User(pseudo, NetworkManager.getLocalAddress(), TCPListenPort);
		Message requestMessage = new Message(MessageType.REQUESTPSEUDO, us);

		UDPClientThread requestClient = new UDPClientThread(requestMessage, "255.255.255.255", UDPListenPort);
		requestClient.start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		listener.close();

		// Check if has not received a denial after
		// some time
		if (pseudoOk) {
			System.out.println("	-> Confirming pseudo " + pseudo);
			Message confirmMessage = new Message(MessageType.CONFIRMPSEUDO, us);
			UDPClientThread confirmClient = new UDPClientThread(confirmMessage, "255.255.255.255", UDPListenPort);
			confirmClient.start();

			// update local connectedUsers table & remote database
			UserManager.insertUserAt(0, pseudo, NetworkManager.getLocalAddress(), TCPListenPort);
			UserManager.printUserTab();
			DatabaseManager.changePseudo(pseudo);

			return 0;

		} else {
			System.out.printf("Name already taken !");
			pseudoOk=true ;
			return -1;
		}
	}

	public static int disconnect() {
		System.out.println("Disconnecting bla");

		// Notify disconnection by broadcasting
		User us = new User(UserManager.myPseudo(), NetworkManager.getLocalAddress(), TCPListenPort);
		Message disconnectedMessage = new Message(MessageType.DISCONNECTED, us);

		UDPClientThread requestClient = new UDPClientThread(disconnectedMessage, "255.255.255.255", UDPListenPort);
		requestClient.start();
		return 0;
	}

}