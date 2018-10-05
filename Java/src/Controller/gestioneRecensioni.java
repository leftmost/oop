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
import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Interface.RecensioneDAOint;

/**
 * Servlet implementation class gestioneRecensioni
 */
@WebServlet("/gestioneRecensioni")
public class gestioneRecensioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	RecensioneDAOint recensioneDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public gestioneRecensioni() {
		super();
		recensioneDAO = new RecensioneDAO();
	}


	/**
	 * Metodo che visualizza elenco recensioni da approvare
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Gestione Recensioni");
		//.Frame

		try {
			request.setAttribute("recensioni",recensioneDAO.daApprovare());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/gestioneRecensioni.jsp");
		rd.forward(request, response);
	}

	/**
	 * Metodo che approva la recensione
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username utente
	 * @param id gioco
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//ricezione paramentri
		String utente=request.getParameter("utente");
		int gioco =Integer.parseInt(request.getParameter("gioco"));

		try {
			//salvataggio approvazione
			recensioneDAO.approvaRecensione(utente, gioco);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
