package Controller;

import java.io.IOException;
import java.sql.SQLException;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestioneUtenti() {
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
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//Frame-public
		Utente utente = (Utente) request.getSession().getAttribute("login");
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("nome",utente.getNome());
		request.setAttribute("tipologia",utente.getTipologia());
		request.setAttribute("active","Gestione Utenti");
		//.Frame

		//set parametri
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("tipologia",utente.getTipologia());
		UtenteDAOint utenteDAO = new UtenteDAO();
		try {
			request.setAttribute("utenti",utenteDAO.listaUtentiBase());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/gestioneUtenti.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		Utente utente = (Utente) request.getSession().getAttribute("login");
		TimelineDAOint timeline = new TimelineDAO();
		
		if(request.getParameter("promuovi")==null){
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
