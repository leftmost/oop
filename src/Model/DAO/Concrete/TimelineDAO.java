package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Model.Esperienza;
import Model.Timeline;
import Model.Utente;
import Model.DAO.Interface.TimelineDAOint;
import Model.Database.Database;

public class TimelineDAO implements TimelineDAOint {

	private static final String
	RICERCA = "SELECT * FROM Timeline WHERE Utente_Username = ? ORDER BY Data DESC;";
	
	private static final String
	AUM_EXP = "UPDATE timeline SET exp=exp+10 WHERE utente_username=? AND Data=DATE(NOW());";

	private static final String
	AUM_LIV = "UPDATE timeline SET exp=exp+100 WHERE utente_username=? AND Data=(SELECT MAX(Data));";
	
	private static final String
	RET_LIV = "UPDATE timeline SET exp=exp-? WHERE utente_username=? AND Data=(SELECT MAX(Data));";

	private static final String
	INSERT = "INSERT INTO Timeline (utente_username, data, exp) VALUES (?,Date(NOW()),?);";


	//ricerca Timeline
	@Override
	public Timeline ricercaTimeline(String username) throws SQLException {
		Timeline timeline = new Timeline();
		Connection connection = Database.openConnection();  
		PreparedStatement ps = connection.prepareStatement(RICERCA);
		ps.setString(1, username);

		ResultSet result = ps.executeQuery();

		while(result.next()){
			timeline.add(new Esperienza(result.getDate("Data"), result.getInt("Exp")));
		}
		if(result.first()==false) {timeline=null;}

		ps.close();
		result.close();
		connection.close();
		return timeline;
	}
	
	
	//aumenta l'esperienza dell'user
	@Override
	public int aumentaExp(String username) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(AUM_EXP);
		ps.setString(1, username);
		

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}
	
	//aumenta l'esperienza dell'user
		@Override
		public int aumentaLiv(String username) throws SQLException {
			Connection connection = Database.openConnection();
			PreparedStatement ps = connection.prepareStatement(AUM_LIV);
			ps.setString(1, username);
			ps.setString(1, username);
			

			int result = ps.executeUpdate();

			ps.close();
			connection.close();
			return result;
		}
	
	
	//Inserisce una nuova riga nella tabella della Timeline
	@Override
	public int aggiornaTimeline(Utente utente) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT);
		ps.setString(1, utente.getUsername());
		ps.setInt(2, utente.getEsperienza()+10);

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}


	@Override
	public int retrocediLiv(String username, int exp) throws SQLException {
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(RET_LIV);
		ps.setInt(1, exp);
		ps.setString(2, username);
		

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}

}
