package Model.DAO.Interface;
import java.sql.SQLException;

import Model.*;

public interface UtenteDAOint {
	
	public int insertUser(Utente utente) throws SQLException;
	
	public Utente findUserbyUsername(String username) throws SQLException;
	
}
