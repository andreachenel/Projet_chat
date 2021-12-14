package threads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import network.User;

public class UDPClientThread extends Thread {
	DatagramSocket socket ;
	
	InetAddress address ;
	int port ;
	
	byte[] buf ;
	Object message ;

	
	public UDPClientThread (String addr, Object msg, int p) {
		message = msg ;
		port = p ;
		
		try {
			socket = new DatagramSocket() ;
			address = InetAddress.getByName(addr);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void run () {
						
			// Convertir l'object en byte pour l'envoyer
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream in;
		try {
			in = new ObjectOutputStream(b);
			in.writeObject(message);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		buf =  b.toByteArray();
		
			// Envoyer le byte array obtenu
		DatagramPacket packet = new DatagramPacket(buf,buf.length,address,port) ;
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}	
}
