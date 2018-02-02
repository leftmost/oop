package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Gioco;
import Model.DAO.Interface.GiocoDAOint;
import Model.Database.Database;

public class GiocoDAO implements GiocoDAOint{
	
	private static final String
	INSERT = "INSERT INTO Gioco(id, titolo, exp) VALUES (?, ?, ?);";
	
	private static final String
	DELETE = "DELETE FROM gioco WHERE id = ?;";
	
	//inserimento nuovo gioco
	public int insertGame(Gioco gioco) throws SQLException {
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERT);
		    ps.setInt(1, gioco.getId());
		    ps.setString(2, gioco.getTitolo());
		    ps.setInt(3, gioco.getExp());

		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
	
	//eliminazione gioco tramite id
	public int deleteGame(Gioco gioco) throws SQLException{
		 	Connection connection = Database.openConnection();
		 	PreparedStatement ps = connection.prepareStatement(DELETE);
		 	ps.setInt(1, gioco.getId());
		 	
		 	int result = ps.executeUpdate();
 
		    ps.close();
		    connection.close();
		    return result;
	 }
}
