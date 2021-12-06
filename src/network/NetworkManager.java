package network;

import java.io.IOException;
import java.net.*;

public class NetworkManager {
	DatagramSocket dtgSocket ;
	boolean running ;
	byte[] buffer = new byte[256];
	
	
	public NetworkManager () {
	
		try {
			dtgSocket = new DatagramSocket(1246);
		} catch (SocketException e) {
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
			String msg = "J'ai reçu " + received ;
			buffer=msg.getBytes();
			DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, address, port);
			System.out.println(msg);
			try {
				dtgSocket.send(outPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.close();
	}
	
	public void close() {
		dtgSocket.close() ;
	}
}