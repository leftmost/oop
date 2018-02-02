package Model.DAO.Interface;

import java.sql.SQLException;

import Model.Timeline;
import Model.Utente;

public interface TimelineDAOint {

	public Timeline findTimelineByUsername(String username) throws SQLException;
}
