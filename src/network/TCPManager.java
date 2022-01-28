package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import data.UserManager;

public class TCPManager extends Thread {

	private boolean TCPServerRunning = true;
	private static HashMap<InetAddress, TCPThread> openTCPConnections = new HashMap<>();

	public static TCPThread connectedTo(String addr) {
		TCPThread res = null;
		try {
			res = openTCPConnections.get(InetAddress.getByName(addr));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void send(String addr, String message) {
		InetAddress a = null;
		try {
			a = InetAddress.getByName(addr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		if (a != null && openTCPConnections.containsKey(a)) {
			openTCPConnections.get(a).send(message);

		} else {
			System.out.println("Error ThreadManager send : no TCP connection found with " + addr);
			this.initiateTCP(addr);
			openTCPConnections.get(a).send(message);

		}
	}

	public int sendTo(String pseudo, String message) {
		String addr = UserManager.userAddress(pseudo);
		if (!addr.equals("")) {
			send(addr, message);
			return 0 ;
		} else {
			return -1 ;
		}
	}

	public TCPManager() {
	}

	public void printTCPConnections() {
		System.out.println("TCP Connections : ");
		for (InetAddress i : openTCPConnections.keySet()) {
			System.out.println("	" + i.toString());
		}
	}

	public static void closeTCP(String addr) {
		TCPThread t = connectedTo(addr);
		if (t != null) {
			System.out.println("Closing TCP connexion with " + addr);
			t.close();
			try {
				openTCPConnections.remove(InetAddress.getByName(addr));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

	}

	public void initiateTCP(String addr) {
		int port = NetworkManager.TCPPort;
		System.out.println("Initiating TCP client at port " + Integer.toString(port) + " for address " + addr);
		NetworkManager.TCPPort += 2;
		try {
			// create a socket from - here:TCPPort - to - addr:TCPListenPort to request TCP
			System.out.println("	Opening request socket at port " + Integer.toString(port));
			Socket request = new Socket(InetAddress.getByName(addr), NetworkManager.TCPListenPort,
					InetAddress.getByName(NetworkManager.getLocalAddress()), port);
			request.close();
			port += 1;
			// receive answer as a TCP request from addr:addr.TCPPort to here:TCPPort
			System.out.println("	Opening server socket at port " + Integer.toString(port));
			ServerSocket sock = new ServerSocket(port);

			// delegate to a TCP Thread
			TCPThread t = new TCPThread(sock.accept());
			t.start();
			openTCPConnections.put(InetAddress.getByName(addr), t);
			printTCPConnections();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Opens a TCP Server who distributes incoming connection requests by creating
	// TCPThreads
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(NetworkManager.TCPListenPort);
			System.out.printf("Opening TCP server at port %d\n", NetworkManager.TCPListenPort);
			while (TCPServerRunning) {
				Socket receiver = serverSocket.accept();
				int remotePort = receiver.getPort() + 1;
				InetAddress remoteAddress = receiver.getInetAddress();
				receiver.close();

				int localPort = NetworkManager.TCPPort;
				NetworkManager.TCPPort += 1;

				System.out.println("received connect request, Opening TCP client at port " + Integer.toString(localPort)
						+ " to address " + remoteAddress.getHostAddress() + " at port" + Integer.toString(remotePort));
				Socket newSocket = new Socket(remoteAddress, remotePort,
						InetAddress.getByName(NetworkManager.getLocalAddress()), localPort);
				TCPThread t = new TCPThread(newSocket);
				t.start();
				openTCPConnections.put(receiver.getInetAddress(), t);
				printTCPConnections();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeTCPServer() {
		this.TCPServerRunning = false;
	}
}
