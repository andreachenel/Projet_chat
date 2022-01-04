package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadManager {
	int port ;
	int nextAvailablePort = 9632;
	boolean TCPServerRunning = true ;
	// fonction pour ouvrir un listener qui répond aux broadcasts et reste ouvert en permanence
	public ThreadManager (int p) {
		this.port = p ;
	}

	// Opens a TCP Server who distributes incoming connection requests by creating TCPServerThreads
	public void openTCPServer () {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			System.out.printf("Opening TCP server at port %d\n",port);
			while (TCPServerRunning) {
				Socket newSocket = serverSocket.accept();
				TCPThread t = new TCPThread(nextAvailablePort, newSocket) ;
				nextAvailablePort+=1 ;
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeTCPServer() {
		this.TCPServerRunning=false ;
	}
}
