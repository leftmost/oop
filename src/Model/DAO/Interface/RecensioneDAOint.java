package Model.DAO.Interface;

import java.sql.SQLException;

import Model.Recensione;
import Model.DAO.Concrete.RecensioneDAO;


public interface RecensioneDAOint {
	
	public int insertReview(Recensione recensione) throws SQLException;
	
	public int approvalReview(Recensione recensione) throws SQLException;
	
	public int modReview(Recensione recensione) throws SQLException;
	
	public static void main (String [] args) throws SQLException {
			
			RecensioneDAOint a = new RecensioneDAO();
			
		//   1 - inserimento recensione
			
//		try {
//				int ok=a.insertReview(new Recensione("chante",2,2,"molto bellissimo"));
//			System.out.println(ok);
//		} catch (SQLException e){
//				e.printStackTrace();
//			}
			
		//   2 - approvazione recensione
			
			try {
					int ok=a.approvalReview(new Recensione("chante",2,2,"molto bellissimo"));
				System.out.println(ok);
			} catch (SQLException e){
					e.printStackTrace();
				}	
			
	}
}
