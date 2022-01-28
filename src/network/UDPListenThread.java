package network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public class UDPListenThread extends Thread {
	DatagramSocket socket;

	int port;

	boolean running;
	byte[] buffer = new byte[256];
	Object received;

	public UDPListenThread(int p) {
		port = p;

		try {
			// System.out.println("attempting to open listen socket at port " +
			// Integer.toString(port));
			socket = new DatagramSocket(port);
			socket.setSoTimeout(500);
		} catch (SocketException e) {
			System.out.println("	error with port " + Integer.toString(port));
		}
		System.out.printf("Opening UDP listener at port %d\n", port);
	}

	public void run() {
		running = true;
		byte[] inP;
		while (running) {
			DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

			try {
				socket.receive(inPacket);
			} catch (SocketTimeoutException tim) {
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
			inP = inPacket.getData();

			InputStream is = new ByteArrayInputStream(inP);
			try (ObjectInputStream ois = new ObjectInputStream(is)) {
				received = ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			MessageHandlerThread t = new MessageHandlerThread((Message) received);
			t.start();
		}
		socket.close();
		System.out.println("closed listen socket at port " + Integer.toString(port));
	}

	public void close() {
		// System.out.println("closing listen socket at port " +
		// Integer.toString(port));
		running = false;
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}