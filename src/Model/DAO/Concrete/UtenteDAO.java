package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Timeline;
import Model.Utente;
import Model.DAO.Interface.TimelineDAOint;
import Model.DAO.Interface.UtenteDAOint;
import Model.Database.Database;

public class UtenteDAO implements UtenteDAOint {
	
	private static final String
	INSERT = "INSERT INTO Utente(username, email, password,nome, cognome, tipologia) VALUES (?, ?, ?, ?, ?,?);";
	
	private static final String
	FIND_BY_USERNAME = "SELECT * FROM utente WHERE username = ?;";
	
	@Override
	public int insertUser(Utente utente) throws SQLException {
		 Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERT);
		    ps.setString(1, utente.getUsername());
		    ps.setString(2, utente.getEmail());
		    ps.setString(3, utente.getPassword());
		    ps.setString(4, utente.getNome());
		    ps.setString(5, utente.getNome());
		    ps.setString(6, utente.getTipologia());
		  
		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}


	@Override
	public Utente findUserbyUsername(String username) throws SQLException {
		
		 Utente utente=null;
		    Connection connection = Database.openConnection();  
		    PreparedStatement ps = connection.prepareStatement(FIND_BY_USERNAME);
		    ps.setString(1, username);
		    
		    ResultSet result = ps.executeQuery();
		    if ( result.first() == false ) {
		        return null;
		    }
		    
		    utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
		    TimelineDAOint timeline = new TimelineDAO();
		    Timeline usersTimeline = timeline.findTimelineByUsername(utente.getUsername());
		    utente.setTimeline(usersTimeline);
		    ps.close();
		    result.close();
		    connection.close();
		    return utente;
	}
	
	
	
	
}
