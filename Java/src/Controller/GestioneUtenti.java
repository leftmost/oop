package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Utente;
import Model.DAO.Concrete.TimelineDAO;
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.TimelineDAOint;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class GestioneUtenti
 */
@WebServlet("/GestioneUtenti")
public class GestioneUtenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	UtenteDAOint utenteDAO;
	TimelineDAOint timeline;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneUtenti() {
		super();
		utenteDAO = new UtenteDAO();
		timeline = new TimelineDAO();
	}


	/**
	 * Metodo che visualizza Gestione Utenti
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Gestione Utenti");
		//.Frame

		//set lista utenti base
		try {
			request.setAttribute("utenti",utenteDAO.listaUtentiBase());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/gestioneUtenti.jsp");
		rd.forward(request, response);
	}

	/**
	 * Metodo che promuove o retrocede il livello dell'utente
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param promuovi
	 * @param retrocedi
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		utente = (Utente) request.getSession().getAttribute("login");

		if(request.getParameter("promuovi")==null){
			//retrocessione livello utente
			UtenteDAOint utenteDAO = new UtenteDAO();
			try {
				Utente utenteDaRetrocedere = utenteDAO.ricercaUser(request.getParameter("username"));
				int expUtente= utenteDaRetrocedere.getTimeline().lastExp().getExp();

				if(expUtente<100){
					timeline.retrocediLiv(request.getParameter("username"), expUtente);

				}else {
					timeline.retrocediLiv(request.getParameter("username"), 100);
				}
				request.setAttribute("mex",false);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		else {
			//promozione livello utente
			try {
				timeline.aumentaLiv(request.getParameter("username"),100);
				request.setAttribute("mex",true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		doGet(request, response);
	}

}
