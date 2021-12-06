package network;

import java.io.IOException;
import java.net.*;

public class UDPClient {
	DatagramSocket socket ;
	InetAddress address ;
	byte[] buf ;
	
	public UDPClient() {
		try {
			socket = new DatagramSocket() ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			address = InetAddress.getByName("localhost") ;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String send (String msg) {
		buf = msg.getBytes() ;
		DatagramPacket packet = new DatagramPacket(buf,buf.length,address,1237) ;
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		packet = new DatagramPacket(buf,buf.length) ;
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String received = new String(packet.getData(), 0, packet.getLength()) ;
		return received ;
	}
	
	public static void main (String[] args) {
		UDPClient cl = new UDPClient() ;
		String ret = cl.send("bonsoir") ;
		System.out.println(ret) ;
		
	}
		
}
