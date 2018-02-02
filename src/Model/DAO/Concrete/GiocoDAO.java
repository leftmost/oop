package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;



import Model.Gioco;
import Model.Recensione;
import Model.DAO.Interface.GiocoDAOint;
import Model.Database.Database;

public class GiocoDAO implements GiocoDAOint{
	
	private static final String
	INSERT = "INSERT INTO Gioco(id, titolo, exp) VALUES (?, ?, ?);";
	
	private static final String
	DELETE = "DELETE FROM gioco WHERE id = ?;";
	
	private static final String
	FIND_BY_TITLE = "SELECT * FROM gioco WHERE titolo = ?;";
	
	private static final String
	ALL = "SELECT * FROM gioco ORDER BY titolo;";
	
	private static final String
	REVIEWS = "SELECT * FROM recensione WHERE recensione.gioco_id = ? AND recensione.approvazione = 1;";
	
	private static final String
	VOTES= "SELECT valutazione FROM gioco WHERE id = ?;";
	
	//inserimento nuovo gioco
	public int insertGame(Gioco gioco) throws SQLException {
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERT);
		    ps.setInt(1, gioco.getId());
		    ps.setString(2, gioco.getTitolo());
		    ps.setInt(3, gioco.getExp());

		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
	
	//eliminazione gioco tramite id
	public int deleteGame(Gioco gioco) throws SQLException{
		 	Connection connection = Database.openConnection();
		 	PreparedStatement ps = connection.prepareStatement(DELETE);
		 	ps.setInt(1, gioco.getId());
		 	
		 	int result = ps.executeUpdate();
 
		    ps.close();
		    connection.close();
		    return result;
	 }
	
	//ricerca gioco tramite titolo
	public Gioco findGameByTitle(String titolo) throws SQLException {
		
		 	Gioco gioco;
		    Connection connection = Database.openConnection();  
		    PreparedStatement ps = connection.prepareStatement(FIND_BY_TITLE);
		    ps.setString(1, titolo);
		    
		    ResultSet result = ps.executeQuery();
		    if ( result.first() == false ) {
		        return null;
		    }
		    
		    gioco = new Gioco(result.getInt(1), result.getString(2), result.getInt(3));
		    ps.close();
		    result.close();
		    connection.close();
		    return gioco;
	}
	
	//restituisce tutti i giochi ordinati in ordine alfabetico
	public ArrayList<Gioco> allGames() throws SQLException{
		
		    ArrayList<Gioco> all_games = new ArrayList<>();
		    Connection connection = Database.openConnection();
		    Statement s = connection.createStatement();
		    ResultSet rset = s.executeQuery(ALL);
		    while (rset.next()){
		      Gioco gioco = new Gioco(rset.getInt(1), rset.getString(2), rset.getInt(3));
		      all_games.add(gioco);
		    }
		    s.close();
		    rset.close();
		    connection.close();
		    return all_games;
	  }
	
	//restituisce tutte le recensioni del gioco
	public ArrayList<Recensione> allGameReviews(Gioco gioco) throws SQLException{
		
		    ArrayList<Recensione> game_reviews = new ArrayList<>();
		    Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(REVIEWS);
		    ps.setInt(1, gioco.getId());
		    ResultSet rset = ps.executeQuery();
		    while (rset.next()){
		      Recensione recensione = new Recensione(rset.getString(1), rset.getInt(2), rset.getBoolean(3), rset.getInt(4), rset.getString(5));
		      game_reviews.add(recensione);
		    }
		    ps.close();
		    rset.close();
		    connection.close();
		    return game_reviews;
	  }
	
	//restituisce il voto medio del gioco
	public int getVotesAverage(Gioco gioco) throws SQLException{
		
		    int votes_avarage;
		    Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(VOTES);
		    ps.setInt(1, gioco.getId());
		    ResultSet rset = ps.executeQuery();
		    rset.first();
		    votes_avarage = rset.getInt(1);
		    ps.close();
		    rset.close();
		    connection.close();
		    return votes_avarage;
		    
	  }


}
