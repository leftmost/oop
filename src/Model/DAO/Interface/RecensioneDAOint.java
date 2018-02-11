package Model.DAO.Interface;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Recensione;

public interface RecensioneDAOint {
	
	/**
	 * @param recensione
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int inserisciRecensione(Recensione recensione) throws SQLException;
	
	/**
	 * @param utente
	 * @param IDgioco
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int approvaRecensione(String utente,int IDgioco) throws SQLException;
	
	/**
	 * @param recensione
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int modificaRecensione(Recensione recensione) throws SQLException;
	
	/**
	 * @return ArrayList di recensioni
	 * @throws SQLException
	 */
	public ArrayList<Recensione> daApprovare() throws SQLException;

	/**
	 * @param titolo
	 * @return ArrayListi di Recensioni
	 * @throws SQLException
	 */
	public ArrayList<Recensione> daApprovareGioco(String titolo) throws SQLException;
	
	/**
	 * @param titolo
	 * @return ArrayList di recensioni
	 * @throws SQLException
	 */
	public ArrayList<Recensione> approvateGioco(String titolo) throws SQLException;

	/**
	 * @param recensione
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int eliminaRecensioneUtente(Recensione recensione) throws SQLException;
}
