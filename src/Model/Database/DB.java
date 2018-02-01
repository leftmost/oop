package Model.Database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DB{
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop","root","root");
		return conn;
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		System.out.println(DB.openConnection());
	}
}