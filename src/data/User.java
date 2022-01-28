package data;

import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {
	private String pseudo;
	private String addrIp;
	private int port;
	private static final long serialVersionUID = 135008554141564168L;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getAddrIp() {
		return addrIp;
	}

	public void setAddrIp(String addrIp) {
		this.addrIp = addrIp;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public User(String pseudo, String addr, int port) {
		this.pseudo = pseudo;
		this.addrIp = addr;
		this.port = port;
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.pseudo = in.readUTF();
		this.addrIp = in.readUTF();
		this.port = in.readInt();
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(pseudo);
		out.writeUTF(addrIp);
		out.writeInt(port);
	}

}
