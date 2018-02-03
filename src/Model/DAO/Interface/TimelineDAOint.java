package Model.DAO.Interface;

import java.sql.SQLException;
import Model.Timeline;

public interface TimelineDAOint {

	public Timeline ricercaTimeline(String username) throws SQLException;
}
