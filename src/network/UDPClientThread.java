package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class UDPClientThread extends Thread {
	DatagramSocket socket;

	InetAddress address;
	int port;

	byte[] buf;
	Object message;

	public UDPClientThread(Object msg, String addr, int port) {
		message = msg;
		this.port = port;

		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName(addr);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// Convertir l'object en byte pour l'envoyer
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream in;
		try {
			in = new ObjectOutputStream(b);
			in.writeObject(message);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		buf = b.toByteArray();

		// Envoyer le byte array obtenu
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}
}
