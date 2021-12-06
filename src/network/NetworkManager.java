package network;

import java.io.IOException;
import java.net.*;

public class NetworkManager {
	DatagramSocket dtgSocket ;
	boolean running ;
	byte[] buffer = new byte[256];
	
	
	public NetworkManager () {
	
		try {
			dtgSocket = new DatagramSocket(1237);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		running = true ;
		
		while(running) {
			DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
			try {
				dtgSocket.receive(inPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			InetAddress address = inPacket.getAddress() ;
			int port = inPacket.getPort() ;
			
			String received = new String(inPacket.getData(), 0, inPacket.getLength()) ;
			String msg = "ahahaha" ;
			buffer=msg.getBytes();
			DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, address, port);
			try {
				dtgSocket.send(outPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void close() {
		dtgSocket.close() ;
	}

	
}