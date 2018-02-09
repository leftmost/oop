package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Play() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		
		//Frame-public
		Utente utente = (Utente) request.getSession().getAttribute("login");
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("nome",utente.getNome());
		request.setAttribute("tipologia",utente.getTipologia());
		request.setAttribute("active","Play");
		//.Frame
		
		request.setAttribute("titolo",request.getParameter("Gioco"));
		
		GiocoDAOint play = new GiocoDAO();
		try {
			Gioco gioco = play.ricercaGioco(request.getParameter("Gioco"));
			ArrayList<Model.Recensione> recensioni = play.recensioniGioco(gioco);
			
			request.setAttribute("recensioni",recensioni);
			
			RecensioneDAOint recensioneDAO = new RecensioneDAO();
			ArrayList<Model.Recensione> daApprovare =recensioneDAO.daApprovare();
			
			for(Model.Recensione x: daApprovare){
				if(x.getUtente_username().equals(utente.getUsername())) {
					request.setAttribute("disattiva","on");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Play.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessione corretta
		HttpSession session = request.getSession(false);
		if(session.getAttribute("login")==null) {response.sendRedirect("/oop17/Logout"); return;}
		Utente utente = (Utente) session.getAttribute("login");
		
		GiocoDAOint play = new GiocoDAO();
		try {
			Gioco gioco = play.ricercaGioco(request.getParameter("Gioco"));
			System.out.println(gioco);
			request.setAttribute("recensioni",play.recensioniGioco(gioco));
		} catch (SQLException e) {
			System.out.println("Errore gioco");
		}
		

		//random vittoria
		boolean vittoria = Math.random() < 0.5;
		request.setAttribute("vittoria",vittoria);
		//if vittoria=true aumenta punti 
		if(vittoria) {
			TimelineDAOint timeline = new TimelineDAO();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			String oraCorrente= dateFormat.format(date);
			String oraUtente = dateFormat.format(utente.getTimeline().lastExp().getData());
			
			//confronta la data di oggi con l'ultima dell'utente
			// se le date coincidono aggiorna l'esperienza
			if(oraCorrente.equals(oraUtente)){
				try {
					timeline.aumentaExp(utente.getUsername(),10);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					timeline.aggiornaTimeline(utente);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			UtenteDAOint aggiornaUtente = new UtenteDAO();
			try {
				Utente nuovoUtente = aggiornaUtente.ricercaUser(utente.getUsername());
				session.setAttribute("login", nuovoUtente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		doGet(request, response);
		
	}

}
