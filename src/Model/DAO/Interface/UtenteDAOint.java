package Model.DAO.Interface;
import java.sql.SQLException;

import Model.*;
import Model.DAO.Concrete.UtenteDAO;

public interface UtenteDAOint {
	
	public int inserisciUser(Utente utente) throws SQLException;
	
	public Utente ricercaUser(String username) throws SQLException;
	
	public int aggiornaUser(Utente utente) throws SQLException;
	
	public boolean emailEsistente(String email) throws SQLException;

	public boolean usernameEsistente(String username) throws SQLException;
	
	//TEST
	public static void main(String[] args){
		UtenteDAOint i = new UtenteDAO();
		
		try {
			System.out.println(i.emailEsistente("andreoli@univaq.it"));
			System.out.println(i.usernameEsistente("loreadnD"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
