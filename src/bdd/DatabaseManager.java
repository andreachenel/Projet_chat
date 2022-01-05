package bdd;

import java.sql.*;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import users.UserManager;

public class DatabaseManager {
	static Connection connection = null ;
	static Statement statement ;
	static ResultSet resultSet ;
	
	public ResultSet request(String req) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(req);
			System.out.println("login table :") ;
			while(resultSet.next())
			{
				// iterate & read the result set
				System.out.println("	id = " + resultSet.getString("id"));
				System.out.println("	password = " + resultSet.getString("password"));
				System.out.println("	currentPseudo = " + resultSet.getString("currentPseudo"));
			}
			statement.close();
		} catch (SQLException e){
			e.printStackTrace() ;
		}
		return resultSet ;
	}
	
	public void update(String upd) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(upd);
			statement.close();
		} catch (SQLException e){
			e.printStackTrace() ;
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
				if(connection == null) {
					connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_009","tp_servlet_009","GaiN6Oh5");
				}
				else {
					connection.close();
					connection = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/tp_servlet_009","tp_servlet_009","GaiN6Oh5");
				}

				statement = connection.createStatement();
				statement.setQueryTimeout(5);  // set timeout to  5sec.

				//statement.executeUpdate("DROP TABLE IF EXISTS login");
				//statement.executeUpdate("CREATE TABLE login (id varchar(100), password varchar(100), currentPseudo varchar(100))");

				//String ids [] = {"1","2","3","4","5"};
				//String passwords [] = {"haha","hoho","hehe","hihi","James Bond"};

				//for(int i=0;i<ids.length;i++){
				//	statement.executeUpdate("INSERT INTO login values(' "+ids[i]+"', '"+passwords[i]+"', 'a')");   
				//}

				//statement.executeUpdate("UPDATE login SET id = 'Peter' WHERE id = 1");
				//statement.executeUpdate("INSERT INTO login values(	'"+UserManager.getMyID()+"'	,	'mdp', 'Pierre'"); 
				statement.close();
			}

			catch(SQLException e){ e.printStackTrace(); }       
			printLoginTable() ;
		}
	}

	public void close () {      
		try {
			if(connection != null)
				connection.close();
		}
		catch(SQLException e) {         
			System.err.println(e); 
		}
	}
	
	public void printLoginTable () {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from login");
			System.out.println("login table :") ;
			while(resultSet.next())
			{
				// iterate & read the result set
				System.out.println("	id = " + resultSet.getString("id"));
				System.out.println("	password = " + resultSet.getString("password"));
				System.out.println("	currentPseudo = " + resultSet.getString("currentPseudo"));
			}
			statement.close();
		} catch (SQLException e){
			e.printStackTrace() ;
		}
	}
	
	
	
	public static void changePseudo (String pseudo) {
		String myID = UserManager.getMyID() ;
		if (myID!=null) {
			System.out.println("changing pseudo to "+pseudo);
			try {
				statement = connection.createStatement();
				String upd = "UPDATE login SET currentPseudo='"+pseudo+"' WHERE id='"+myID+"'" ;
				statement.executeUpdate(upd) ;
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}

	public static boolean verifyLogin(String id, String pwd) {
		boolean result = false;
		try {
			statement = connection.createStatement();
			String req = "SELECT id, password from login WHERE id='"+id+"'" ;
			resultSet = statement.executeQuery(req);
			
			// if ID already in table, check if password is right
			if (resultSet.next()) {
				System.out.println("resultset not empty");
				if (pwd.equals(resultSet.getString("password"))) {
					result=true;
					UserManager.setMyID(id);
				}
				
			// if ID not in table, create a new entry
			} else {
				statement.executeUpdate("INSERT INTO login values('"+id+"', '"+pwd+"', '')");  
				result=true ;
				UserManager.setMyID(id);
				System.out.println("New user ! entry created in login database");
			}
			statement.close();
		} catch (MySQLSyntaxErrorException e) {
		} catch (SQLException e) {
			System.err.println(e);
		} 

		return result;
	}

	public void printMessages () {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from messages");
			System.out.println("messages :") ;
			while(resultSet.next())
			{
				// iterate & read the result set
				System.out.println("	"+resultSet.getString("id1")+" -> "+resultSet.getString("id2")+" at "+resultSet.getString("time")+" : "+resultSet.getString("message"));
			}
			statement.close();
		} catch (SQLException e){
			e.printStackTrace() ;
		}
	}
	
	public ResultSet retrieveMessages (String id1, String id2) {
		resultSet = null ;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * from messages WHERE (id1='"+id1+"' AND id2='"+id2+"') OR (id1='"+id2+"' AND id2='"+id1+"')");
			System.out.println("Conversation between "+id1+" and "+id2+" :") ;
			while(resultSet.next())
			{
				// iterate & read the result set
				System.out.println("	"+resultSet.getString("id1")+" -> "+resultSet.getString("id2")+" at "+resultSet.getString("time")+" : "+resultSet.getString("message"));
			}
			statement.close();
		} catch (SQLException e){
			e.printStackTrace() ;
		}
		return resultSet ;
	}
	
	public void newMessage (String id1, String id2, String message) {
		String upd = "INSERT INTO messages values('"+id1+"','"+id2+"','"+message+"','1')" ;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(upd);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}