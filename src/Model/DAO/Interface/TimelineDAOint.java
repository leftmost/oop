package Model.DAO.Interface;

import java.sql.SQLException;
import Model.Timeline;

public interface TimelineDAOint {

	public Timeline findTimelineByUsername(String username) throws SQLException;
}
