package network;

import java.io.IOException;
import java.net.*;

public class UDPClient {
	DatagramSocket socket ;
	InetAddress address ;
	byte[] buf ;
	
	public UDPClient(String addr) {
		try {
			socket = new DatagramSocket() ;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			address = InetAddress.getByName(addr);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String broadcast (String msg) {
		buf = msg.getBytes() ;
		DatagramPacket packet = new DatagramPacket(buf,buf.length,address,1252) ; //Constructs a datagram packet for sending packets of length length to the specified port number on the specified host.
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		packet = new DatagramPacket(buf,buf.length) ;//Constructs a DatagramPacket for receiving packets of length length.
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String received = new String(packet.getData(), 0, packet.getLength()) ;
		return received ;
	}
	
	/*public String send (String msg) {
		null;
		
	} */
	
		
}
