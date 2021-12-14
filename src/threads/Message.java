package threads;

import java.io.*;
import network.*;

public class Message implements Serializable {
	
	private MessageType type; 
	private Object content;
	private String oldPseudo="a" ;
	private static final long serialVersionUID = 13500854141564168L;
	
	public Message(MessageType type, Object content) {
		this.type = type;
		this.content = content;
	}
	
	public Message(MessageType type, Object content, String oldPseudo) {
		this.type = type;
		this.content = content;
		this.oldPseudo = oldPseudo ;
		System.out.println("changed old pseudo : "+ oldPseudo) ;
	}

	public String getOldPseudo() {
		return oldPseudo;
	}

	public void setOldPseudo(String oldPseudo) {
		this.oldPseudo = oldPseudo;
	}

	public MessageType getType() {
		return type;
	}


	public void setType(MessageType type) {
		this.type = type;
	}


	public Object getContent() {
		return content;
	}


	public void setContent(Object content) {
		this.content = content;
	}
	private void readObject (java.io.ObjectInputStream in)throws IOException, ClassNotFoundException {
		this.type=MessageType.valueOf(in.readUTF());
		this.content=in.readObject();
		this.oldPseudo =in.readUTF();
	}
	
	private void writeObject (java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(type.name());
		out.writeObject(content);
		out.writeUTF(oldPseudo);
	}
	
	/*public static void main (String args[]) {
		User us = new User("tptp", "addr", 1546);
		Message msg = new Message(MessageType.REQUESTPSEUDO, us);
		System.out.println("LE contenu est : " + msg.toString());
	}*/
	
}
