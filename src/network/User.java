package network;

import java.io.IOException;
import java.io.Serializable;

import threads.MessageType;

	public class User implements Serializable {
		public String pseudo ;
		public String addrIp ;
		public int port ;	
		private static final long serialVersionUID = 135008554141564168L;
	
	
	public User (String pseudo, String addr, int port) {
		this.pseudo=pseudo;
		this.addrIp=addr;
		this.port=port;
		}
	private void readObject (java.io.ObjectInputStream in)throws IOException, ClassNotFoundException {
		this.pseudo=in.readUTF();
		this.addrIp=in.readUTF();
		this.port=in.readInt();
	}
	
	private void writeObject (java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(pseudo);
		out.writeUTF(addrIp);
		out.writeInt(port);
	}
	
}
