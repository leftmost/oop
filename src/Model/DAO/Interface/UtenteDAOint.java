package Model.DAO.Interface;
import java.sql.SQLException;

import Model.*;
import Model.DAO.Concrete.UtenteDAO;

public interface UtenteDAOint {
	
	public int inserisciUser(Utente utente) throws SQLException;
	
	public Utente ricercaUser(String username) throws SQLException;
	
	public int aggTipologiaUser(Utente utente) throws SQLException;
	
	public int aggAnagraficaUser(Utente utente) throws SQLException;
	
	public boolean emailEsistente(String email) throws SQLException;

	public boolean usernameEsistente(String username) throws SQLException;
	
	//TEST
	public static void main(String[] args){
		UtenteDAOint user = new UtenteDAO();
		
		try {
			user.aggTipologiaUser(new Utente("loreand", null, null, null, null, "Users"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
