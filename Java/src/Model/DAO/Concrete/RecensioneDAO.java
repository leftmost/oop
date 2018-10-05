package Model.DAO.Concrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	private static final String
	DA_APPROVARE = "SELECT * FROM Recensione WHERE Approvazione='0'";

	private static final String
	DA_APPROVARE_GIOCO = "SELECT * FROM Recensione JOIN Gioco ON Recensione.Gioco_id=Gioco.id WHERE Approvazione='0' AND Gioco.Titolo=?";

	private static final String
	APPROVATE_GIOCO = "SELECT * FROM Recensione JOIN Gioco ON Recensione.Gioco_id=Gioco.id WHERE Approvazione='1' AND Gioco.Titolo=?";

	private static final String
	ELIMINA = "DELETE FROM Recensione WHERE Utente_Username =? AND Gioco_id=? ;";

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

	@Override
	public int approvaRecensione(String utente,int idGioco) throws SQLException {


		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(APPROVA);

		ps.setString(1,utente);
		ps.setInt(2,idGioco);

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}

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

	@Override
	public ArrayList<Recensione> daApprovare() throws SQLException {
		ArrayList<Recensione> recensioni = new ArrayList<>();
		Connection connection = Database.openConnection();
		Statement s = connection.createStatement();
		ResultSet rset = s.executeQuery(DA_APPROVARE);
		while (rset.next()){
			recensioni.add(new Recensione(rset.getString(1), rset.getInt(2), rset.getInt(3),rset.getString(5)));
		}
		s.close();
		rset.close();
		connection.close();
		return recensioni;
	}

	@Override
	public ArrayList<Recensione> daApprovareGioco(String titolo) throws SQLException {
		ArrayList<Recensione> recensioni = new ArrayList<>();
		Connection connection = Database.openConnection();
		Statement s = connection.createStatement();
		PreparedStatement ps = connection.prepareStatement(DA_APPROVARE_GIOCO);
		ps.setString(1, titolo);
		ResultSet rset = ps.executeQuery();
		while (rset.next()){
			recensioni.add(new Recensione(rset.getString(1), rset.getInt(2), rset.getInt(3),rset.getString(5)));
		}
		s.close();
		rset.close();
		connection.close();
		return recensioni;
	}

	@Override
	public ArrayList<Recensione> approvateGioco(String titolo) throws SQLException {
		ArrayList<Recensione> recensioni = new ArrayList<>();
		Connection connection = Database.openConnection();
		Statement s = connection.createStatement();
		PreparedStatement ps = connection.prepareStatement(APPROVATE_GIOCO);
		ps.setString(1, titolo);
		ResultSet rset = ps.executeQuery();
		while (rset.next()){
			recensioni.add(new Recensione(rset.getString(1), rset.getInt(2), rset.getInt(3),rset.getString(5)));
		}
		s.close();
		rset.close();
		connection.close();
		return recensioni;
	}

	@Override
	public int eliminaRecensioneUtente(Recensione recensione) throws SQLException{
		Connection connection = Database.openConnection();
		PreparedStatement ps = connection.prepareStatement(ELIMINA);
		ps.setString(1, recensione.getUtente_username());
		ps.setInt(2, recensione.getGioco_id());

		int result = ps.executeUpdate();

		ps.close();
		connection.close();
		return result;
	}

}
