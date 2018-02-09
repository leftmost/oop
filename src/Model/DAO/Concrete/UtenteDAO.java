package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	AGG_ANAGRAFICA = "UPDATE utente SET Nome=?,Cognome=? WHERE username=?;";

	private static final String
	EMAIL_ESISTENTE = "SELECT * FROM utente WHERE email = ?;";

	private static final String
	USERNAME_ESISTENTE = "SELECT * FROM utente WHERE username = ?;";
	
	private static final String
	UTENTI_BASE = "SELECT * FROM Utente WHERE tipologia = 'Utente';";
	
	private static final String
	UTENTI_MODERATORI = "SELECT * FROM Utente WHERE tipologia != 'Admin'";
	
	private static final String
	NOMINA_MODERATORE = "UPDATE Utente SET tipologia='Moderatore' WHERE username=?;";
	
	private static final String
	RETROCEDI_MODERATORE = "UPDATE Utente SET tipologia='Utente' WHERE username=?;";
	
	


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


	
	//Modifica anagrafica utente
		@Override
		public int aggAnagraficaUser(Utente utente) throws SQLException {
			Connection connection = Database.openConnection();
			PreparedStatement ps = connection.prepareStatement(AGG_ANAGRAFICA);
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getUsername());

			int result = ps.executeUpdate();

			ps.close();
			connection.close();
			return result;
		}
		
	//controlla l'esistenza di una  email
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

	//controlla l'esistenza di un username
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

	//restituisce la lista di tutti gli utenti base
	@Override
	public ArrayList<Utente> listaUtentiBase() throws SQLException {
		ArrayList<Utente> utentiBase = new ArrayList<>();
		Connection connection = Database.openConnection();
		Statement s = connection.createStatement();
		ResultSet rset = s.executeQuery(UTENTI_BASE);
		while (rset.next()){
			
			Utente utente = new Utente(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6));

			TimelineDAOint timeline = new TimelineDAO();

			Timeline usersTimeline = timeline.ricercaTimeline(utente.getUsername());

			utente.setTimeline(usersTimeline);
			
			utentiBase.add(utente);
		}
		s.close();
		rset.close();
		connection.close();
		return utentiBase;
	}
	
	//restituisce la lista di tutti gli utenti base e moderatori
	@Override
	public ArrayList<Utente> listaUtentieModeratori() throws SQLException {
		ArrayList<Utente> utentiBase = new ArrayList<>();
		Connection connection = Database.openConnection();
		Statement s = connection.createStatement();
		ResultSet rset = s.executeQuery(UTENTI_MODERATORI);
		while (rset.next()){
			
			Utente utente = new Utente(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6));

			TimelineDAOint timeline = new TimelineDAO();

			Timeline usersTimeline = timeline.ricercaTimeline(utente.getUsername());

			utente.setTimeline(usersTimeline);
			
			utentiBase.add(utente);
		}
		s.close();
		rset.close();
		connection.close();
		return utentiBase;
	}
	
	
	//Nomina Moderatore
	@Override
	public int nominaModeratore(Utente utente) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(NOMINA_MODERATORE);
		ps.setString(1, utente.getUsername());
		

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}
	
	
	//Retrocedi Moderatore
	@Override
	public int retrocediModeratore(Utente utente) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(RETROCEDI_MODERATORE);
		ps.setString(1, utente.getUsername());
		

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}

}
