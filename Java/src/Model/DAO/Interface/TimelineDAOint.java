package Model.DAO.Interface;

import java.sql.SQLException;
import Model.Timeline;
import Model.Utente;

public interface TimelineDAOint {

	/**
	 * @param username
	 * @return Timeline
	 * @throws SQLException
	 */
	public Timeline ricercaTimeline(String username) throws SQLException;

	/**
	 * @param username
	 * @param exp
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int aumentaExp(String username,int exp) throws SQLException;

	/**
	 * @param username
	 * @param exp
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int aumentaLiv(String username,int exp) throws SQLException;
	
	/**
	 * @param username
	 * @param exp
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int retrocediLiv(String username,int exp) throws SQLException;

	/**
	 * @param utente
	 * @param exp
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int aggiungiTimeline(Utente utente, int exp) throws SQLException;

}
