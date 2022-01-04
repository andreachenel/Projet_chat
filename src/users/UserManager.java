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
		getUserTab().set(index, us) ;
	}

	public static int findUser (String pseudo) {
		int result = -1;

		int i=0 ;
		boolean found = false ;
		while (!found && i<getUserTab().size()) {
			if (pseudo.equals(getUserTab().get(i).pseudo)) {
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
		while (!found && i<getUserTab().size()) {
			if (address.equals(getUserTab().get(i).addrIp)) {
				result = i ;
				found= true ;
			}
			i++ ;
		}

		return result;
	}

	public static User userAt (int i) {
		return getUserTab().get(i) ;
	}

	public static int length() {
		return getUserTab().size();
	}

	public static void printUserTab () {
		System.out.print("- - UserTab : ") ;
		for (int i = 0; i < getUserTab().size();i++) 
		{ 		      
			System.out.print(getUserTab().get(i).pseudo+" "); 		
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
