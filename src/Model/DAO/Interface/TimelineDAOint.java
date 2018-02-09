package Model.DAO.Interface;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Esperienza;
import Model.Timeline;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Concrete.TimelineDAO;

public interface TimelineDAOint {

	public Timeline ricercaTimeline(String username) throws SQLException;

	public int aumentaExp(String username,int exp) throws SQLException;

	public int aumentaLiv(String username,int exp) throws SQLException;
	
	public int retrocediLiv(String username,int exp) throws SQLException;

	public int aggiornaTimeline(Utente utente) throws SQLException;

	//++++++++++++TEST+++++++++

	public static void main(String [] args) {

		TimelineDAOint timeline = new TimelineDAO();
		try {
			timeline.aumentaLiv("giulia",10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
