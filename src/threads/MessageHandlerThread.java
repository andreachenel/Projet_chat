package threads;

import network.NetworkManager;
import network.User;
import network.UserManager;

public class MessageHandlerThread extends Thread {
	Message received ;
	
	public MessageHandlerThread (Message m) {
		received = m ;
	}
	
	public void run () {
		User us = (User) received.getContent() ;
		
		switch(received.getType()) {
			case REQUESTPSEUDO :     // Si on reçoit un User, on regarde s'il est dans la table
				System.out.println("Ici MessageHandler, j'ai reçu REQUEST" + us.pseudo + received.getOldPseudo()) ;
				User me = UserManager.userAt(0) ;
				Message msg = new Message(null, me);
				
				if (UserManager.findUser(us.pseudo)==-1) {
					msg.setType(MessageType.AUTHORIZEPSEUDO);
				} else  {
					msg.setType(MessageType.DENYPSEUDO);
					
				}
	
				UDPClientThread client = new UDPClientThread(us.addrIp,msg,us.port);
				client.start();
				break;
	
			case AUTHORIZEPSEUDO :
				System.out.println("Pseudo OK du cote de " + us.pseudo);

				NetworkManager.receiveOk=true;
				break;
	
			case DENYPSEUDO :
				System.out.println("C'est non");

				NetworkManager.pseudoOk=false;
				break;
	
			case CONFIRMPSEUDO :
				System.out.println("Ici MessageHandler, j'ai reçu CONFIRM " + us.pseudo + received.getOldPseudo()) ;
				String oldP = received.getOldPseudo() ;
				if (!oldP.equals("a")) {
					UserManager.removeUser(oldP) ;
					System.out.println("Removed user : " + oldP) ;
				}
				UserManager.addUser(us.pseudo, us.addrIp, us.port);
				System.out.println("Added user : " + us.pseudo) ;
				break;
		
		
		}
	}
}
