package Model.DAO.Interface;

import java.sql.SQLException;
import java.util.Date;

import Model.Esperienza;
import Model.Timeline;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Concrete.TimelineDAO;

public interface TimelineDAOint {

	public Timeline ricercaTimeline(String username) throws SQLException;
	
	public int aumentaExp(String username, Date data) throws SQLException;
	
	public int aggiornaTimeline(Utente utente) throws SQLException;
	
	//++++++++++++TEST+++++++++
	
	public static void main(String [] args) {
		
//		//   1 - inserimento
//		TimelineDAOint a = new TimelineDAO();
//		Timeline t = new Timeline();
//		t.add(new Esperienza(0, null, 0));
//	
//		Utente utente = new Utente("andcant", "", "", "", "", "");
//		utente.setTimeline(t);
//		try {
//			int ok=a.aggiornaTimeline(utente);
//			System.out.println(ok);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
	
	
//  2 - exp
//		TimelineDAOint a = new TimelineDAO();
//	
//		
//		try {
//			int ok=a.aumentaExp("andcant");
//			System.out.println(ok);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
}
	
}
