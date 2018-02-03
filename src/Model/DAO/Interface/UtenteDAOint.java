package Model.DAO.Interface;
import java.sql.SQLException;

import Model.*;

public interface UtenteDAOint {
	
	public int inserisciUser(Utente utente) throws SQLException;
	
	public Utente ricercaUser(String username) throws SQLException;
	
	public int aggiornaUser(Utente utente) throws SQLException;
	
}
