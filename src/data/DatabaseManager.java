package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class DatabaseManager {
	static Connection connection = null;
	static Statement statement;
	static ResultSet resultSet;

	public ResultSet request(String req) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(req);

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
				statement.setQueryTimeout(3); // set timeout to 5sec.

				statement.close();
			}

			catch (SQLException e) {

				System.out.println("Can't reach database ! Check connection");
				System.exit(1);
			}
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

	public static int verifyLogin(String id, String pwd) {
		int result = -1 ;
		try {
			statement = connection.createStatement();
			String req = "SELECT id, password from login WHERE id='" + id + "'";
			resultSet = statement.executeQuery(req);

			// if ID already in table, check if password is right
			if (resultSet.next()) {
				if (pwd.equals(resultSet.getString("password"))) {
					result = 0;
					UserManager.setMyID(id);
				}

				// if ID not in table, create a new entry
			} else {
				statement.executeUpdate("INSERT INTO login values('" + id + "', '" + pwd + "', '')");
				result = -2 ;
				UserManager.setMyID(id);
				System.out.println("New user ! entry created in login database");
			}
		} catch (MySQLSyntaxErrorException e) {
		} catch (SQLException e) {
			System.err.println(e);
		}

		return result;
	}

	public static ResultSet retrieveMessages(String pseudo1, String pseudo2) {
		String id1 = getId(pseudo1);
		String id2 = getId(pseudo2);
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from messages WHERE (id1='" + id1 + "' AND id2='" + id2
					+ "') OR (id1='" + id2 + "' AND id2='" + id1 + "') ORDER BY time ASC");

			/*
			 * 
			 * }
			 */

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

}