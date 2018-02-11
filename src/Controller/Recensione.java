package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Interface.GiocoDAOint;
import Model.DAO.Interface.RecensioneDAOint;

/**
 * Servlet implementation class Recensione
 */
@WebServlet("/Recensione")
public class Recensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	private RecensioneDAOint recensioneDAO;
	private GiocoDAOint giocoDAO;
	private Model.Recensione nuovoRecensione;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recensione() {
		super();
		recensioneDAO = new RecensioneDAO();
		giocoDAO = new GiocoDAO();
		
	}

	/**
	 * Metodo che memorizza la recensione inviata dall'utente
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//recupere parametri form
		String username_utente = utente.getUsername(); //username
		String testo= request.getParameter("recensione"); // testo
		int rating= Integer.parseInt(request.getParameter("rating")); // valutazione
		

		try {
			//ricerca gioco
			Gioco gioco = giocoDAO.ricercaGioco(request.getParameter("Gioco"));
			int idGioco = gioco.getId(); // id gioco
			
			//lista recensioni approvate di un gioco
			ArrayList<Model.Recensione> listaApprovate= recensioneDAO.approvateGioco(request.getParameter("Gioco"));
			for(Model.Recensione x:listaApprovate){
				//se l'utente ha gia scritto una recensione, quella vecchia viene eliminata
				if(x.getGioco_id()==idGioco &&  x.getUtente_username().equals(username_utente)) {
					recensioneDAO.eliminaRecensioneUtente(x);
				}
			}

			nuovoRecensione = new Model.Recensione(username_utente,idGioco,rating,testo);
			//salva nuova recensione
			recensioneDAO.inserisciRecensione(nuovoRecensione);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		response.sendRedirect("/oop17/Play?Gioco="+request.getParameter("Gioco"));
	}

}
