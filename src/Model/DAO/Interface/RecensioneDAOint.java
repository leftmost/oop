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

	public ArrayList<Recensione> daApprovareGioco(String titolo) throws SQLException;
	
	public static void main (String [] args) throws SQLException {
		RecensioneDAOint recensione = new RecensioneDAO();
		System.out.println(recensione.daApprovareGioco("Solitario"));
	}

	public ArrayList<Recensione> approvateGioco(String titolo) throws SQLException;

	public int eliminaRecensioneUtente(Recensione recensione) throws SQLException;
}
