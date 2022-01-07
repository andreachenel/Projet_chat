package bdd;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import users.UserManager;

public class DatabaseManager {
	static Connection connection = null;
	static Statement statement;
	static ResultSet resultSet;

	public ResultSet request(String req) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(req);
			/*
			 * System.out.println("login table :") ; while(resultSet.next()) { // iterate &
			 * read the result set System.out.println("	id = " + resultSet.getString("id"));
			 * System.out.println("	password = " + resultSet.getString("password"));
			 * 
			 * System.out.println("	currentPseudo = " +
			 * resultSet.getString("currentPseudo")); }
			 */
			// statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public void update(String upd) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(upd);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create() {
		{
			// load the mysql JDBC driver using the current class loader
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			try {

				// create a database connection
				if (connection == null) {
					connection = DriverManager.getConnection(
							"jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_009", "tp_servlet_009",
							"GaiN6Oh5");
				} else {
					connection.close();
					connection = DriverManager.getConnection(
							"jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_009", "tp_servlet_009",
							"GaiN6Oh5");
				}

				statement = connection.createStatement();
				statement.setQueryTimeout(5); // set timeout to 5sec.

				// String ids [] = {"1","2","3","4","5"};
				// String passwords [] = {"haha","hoho","hehe","hihi","James Bond"};

				// for(int i=0;i<ids.length;i++){
				// statement.executeUpdate("INSERT INTO login values(' "+ids[i]+"',
				// '"+passwords[i]+"', 'a')");
				// }

				// statement.executeUpdate("UPDATE login SET id = 'Peter' WHERE id = 1");
				// statement.executeUpdate("INSERT INTO login values(
				// '"+UserManager.getMyID()+"' , 'mdp', 'Pierre'");
				statement.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
			}
			printLoginTable();
		}
	}

	public void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void printLoginTable() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from login");
			System.out.println("login table :");
			while (resultSet.next()) {
				// iterate & read the result set
				System.out.println("	id = " + resultSet.getString("id"));
				System.out.println("	password = " + resultSet.getString("password"));
				System.out.println("	currentPseudo = " + resultSet.getString("currentPseudo"));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void changePseudo(String pseudo) {
		String myID = UserManager.getMyID();
		if (myID != null) {
			System.out.println("changing pseudo to " + pseudo);
			try {
				statement = connection.createStatement();
				String upd = "UPDATE login SET currentPseudo='" + pseudo + "' WHERE id='" + myID + "'";
				statement.executeUpdate(upd);
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("myID is null ! please connect first");
		}
	}

	public static boolean verifyLogin(String id, String pwd) {
		boolean result = false;
		try {
			statement = connection.createStatement();
			String req = "SELECT id, password from login WHERE id='" + id + "'";
			resultSet = statement.executeQuery(req);

			// if ID already in table, check if password is right
			if (resultSet.next()) {
				System.out.println("resultset not empty");
				if (pwd.equals(resultSet.getString("password"))) {
					result = true;
					UserManager.setMyID(id);
				}

				// if ID not in table, create a new entry
			} else {
				statement.executeUpdate("INSERT INTO login values('" + id + "', '" + pwd + "', '')");
				result = true;
				UserManager.setMyID(id);
				System.out.println("New user ! entry created in login database");
			}
		} catch (MySQLSyntaxErrorException e) {
		} catch (SQLException e) {
			System.err.println(e);
		}

		return result;
	}

	public void printMessages() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from messages ORDER BY time ASC");
			System.out.println("messages :");
			while (resultSet.next()) {
				System.out.println("	" + resultSet.getString("id1") + " -> " + resultSet.getString("id2") + " at "
						+ resultSet.getString("time") + " : " + resultSet.getString("message"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet retrieveMessages(String id1, String id2) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from messages WHERE (id1='" + id1 + "' AND id2='" + id2
					+ "') OR (id1='" + id2 + "' AND id2='" + id1 + "') ORDER BY time ASC");
			// statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public static String getId(String pseudo) {
		String id = "";
		String query = "SELECT id from login WHERE currentPseudo='" + pseudo + "'";
		try {
			statement = connection.createStatement();
			// retrieve id corresponding to pseudo
			if (resultSet != null) {
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					id = resultSet.getString("id");
				}
				System.out.println("found id1 " + id + " for pseudo" + pseudo);
			} else {
				System.out.println("error : Pseudo " + pseudo + " not found in database");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static void newMessage(String pseudo1, String pseudo2, String message) {

		try {
			String id1 = getId(pseudo1);
			String id2 = getId(pseudo2);

			// retrieve date
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			// execute the update
			String upd = "INSERT INTO messages values('" + id1 + "','" + id2 + "','" + message + "','" + dtf.format(now)
					+ "')";
			statement = connection.createStatement();
			statement.executeUpdate(upd);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkID(String ID) {

		return false;
	}

}