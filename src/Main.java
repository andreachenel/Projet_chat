import java.sql.SQLException;
import users.UserManager;
import network.*;
import threads.*;
import gui.*;
import bdd.*;

public class Main {
	
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		
		DatabaseManager DBM = new DatabaseManager() ;
		DBM.create() ;
		String id1="TestBot2" ;
		String id2="Pierre2" ;
		String message="bonsoir" ;
		String upd = "INSERT INTO messages values('"+id1+"','"+id2+"','"+message+"','1')" ;
		DBM.retrieveMessages(id1,id2) ;
		//InterfaceConnexion ic = new InterfaceConnexion() ;
		//ic.run() ;
		
//		DatabaseManager.verifyLogin("TestBot", "") ;
//		NetworkManager.requestPseudo("XxPierreDu31xX") ;
//		DBM.printLoginTable();
		DBM.printMessages();

		InterfaceConnexion connex = new InterfaceConnexion();
		connex.run();
		
		InterfaceDiscussion disc = new InterfaceDiscussion();
		disc.run();


	}	
}
