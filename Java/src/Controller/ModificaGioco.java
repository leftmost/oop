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
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Interface.GiocoDAOint;

/**
 * Servlet implementation class ModificaGioco
 */
@WebServlet("/ModificaGioco")
public class ModificaGioco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	GiocoDAOint giocoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaGioco() {
		super();
		giocoDAO = new GiocoDAO();
	}

	/**
	 * Metodo che visualizza lista di giochi
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Gestione Giochi");
		//.Frame

		try {
			//recupera e set lista di tutti i giochi
			request.setAttribute("giochi",giocoDAO.tuttiGiochi());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Carica template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/modificaGioco.jsp");
		rd.forward(request, response);
	}

	/**
	 * Metodo che rimuove un gioco
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param id gioco
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		
		try {
			//rimozione del gioco tramite id gioco
			giocoDAO.eliminaGioco(giocoDAO.ricercaGioco(request.getParameter("titolo")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
