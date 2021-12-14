package network;

import java.net.SocketException;
import java.util.ArrayList;

public class UserManager {
	
	static ArrayList<User> UserTab = new ArrayList <User>();
	
	public UserManager(String myPseudo, int myPort) {
		this.addUser(myPseudo, NetworkManager.getLocalAddress(), myPort);
	}
	
	public static User findMe () {
		return UserTab.get(0) ;
	}
	
	public static void addUser (String pseudo, String addr, int port) {
		UserTab.add(new User(pseudo, addr, port));
	}
	
	public static void removeUser (String pseudo) {
		UserTab.remove(pseudo);
	}
	
	public static void setUserAt(int index, User us) {
		UserTab.set(index, us) ;
	}
	
	public static int findUser (String pseudo) {
		int result = -1;
		
		int i=0 ;
		boolean found = false ;
		while (!found && i<UserTab.size()) {
			if (pseudo.equals(UserTab.get(i).pseudo)) {
				result = i ;
				found= true ;
			}
			i++ ;
		}
		
		return result;
	}
	
	public static User userAt (int i) {
		return UserTab.get(i) ;
	}
	
	public static void printUserTab () {
	      for (int i = 0; i < UserTab.size();i++) 
	      { 		      
	          System.out.println(UserTab.get(i).pseudo); 		
	      } 
	}

}
