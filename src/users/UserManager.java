package users;

import java.util.ArrayList;

import network.NetworkManager;

public class UserManager {

	private static ArrayList<User> UserTab =new ArrayList<User>() ;

	public UserManager(String myPseudo, int myPort) {
		UserTab = new ArrayList <User>();
		addUser(myPseudo, NetworkManager.getLocalAddress(), myPort);
	}

	public UserManager() {
		UserTab = new ArrayList <User>();
	}

	public static User findMe () {
		return UserTab.get(0) ;
	}

	public static void addUser (String pseudo, String addr, int port) {
		if (findUser(pseudo)!=-1) {
			UserTab.add(new User(pseudo, addr, port));
		}
	}
	
	public static void addUser (User us) {
		if (findUser(us.pseudo)!=-1) {
			UserTab.add(us);
		}
	}

	public static void remove(int index) {
		UserTab.remove(index);
	}

	public static void removeUserByPseudo (String pseudo) {
		int index = findUser(pseudo) ;
		UserTab.remove(index);
	}

	public static void insertUserAt(int index, String pseudo, String addr, int port) {
		UserTab.add(index,new User(pseudo, addr, port));
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

	public static ArrayList<User> getUserTab() {
		return UserTab;
	}

}
