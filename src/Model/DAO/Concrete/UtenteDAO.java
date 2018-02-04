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
	INSERIMENTO = "INSERT INTO Utente(username, email, password,nome, cognome, tipologia) VALUES (?, ?, ?, ?, ?,?);";

	private static final String
	RICERCA = "SELECT * FROM utente WHERE username = ?;";

	private static final String
	AGGIORNAMENTO = "UPDATE utente SET tipologia=? ;";

	private static final String
	EMAIL_ESISTENTE = "SELECT * FROM utente WHERE email = ?;";

	private static final String
	USERNAME_ESISTENTE = "SELECT * FROM utente WHERE username = ?;";


	//inserisce un nuovo User
	@Override
	public int inserisciUser(Utente utente) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(INSERIMENTO);
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

	//ricerca un User 
	@Override
	public Utente ricercaUser(String username) throws SQLException {

		Utente utente=null;
		Connection connection = Database.openConnection();  
		PreparedStatement ps = connection.prepareStatement(RICERCA);
		ps.setString(1, username);

		ResultSet result = ps.executeQuery();

		if ( result.first() == false ) {
			utente=null;
		}else {

			utente = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));

			TimelineDAOint timeline = new TimelineDAO();

			Timeline usersTimeline = timeline.ricercaTimeline(utente.getUsername());

			utente.setTimeline(usersTimeline);

		}
		ps.close();
		result.close();
		connection.close();
		return utente;
	}


	//promuove o retrocede un User
	@Override
	public int aggiornaUser(Utente utente) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(AGGIORNAMENTO);
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
	public boolean emailEsistente(String email) throws SQLException {
		boolean emailEsistente;

		Connection connection = Database.openConnection();  
		PreparedStatement ps = connection.prepareStatement(EMAIL_ESISTENTE);
		ps.setString(1, email);

		ResultSet result = ps.executeQuery();
		if ( result.first() == false ) {
			emailEsistente=false;
		}else {
			emailEsistente=true;
		}

		ps.close();
		result.close();
		connection.close();
		return emailEsistente;
	}

	@Override
	public boolean usernameEsistente(String username) throws SQLException {
		boolean usernameEsistente;

		Connection connection = Database.openConnection();  
		PreparedStatement ps = connection.prepareStatement(USERNAME_ESISTENTE);
		ps.setString(1, username);

		ResultSet result = ps.executeQuery();
		if ( result.first() == false ) {
			usernameEsistente=false;
		}else {
			usernameEsistente=true;
		}

		ps.close();
		result.close();
		connection.close();
		return usernameEsistente;
	}






}
