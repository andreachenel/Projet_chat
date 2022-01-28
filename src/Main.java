import java.sql.SQLException;

import data.DatabaseManager;
import gui.InterfaceManager;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DatabaseManager DBM = new DatabaseManager();
		DBM.create();

		InterfaceManager inter = new InterfaceManager();
		inter.run();

	}
}
