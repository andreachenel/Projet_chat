package network;

import threads.UDPClientThread;
import users.User;
import users.UserManager;

public class MessageHandlerThread extends Thread {
	Message received ;

	public MessageHandlerThread (Message m) {
		received = m ;
	}

	public void run () {
		User us = (User) received.getContent() ;
		UserManager.printUserTab() ;
		switch(received.getType()) {
		case REQUESTPSEUDO :     // Si on reçoit un User, on regarde s'il est dans la table
			System.out.println("Ici MessageHandler, j'ai reçu REQUEST " + us.pseudo) ;
			User me = UserManager.userAt(0) ;
			Message msg = new Message(null, me);

			if (UserManager.findUser(us.pseudo)==-1) {
				msg.setType(MessageType.AUTHORIZEPSEUDO);
			} else  {
				msg.setType(MessageType.DENYPSEUDO);
			}
			UDPClientThread client = new UDPClientThread(msg, us.addrIp,NetworkManager.UDPRequestPort);
			client.start();
			break;

		case AUTHORIZEPSEUDO :
			System.out.println("					<- Pseudo OK du cote de " + us.pseudo);

			NetworkManager.receiveOk=true;
			break;

		case DENYPSEUDO :
			System.out.println("					<- C'est non");

			NetworkManager.pseudoOk=false;
			break;

		case CONFIRMPSEUDO :
			System.out.println("Ici MessageHandler, j'ai reçu CONFIRM " + us.pseudo) ;

			// if this user is already in table, remove it
			int index = UserManager.findUserByAddress(us.addrIp) ;
			if (index !=-1 && us.port==UserManager.userAt(index).port) {
				UserManager.remove(index) ;
				System.out.println("User already in table, removed old pseudo") ;
			}

			UserManager.addUser(us.pseudo, us.addrIp, us.port);
			System.out.println("Added user : " + us.pseudo) ;
			break;
//		case TCPREQUEST :
//			System.out.println("TCP Request received") ;
//			UserManager.changePort(us.pseudo, us.port) ;
//			break ;
		}
	}
}
