package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Recensione;
import Model.DAO.Interface.RecensioneDAOint;
import Model.Database.Database;

public class RecensioneDAO implements RecensioneDAOint{

	
	private static final String
	INSERIMENTO = "INSERT INTO Recensione(utente_username, gioco_id, voto, recensione) VALUES (?, ?, ?, ?);";
	
	private static final String
	APPROVA = "UPDATE Recensione SET approvazione=1 WHERE utente_username=? AND gioco_id=?;";
	
	private static final String
	MOD = "UPDATE Recensione SET recensione=?, voto=? WHERE utente_username=? AND gioco_id=?;";
	
	//inserimento nuova recensione
	@Override
	public int inserisciRecensione(Recensione recensione) throws SQLException {
		
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERIMENTO);
		    ps.setString(1, recensione.getUtente_username());
		    ps.setInt(2, recensione.getGioco_id());
		    ps.setInt(3, recensione.getVoto());
		    ps.setString(4, recensione.getRecensione());

		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
	
	//approvazione recensione
	@Override
	public int approvaRecensione(Recensione recensione) throws SQLException {
		
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(APPROVA);
		   
		    ps.setString(1, recensione.getUtente_username());
		    ps.setInt(2, recensione.getGioco_id());
		   
		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
	
	
	//modifica voto e recensione
	@Override
	public int modificaRecensione(Recensione recensione) throws SQLException {
		
		 	Connection connection = Database.openConnection();
		    PreparedStatement ps = connection.prepareStatement(MOD);
		    ps.setString(1, recensione.getRecensione());
		    ps.setInt(2, recensione.getVoto());
		    ps.setString(3, recensione.getUtente_username());
		    ps.setInt(4, recensione.getGioco_id());

		    int result = ps.executeUpdate();
		    
		    ps.close();
		    connection.close();
		    return result;
	}
}
