package network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

import threads.* ;

public class NetworkManager {

	public static boolean receiveOk = false;
	public static boolean pseudoOk = true;
	
	public static String getLocalAddress () {
		Enumeration<NetworkInterface> n = null;
		try {
			n = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        NetworkInterface e = n.nextElement();
        Enumeration<InetAddress> a = e.getInetAddresses();
        InetAddress addr = a.nextElement();
        addr = a.nextElement();
        return addr.getHostAddress() ;
	}
	
	public static int requestPseudo(String pseudo, int portEnvoi, int portRecep, String oldPseudo) {
		
		// Listen to answers
		UDPListenThread listener = new UDPListenThread(portRecep);
		listener.start() ;
	
		// Request pseudo by broadcasting
		User us = new User(pseudo,NetworkManager.getLocalAddress(),portRecep) ;
		
		Message requestMessage = new Message(MessageType.REQUESTPSEUDO, us, oldPseudo);
		
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
			System.out.println("Conditions ok");
			Message confirmMessage = new Message(MessageType.CONFIRMPSEUDO, us, oldPseudo);
			UDPClientThread confirmClient = new UDPClientThread("255.255.255.255",confirmMessage,portEnvoi);
			confirmClient.start();
			return 0 ;
			
		} else {
			System.out.println("Conditions pas ok");
			return -1 ;
		}
	}
}
