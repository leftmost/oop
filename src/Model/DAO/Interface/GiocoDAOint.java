package Model.DAO.Interface;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Gioco;
import Model.Recensione;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;


public interface GiocoDAOint {
	
	public int insertGame(Gioco gioco) throws SQLException;
	
	public int deleteGame(Gioco gioco) throws SQLException;
	
	public Gioco findGameByTitle(String titolo) throws SQLException;
	
	public ArrayList<Gioco> allGames() throws SQLException;
	
	public ArrayList<Recensione> allGameReviews(Gioco gioco) throws SQLException;
	
	public int getVotesAverage(Gioco gioco) throws SQLException;
	
	
	//++++++++++++++++++TEST++++++++++++++++++++++
	public static void main (String [] args) throws SQLException {
		
		GiocoDAOint a = new GiocoDAO();
		
		//   1 - inserimento
		
//		try {
//			int ok=a.insertGame(new Gioco(2,"LOL",2));
//			System.out.println(ok);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
		
		
		//   2 - rimozione per id
		
//		try {
//			int ok=a.deleteGame(new Gioco(1,"GTA",3));
//			System.out.println(ok);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
		
		
		//   3 - ricerca per titolo
//		
//		Gioco ricerca = a.findGameByTitle("GTA");
//		System.out.println(ricerca);
		
		
		//   4 - lista di giochi
//		
//		ArrayList<Gioco> tutti = a.allGames();
//		System.out.println(tutti);
		
		
		//   5 - recensioni di un gioco per id
		
//		ArrayList<Recensione> rec = a.allGameReviews(new Gioco(1,"GTA",3));
//		System.out.println(rec);
		
		
		//   6 - valutazione per id
//		
//		try {
//			int ok=a.getVotesAverage(new Gioco(1,"GTA", 3));
//			System.out.println(ok);
//		} catch (SQLException e){
//			e.printStackTrace();
//		}
//		
		
		
	}

}
