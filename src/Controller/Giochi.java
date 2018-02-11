package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Interface.*;

/**
 * Servlet implementation class Giochi
 */
@WebServlet("/Giochi")
public class Giochi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	private GiocoDAOint giochiDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Giochi() {
		super();
		giochiDAO = new GiocoDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Giochi");
		//.Frame

		//recupero lista giochi
		try {
			ArrayList<Gioco> listaGiochi = giochiDAO.tuttiGiochi();
			request.setAttribute("listaGiochi",listaGiochi);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/Giochi.jsp");
		rd.forward(request, response);
	}

}
