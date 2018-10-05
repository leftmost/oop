package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Utente;
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class Profilo
 */
@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	UtenteDAOint utenteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profilo() {
		super();
		utenteDAO=new UtenteDAO();
	}

	/**
	 * Metodo che visualizza l'anagrafica utente
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Profilo");
		//.Frame

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/Profilo.jsp");
		rd.forward(request, response);

	}


	/**
	 * Metodo che modifica anagrafica utente
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param nome utente
	 * @param cognome utente
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//ricezione parametri
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");

		//modifica utente
		HttpSession session = request.getSession();
		utente=(Utente) session.getAttribute("login");
		utente.setNome(nome);
		utente.setCognome(cognome);
		session.setAttribute("login", utente);

		//salvataggio modifiche
		try {
			utenteDAO.aggAnagraficaUser(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("modifica",true);
		doGet(request, response);
	}

}
