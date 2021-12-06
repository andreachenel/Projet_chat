package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	Connection connection = null ;
	static Statement statement;
	static ResultSet resultSet ;
	
	public void create() {
		{
			 // load the sqlite-JDBC driver using the current class loader
		      try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      try
		      { if(connection == null) {
		    	  
		         // create a database connection
		         connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		      }
		      else {
		    	  connection.close();
			      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		      }

		     	 statement = connection.createStatement();
		         statement.setQueryTimeout(5);  // set timeout to  5sec.


		         statement.executeUpdate("DROP TABLE IF EXISTS login");
		         statement.executeUpdate("CREATE TABLE login (id STRING, password STRING)");

		         String ids [] = {"1","2","3","4","5"};
		         String passwords [] = {"haha","hoho","hehe","hihi","James Bond"};

		         for(int i=0;i<ids.length;i++){
		              statement.executeUpdate("INSERT INTO login values(' "+ids[i]+"', '"+passwords[i]+"')");   
		         }

		         statement.executeUpdate("UPDATE login SET id='Peter' WHERE id='1'");
		         //statement.executeUpdate("DELETE FROM person WHERE id='1'");

		           resultSet = statement.executeQuery("SELECT * from login");
		           while(resultSet.next())
		           {
		              // iterate & read the result set
		              //System.out.println("id = " + resultSet.getString("id"));
		              //System.out.println("password = " + resultSet.getString("password"));
		              statement.close();
		           }
		          }
		      	  
		     catch(SQLException e){  System.err.println(e.getMessage()); }       
		      
		}
	}
	
	public void close () {      
	        try {
	              if(connection != null)
	                 connection.close();
	              }
	        catch(SQLException e) {  // Use SQLException class instead.          
	           System.err.println(e); 
	         }
	}
	
	public static boolean verifyLogin(String id, String pwd) {
		boolean result = false;
		try {
			String req = "SELECT id, password from login WHERE id='" +id+ "' " ;
			System.out.println(req) ;
			resultSet = statement.executeQuery(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
		
		try {
			if (pwd.equals(resultSet.getString("password"))) {
				result=true;
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
