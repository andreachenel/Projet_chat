package network;

import java.io.IOException;
import java.io.Serializable;

public class Message implements Serializable {
	
	private MessageType type; 
	private Object content;
	private static final long serialVersionUID = 13500854141564168L;
	
	public Message(MessageType type, Object content) {
		this.type = type;
		this.content = content;
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
	}
	
	private void writeObject (java.io.ObjectOutputStream out) throws IOException {
		out.writeUTF(type.name());
		out.writeObject(content);
	}
	
}
