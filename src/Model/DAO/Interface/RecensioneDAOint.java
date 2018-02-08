package Model.DAO.Interface;


import java.sql.SQLException;
import java.util.ArrayList;

import Model.Recensione;
import Model.DAO.Concrete.RecensioneDAO;


public interface RecensioneDAOint {
	
	public int inserisciRecensione(Recensione recensione) throws SQLException;
	
	public int approvaRecensione(String utente,int IDgioco) throws SQLException;
	
	public int modificaRecensione(Recensione recensione) throws SQLException;
	
	public ArrayList<Recensione> daApprovare( ) throws SQLException;
	
	
}
