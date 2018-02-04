package Controller;

import java.io.IOException;
import java.sql.SQLException;

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
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class Profilo
 */
@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Profilo() {
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
		//sessione corretta
		HttpSession session = request.getSession(false);
		if(session.getAttribute("login")==null) {response.sendRedirect("/oop17/Logout"); return;}

		Utente utente = (Utente) session.getAttribute("login");
		//set parametri
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("nome",utente.getNome());
		request.setAttribute("cognome",utente.getCognome());
		request.setAttribute("tipologia",utente.getTipologia());
		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Profilo.jsp");
		rd.forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ricezione parametri
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		
		//modifica utente
		HttpSession session = request.getSession();
		Utente utente=(Utente) session.getAttribute("login");
		utente.setNome(nome);
		utente.setCognome(cognome);
		session.setAttribute("login", utente);
		
		//salvataggio db
		UtenteDAOint salvaModifiche=new UtenteDAO();
		try {
			salvaModifiche.aggAnagraficaUser(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("modifica",true);
		doGet(request, response);
	}

}
