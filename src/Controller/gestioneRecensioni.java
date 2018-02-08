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
import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Interface.RecensioneDAOint;

/**
 * Servlet implementation class gestioneRecensioni
 */
@WebServlet("/gestioneRecensioni")
public class gestioneRecensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public gestioneRecensioni() {
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
		//controllo accesso
		if(utente.getTipologia().equals("Utente")){response.sendRedirect("/oop17/Logout"); return;}
		//set parametri
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("tipologia",utente.getTipologia());
		
		
		try {
			RecensioneDAOint recensione = new RecensioneDAO();
			request.setAttribute("recensioni",recensione.daApprovare());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Carica gestioneRecensioni.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/gestioneRecensioni.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String utente=request.getParameter("utente");
		int gioco =Integer.parseInt(request.getParameter("gioco"));
		
		RecensioneDAOint recensione = new RecensioneDAO();
		try {
			recensione.approvaRecensione(utente, gioco);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
