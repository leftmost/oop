package Model.DAO.Interface;
import java.sql.SQLException;


import Model.*;
import Model.DAO.Concrete.UtenteDAO;

public interface UtenteDAOint {
	
	public int insertUser(Utente utente) throws SQLException;
	
	public Utente findUserbyUsername(String username) throws SQLException;
	
	//TEST OBJECT
	public static void main(String[] args) throws SQLException{

		UtenteDAOint i=new UtenteDAO();

		//Creazione user
//		try {
//			int ok=i.insertUser(new Utente("ok1", "ok1", "ok", "ok", "ok", "ok"));
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		//ricerca user
		Utente ricerca = i.findUserbyUsername("kij");
		System.out.println(ricerca);
	}
}
