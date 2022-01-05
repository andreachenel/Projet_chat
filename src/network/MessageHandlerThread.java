package network;

import threads.UDPClientThread;
import users.User;
import users.UserManager;

public class MessageHandlerThread extends Thread {
	private Message received ;

	public MessageHandlerThread (Message m) {
		received = m ;
	}

	public void run () {
		User us = (User) received.getContent() ;
		UserManager.printUserTab() ;
		switch(received.getType()) {
		case REQUESTPSEUDO :     // Si on reçoit un User, on regarde s'il est dans la table
			System.out.println("Ici MessageHandler, j'ai reçu REQUEST " + us.getPseudo()) ;
			User me = UserManager.userAt(0) ;
			Message msg = new Message(null, me);

			if (UserManager.findUser(us.getPseudo())==-1) {
				msg.setType(MessageType.AUTHORIZEPSEUDO);
			} else  {
				msg.setType(MessageType.DENYPSEUDO);
			}
			UDPClientThread client = new UDPClientThread(msg, us.getAddrIp(),NetworkManager.UDPRequestPort);
			client.start();
			break;

		case AUTHORIZEPSEUDO :
			System.out.println("					<- Pseudo OK du cote de " + us.getPseudo());
			UserManager.addUser(us) ;
			NetworkManager.receiveOk=true;
			break;

		case DENYPSEUDO :
			System.out.println("					<- C'est non - "+us.getPseudo());
			UserManager.addUser(us) ;
			NetworkManager.pseudoOk=false;
			break;

		case CONFIRMPSEUDO :
			System.out.println("Ici MessageHandler, j'ai reçu CONFIRM " + us.getPseudo()) ;

			// if this user is already in table, remove it
			int index = UserManager.findUserByAddress(us.getAddrIp()) ;
			if (index !=-1 && us.getPort()==UserManager.userAt(index).getPort()) {
				UserManager.remove(index) ;
				System.out.println("User already in table, removed old pseudo") ;
			}

			UserManager.addUser(us.getPseudo(), us.getAddrIp(), us.getPort());
			System.out.println("Added user : " + us.getPseudo()) ;
			break;

		}
	}
}
