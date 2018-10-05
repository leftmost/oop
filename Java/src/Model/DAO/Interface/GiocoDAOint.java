package Model.DAO.Interface;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.Gioco;
import Model.Recensione;

/**
 * @author lorenzo
 *
 */
public interface GiocoDAOint {
	
	/**
	 * Metodo per inserimento gioco nel db
	 * @param gioco
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int inserisciGioco(Gioco gioco) throws SQLException;
	
	/**
	 * Metodo per eliminazione gioco dal db
	 * @param gioco
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int eliminaGioco(Gioco gioco) throws SQLException;
	
	/**
	 * Metodo di ricerca di un gioco nel db
	 * @param titolo
	 * @return Gioco
	 * @throws SQLException
	 */
	public Gioco ricercaGioco(String titolo) throws SQLException;
	
	/**
	 * @return ArraList di Giochi
	 * @throws SQLException
	 */
	public ArrayList<Gioco> tuttiGiochi() throws SQLException;
	
	/**
	 * @param gioco
	 * @return ArrayList di recensioni
	 * @throws SQLException
	 */
	public ArrayList<Recensione> recensioniGioco(Gioco gioco) throws SQLException;
	
	/**
	 * @param gioco
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int voto(Gioco gioco) throws SQLException;

	/**Metodo ricerca gioco da id
	 * @param id
	 * @return Gioco
	 * @throws SQLException
	 */
	public Gioco ricercaGiocoID(String id) throws SQLException;

}
