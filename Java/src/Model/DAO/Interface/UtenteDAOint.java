package Model.DAO.Interface;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.*;

public interface UtenteDAOint {
	
	/**
	 * @param utente
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int inserisciUser(Utente utente) throws SQLException;
	
	/**
	 * @param username
	 * @return Utente 
	 * @throws SQLException
	 */
	public Utente ricercaUser(String username) throws SQLException;
	
	/**
	 * @param utente
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int aggAnagraficaUser(Utente utente) throws SQLException;
	
	/**
	 * @param email
	 * @return true email esistente
	 * @throws SQLException
	 */
	public boolean emailEsistente(String email) throws SQLException;

	/**
	 * @param username
	 * @return true username esistente
	 * @throws SQLException
	 */
	public boolean usernameEsistente(String username) throws SQLException;
	
	/**
	 * @return ArrayList di utente
	 * @throws SQLException
	 */
	public ArrayList<Utente> listaUtentiBase() throws SQLException;
	
	/**
	 * @return ArrayList di Utenti
	 * @throws SQLException
	 */
	public ArrayList<Utente> listaUtentieModeratori() throws SQLException;
	
	/**
	 * @param utente
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int nominaModeratore(Utente utente) throws SQLException;
	
	/**
	 * @param utente
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int retrocediModeratore(Utente utente) throws SQLException;
	
	/**
	 * @param utente
	 * @return 1 esecuzione query corretta
	 * @throws SQLException
	 */
	public int rimozioneUser(Utente utente) throws SQLException;
	
}
