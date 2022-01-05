package users;

import java.util.ArrayList;

import network.NetworkManager;

public class UserManager {

	private static ArrayList<User> UserTab = new ArrayList<User>() ;
	
	
	public static ArrayList<String> pseudoTab() {
		ArrayList<String> pseudoTab = new ArrayList<String>() ;
		for (User u : UserTab ) {
			pseudoTab.add(u.pseudo);
			}
		return pseudoTab;
	}
	

	public UserManager(String myPseudo, int myPort) {
		UserTab = new ArrayList <User>();
		addUser(myPseudo, NetworkManager.getLocalAddress(), myPort);
	}

	public UserManager() {
		UserTab = new ArrayList <User>();
	}

	public static User findMe () {
		return getUserTab().get(0) ;
	}

	public static void addUser (String pseudo, String addr, int port) {
		getUserTab().add(new User(pseudo, addr, port));
	}

	public static void remove(int index) {
		getUserTab().remove(index);
	}

	public static void removeUserByPseudo (String pseudo) {
		int index = findUser(pseudo) ;
		getUserTab().remove(index);
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

	public static int findUserByAddress (String address) {
		int result = -1;

		int i=0 ;
		boolean found = false ;
		while (!found && i<UserTab.size()) {
			if (address.equals(UserTab.get(i).addrIp)) {
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

	public static int length() {
		return UserTab.size();
	}

	public static void printUserTab () {
		System.out.print("- - UserTab : ") ;
		for (int i = 0; i < UserTab.size();i++) 
		{ 		      
			System.out.print(UserTab.get(i).pseudo+" "); 		
		} 
		System.out.println() ;
	}

	public static void changePort(String pseudo, int port) {
		int index = findUser(pseudo) ;
		UserTab.get(index).port=port ;
	}
	

}
