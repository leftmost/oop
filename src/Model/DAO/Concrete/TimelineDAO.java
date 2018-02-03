package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Esperienza;
import Model.Timeline;
import Model.Utente;
import Model.DAO.Interface.TimelineDAOint;
import Model.DAO.Interface.UtenteDAOint;
import Model.Database.Database;

public class TimelineDAO implements TimelineDAOint {

	private static final String
	RICERCA = "SELECT * FROM Timeline WHERE Utente_Username = ? ORDER BY Data DESC;";

	@Override
	public Timeline ricercaTimeline(String username) throws SQLException {
		Timeline timeline = new Timeline();
		Connection connection = Database.openConnection();  
		PreparedStatement ps = connection.prepareStatement(RICERCA);
		ps.setString(1, username);

		ResultSet result = ps.executeQuery();

		while(result.next()){
			timeline.add(new Esperienza(result.getInt("Livello"), result.getDate("Data"), result.getInt("Exp")));
		}
		if(result.first()==false) {timeline=null;}

		ps.close();
		result.close();
		connection.close();
		return timeline;
	}

}
