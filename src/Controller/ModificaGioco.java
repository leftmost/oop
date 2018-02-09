package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaGioco() {
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
		request.setAttribute("active","Gestione Giochi");
		//.Frame

		GiocoDAOint gioco = new GiocoDAO();
		try {
			request.setAttribute("giochi",gioco.tuttiGiochi());
		} catch (SQLException e) {
			System.out.println("errror giochi");
			e.printStackTrace();
		}

		//modificaGioco.jsp
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/modificaGioco.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		System.out.println(request.getParameter("titolo"));
		GiocoDAOint gioco = new GiocoDAO();
		try {
			gioco.eliminaGioco(gioco.ricercaGioco(request.getParameter("titolo")));
		} catch (SQLException e) {
			System.out.println("errror giochi");
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
