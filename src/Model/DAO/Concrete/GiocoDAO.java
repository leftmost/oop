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
	INSERIMENTO = "INSERT INTO Gioco(id, titolo) VALUES (?, ?);";
	
	private static final String
	ELIMINA = "DELETE FROM gioco WHERE id = ?;";
	
	private static final String
	RICERCA = "SELECT * FROM gioco WHERE titolo = ?;";
	
	private static final String
	TUTTI = "SELECT * FROM gioco ORDER BY titolo;";
	
	private static final String
	RECENSIONI = "SELECT * FROM recensione WHERE recensione.gioco_id = ? AND recensione.approvazione = 1;";
	
	private static final String
	VOTO= "SELECT valutazione FROM gioco WHERE id = ?;";
	
	//inserimento nuovo gioco
	@Override
	public int inserisciGioco(Gioco gioco) throws SQLException {
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERIMENTO);
		    ps.setInt(1, gioco.getId());
		    ps.setString(2, gioco.getTitolo());
		    

		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
	
	//eliminazione gioco tramite id
	@Override
	public int eliminaGioco(Gioco gioco) throws SQLException{
		 	Connection connection = Database.openConnection();
		 	PreparedStatement ps = connection.prepareStatement(ELIMINA);
		 	ps.setInt(1, gioco.getId());
		 	
		 	int result = ps.executeUpdate();
 
		    ps.close();
		    connection.close();
		    return result;
	 }
	
	//ricerca gioco tramite titolo
	@Override
	public Gioco ricercaGioco(String titolo) throws SQLException {
		
		 	Gioco gioco;
		    Connection connection = Database.openConnection();  
		    PreparedStatement ps = connection.prepareStatement(RICERCA);
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
	@Override
	public ArrayList<Gioco> tuttiGiochi() throws SQLException{
		
		    ArrayList<Gioco> all_games = new ArrayList<>();
		    Connection connection = Database.openConnection();
		    Statement s = connection.createStatement();
		    ResultSet rset = s.executeQuery(TUTTI);
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
	@Override
	public ArrayList<Recensione> recensioniGioco(Gioco gioco) throws SQLException{
		
		    ArrayList<Recensione> game_reviews = new ArrayList<>();
		    Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(RECENSIONI);
		    ps.setInt(1, gioco.getId());
		    ResultSet rset = ps.executeQuery();
		    while (rset.next()){
		      Recensione recensione = new Recensione(rset.getString(1), rset.getInt(2), rset.getInt(4), rset.getString(5));
		      game_reviews.add(recensione);
		    }
		    ps.close();
		    rset.close();
		    connection.close();
		    return game_reviews;
	  }
	
	//restituisce il voto medio del gioco
	@Override
	public int voto(Gioco gioco) throws SQLException{
		
		    int votes_avarage;
		    Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(VOTO);
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
