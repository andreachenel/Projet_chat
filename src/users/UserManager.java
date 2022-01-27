package users;

import java.util.ArrayList;

public class UserManager {

	private static String myID = null;
	private static ArrayList<User> UserTab = new ArrayList<User>();

	public static ArrayList<String> pseudoTab() {
		ArrayList<String> pseudoTab = new ArrayList<String>();
		for (int i = 1; i < UserTab.size(); i++) {
			pseudoTab.add(UserTab.get(i).getPseudo());
		}
		return pseudoTab;
	}

	public static String getMyID() {
		return myID;
	}

	public static void setMyID(String myID) {
		UserManager.myID = myID;
	}

	public UserManager() {
		UserTab = new ArrayList<User>();
	}

	public static String userAddress(String us) {
		int index = findUser(us);
		if (index != -1) {
			return userAt(index).getAddrIp();
		} else
			return "";

	}

	public static String myPseudo() {
		String pseudo = "";
		try {
			pseudo = UserTab.get(0).getPseudo();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("usertab empty ! define mypseudo");
		}
		return pseudo;
	}

	public static void addUser(String pseudo, String addr, int port) {
		if (findUser(pseudo) == -1) {
			UserTab.add(new User(pseudo, addr, port));
		}
	}

	public static void addUser(User us) {
		if (findUser(us.getPseudo()) == -1) {
			UserTab.add(us);
		}
	}

	public static void remove(int index) {
		UserTab.remove(index);
	}

	public static void removeUserByPseudo(String pseudo) {
		int index = findUser(pseudo);
		UserTab.remove(index);
	}

	public static void insertUserAt(int index, String pseudo, String addr, int port) {
		UserTab.add(index, new User(pseudo, addr, port));
	}

	public static int findUser(String pseudo) {
		int result = -1;

		int i = 0;
		boolean found = false;
		while (!found && i < UserTab.size()) {
			if (pseudo.equals(UserTab.get(i).getPseudo())) {
				result = i;
				found = true;
			}
			i++;
		}

		return result;
	}

	public static int findUserByAddress(String address) {
		int result = -1;

		int i = 0;
		boolean found = false;
		while (!found && i < UserTab.size()) {
			if (address.equals(UserTab.get(i).getAddrIp())) {
				result = i;
				found = true;
			}
			i++;
		}

		return result;
	}

	public static User userAt(int i) {
		return UserTab.get(i);
	}

	public static void printUserTab() {
		System.out.print("- - UserTab : ");
		for (int i = 0; i < UserTab.size(); i++) {
			System.out.print(UserTab.get(i).getPseudo() + " ");
		}
		System.out.println();
	}

	public static ArrayList<User> getUserTab() {
		return UserTab;
	}

}
