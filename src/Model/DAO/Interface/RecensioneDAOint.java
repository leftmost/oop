package Model.DAO.Interface;


import java.sql.SQLException;

import Model.Recensione;
import Model.DAO.Concrete.RecensioneDAO;


public interface RecensioneDAOint {
	
	public int inserisciRecensione(Recensione recensione) throws SQLException;
	
	public int approvaRecensione(Recensione recensione) throws SQLException;
	
	public int modificaRecensione(Recensione recensione) throws SQLException;
	
	
	
}
