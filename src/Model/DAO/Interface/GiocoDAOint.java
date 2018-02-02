package Model.DAO.Interface;

import java.sql.SQLException;

import Model.Gioco;
import Model.DAO.Concrete.GiocoDAO;


public interface GiocoDAOint {
	
	public int insertGame(Gioco gioco) throws SQLException;
	
	public int deleteGame(Gioco gioco) throws SQLException;
	
	
	//++++++++++++++++++TEST++++++++++++++++++++++
	public static void main (String [] args) {
		
		GiocoDAOint a = new GiocoDAO();
		
		try {
			int ok=a.insertGame(new Gioco(2,"LOL",2));
			System.out.println(ok);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
	}

}
