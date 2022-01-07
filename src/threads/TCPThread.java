package threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import bdd.DatabaseManager;
import users.UserManager;

public class TCPThread extends Thread {

	private Socket socket;
	private String in = "";
	private String out = "";
	private boolean running = true;

	// Threads are created by ThreadManager with a socket
	public TCPThread(Socket socket) {
		this.socket = socket;
	}

	public void send(String message) {
		this.out += message;
	}

	public void run() {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream printer = new PrintStream(socket.getOutputStream());

			while (running) {
				// read if there is something to read
				if (reader.ready()) {
					in = reader.readLine();
					System.out.println("Received via TCP : " + in);
				}

				// send if there is something to send
				if (out.length() > 0) {
					printer.println(out);
					String remotePseudo = UserManager
							.userAt(UserManager.findUserByAddress(socket.getInetAddress().getHostAddress()))
							.getPseudo();
					DatabaseManager.newMessage(UserManager.myPseudo(), remotePseudo, out);

					out = "";
				}
			}
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		running = false;
	}
}