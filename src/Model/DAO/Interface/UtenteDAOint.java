package Model.DAO.Interface;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.*;
import Model.DAO.Concrete.UtenteDAO;

public interface UtenteDAOint {
	
	public int inserisciUser(Utente utente) throws SQLException;
	
	public Utente ricercaUser(String username) throws SQLException;
	
	public int aggAnagraficaUser(Utente utente) throws SQLException;
	
	public boolean emailEsistente(String email) throws SQLException;

	public boolean usernameEsistente(String username) throws SQLException;
	
	public ArrayList<Utente> listaUtentiBase() throws SQLException;
	
	public ArrayList<Utente> listaUtentieModeratori() throws SQLException;
	
	public int nominaModeratore(Utente utente) throws SQLException;
	
	public int retrocediModeratore(Utente utente) throws SQLException;
	
	//TEST
	public static void main(String[] args){
		UtenteDAOint user = new UtenteDAO();
		
		try {
System.out.println(user.listaUtentiBase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
