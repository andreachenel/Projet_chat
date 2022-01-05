package bdd;

import java.sql.*;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class DatabaseManager {
	static Connection connection = null ;
	static Statement statement ;
	static ResultSet resultSet ;

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
				System.out.println("	pseudo = " + resultSet.getString("pseudo"));
			}
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

				statement.executeUpdate("DROP TABLE IF EXISTS login");
				statement.executeUpdate("CREATE TABLE login (id varchar(100), password varchar(100), pseudo varchar(100))");

				String ids [] = {"1","2","3","4","5"};
				String passwords [] = {"haha","hoho","hehe","hihi","James Bond"};
				String pseudos [];

				for(int i=0;i<ids.length;i++){
					statement.executeUpdate("INSERT INTO login values(' "+ids[i]+"', '"+passwords[i]+"')");   
				}

				statement.executeUpdate("UPDATE login SET id='Peter' WHERE id='1'");
				//statement.executeUpdate("DELETE FROM person WHERE id='1'");
				statement.close();
			}

			catch(SQLException e){  System.err.println(e.getMessage()); }       
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

	public static boolean verifyLogin(String id, String pwd) {
		boolean result = false;
		try {
			statement = connection.createStatement();
			String req = "SELECT id, password from login WHERE id="+id ;
			resultSet = statement.executeQuery(req);
			if (resultSet.next()) {
				if (pwd.equals(resultSet.getString("password"))) {
					result=true;
				}
			}
			statement.close();
		} catch (MySQLSyntaxErrorException e) {
		} catch (SQLException e) {
			System.err.println(e);
		} 
		
		return result;
	}

	public static boolean checkID(String ID) {
		
		
		return false;
	}

}