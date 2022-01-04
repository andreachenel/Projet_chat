package threads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import network.NetworkManager;

public class ThreadManager {
	boolean TCPServerRunning = true ;
	HashMap<InetAddress,TCPThread> openTCPConnections = new HashMap<>() ;

	public void send(InetAddress addr,String message) {
		if (openTCPConnections.containsKey(addr)) {
			openTCPConnections.get(addr).send(message);
		} else {
			System.out.println("Error ThreadManager send : no TCP connection found with "+addr.toString());
		}
	}
	
	public ThreadManager () {
	}
	
	public void printTCPConnections () {
		System.out.println("TCP Connections : ");
		for (InetAddress i : openTCPConnections.keySet()) {
			System.out.println("	"+i.toString()) ;
		}
	}

	public void initiateTCP(String addr) {
		int port = NetworkManager.TCPPort+10 ;
		System.out.println("Initiating TCP client at port "+Integer.toString(port));
		NetworkManager.TCPPort+=1 ;
		try {
			// create a socket from - here:TCPPort - to - addr:TCPListenPort to request TCP
			Socket request = new Socket(InetAddress.getByName(addr),NetworkManager.TCPListenPort, InetAddress.getByName(NetworkManager.getLocalAddress()),port) ;
			request.close();
			// receive answer as a TCP request from addr:addr.TCPPort to here:TCPPort
			ServerSocket sock = new ServerSocket(port) ;
			
			// delegate to a TCP Thread
			TCPThread t = new TCPThread(sock.accept());
			t.start();
			openTCPConnections.put(InetAddress.getByName(addr), t) ;
			printTCPConnections();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	// Opens a TCP Server who distributes incoming connection requests by creating TCPThreads
	public void openTCPServer () {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(NetworkManager.TCPListenPort);
			System.out.printf("Opening TCP server at port %d\n",NetworkManager.TCPListenPort);
			while (TCPServerRunning) {
				Socket receiver = serverSocket.accept();
				int remotePort= receiver.getPort();
				InetAddress remoteAddress= receiver.getInetAddress() ;
				receiver.close();
				
				int localPort = NetworkManager.TCPPort ;
				NetworkManager.TCPPort+=1 ;
				
				System.out.println("Opening TCP client at port "+Integer.toString(localPort));
				Socket newSocket = new Socket(remoteAddress,remotePort ,InetAddress.getByName(NetworkManager.getLocalAddress()),localPort) ;
				TCPThread t = new TCPThread(newSocket) ;
				t.start();
				openTCPConnections.put(receiver.getInetAddress(), t) ;
				printTCPConnections();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeTCPServer() {
		this.TCPServerRunning=false ;
	}
}
