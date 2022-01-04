package threads;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TCPThread extends Thread {
	
	
	Socket socket ;
	String in = "" ;
	String out = "" ;
	boolean running = true ;

	// Threads can be created by ThreadManager with a socket
	public TCPThread (Socket socket) {
		this.socket= socket ;
	}
	
	// Or by other classes by specifying IP address & port
	public TCPThread (String addr, int port) {
		this.socket= new Socket(InetAddress.getByName(addr), port) ;
	}
	

	public void send (String message) {
		this.out+=message ;
	}

	public void run () {

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream printer = new PrintStream(socket.getOutputStream());

			while (running) {

				// read if there is something to read
				in = reader.readLine();
				if (in.length()>0) {
					System.out.println("Received via TCP : "+in);
				}

				// send if there is something to send
				if (out.length()>0) {
					printer.println(out);
					out="" ;
				}

				// then sleep 1s
				Thread.sleep(1000);
			}

			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		running=false ;
	}
} 