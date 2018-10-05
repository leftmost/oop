package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Concrete.TimelineDAO;
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.GiocoDAOint;
import Model.DAO.Interface.RecensioneDAOint;
import Model.DAO.Interface.TimelineDAOint;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class Play
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	GiocoDAOint giocoDAO;
	RecensioneDAOint recensioneDAO;
	TimelineDAOint timelineDAO;
	UtenteDAOint utenteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Play() {
		super();
		giocoDAO = new GiocoDAO();
		recensioneDAO = new RecensioneDAO();
		timelineDAO = new TimelineDAO();
		utenteDAO = new UtenteDAO();
	}


	/**
	 * Metodo che consente di visualizzare il gioco e le relative recensioni
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Play");
		//.Frame
		
		//set titolo del gioco selezionato
		request.setAttribute("titolo",request.getParameter("Gioco"));
		
		
		try {
			//ricerca del gioco
			Gioco gioco = giocoDAO.ricercaGioco(request.getParameter("Gioco"));
			//lista recensioni gioco
			ArrayList<Model.Recensione> recensioni = giocoDAO.recensioniGioco(gioco);
			//set recensioni
			request.setAttribute("recensioni",recensioni);
			
			//lista recensioni da approvare
			ArrayList<Model.Recensione> daApprovare =recensioneDAO.daApprovareGioco(request.getParameter("Gioco"));
			
			for(Model.Recensione x: daApprovare){
				//se l'utente ha una recensione da approvare non può inviarene un'altra
				if(x.getUtente_username().equals(utente.getUsername())) {
					//disattiva form inserimento
					request.setAttribute("disattiva","on");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/Play.jsp");
		rd.forward(request, response);

	}

	/**
	 * Metodo che consente di effettuare una sessione di gioco
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		utente = (Utente) request.getSession().getAttribute("login");

		try {
			//ricerca gioco
			Gioco gioco = giocoDAO.ricercaGioco(request.getParameter("Gioco"));
			//set recensioni 
			request.setAttribute("recensioni",giocoDAO.recensioniGioco(gioco));
		} catch (SQLException e) {
			System.out.println("Errore gioco");
		}
		

		//random vittoria
		boolean vittoria = Math.random() < 0.5;
		//set notifica vittoria
		request.setAttribute("vittoria",vittoria);
		//if vittoria=true aumenta punti 
		if(vittoria) {
			
			// recupera data corrente
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			String oraCorrente= dateFormat.format(date);
			String oraUtente = dateFormat.format(utente.getTimeline().lastExp().getData());
			
			//confronta la data corrente con l'ultima dell'utente
			// se le date coincidono aggiorna l'esperienza odierna
			if(oraCorrente.equals(oraUtente)){
				try {
					//aumenta di 10 exp alla data odierna
					timelineDAO.aumentaExp(utente.getUsername(),10);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				try {
					//aggiungi alla timeline la data odierna e l'exp conquistata 
					timelineDAO.aggiungiTimeline(utente,10);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			try {
				//recupero utente dal db
				utente = utenteDAO.ricercaUser(utente.getUsername());
				request.getSession().setAttribute("login", utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		doGet(request, response);
		
	}

}
