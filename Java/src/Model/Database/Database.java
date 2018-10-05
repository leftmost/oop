package Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
*Class which has the aim to enstablish a connection with the database
*/
public class Database{
	
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
