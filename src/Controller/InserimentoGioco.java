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

import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Interface.GiocoDAOint;

/**
 * Servlet implementation class InserimentoGioco
 */
@WebServlet("/InserimentoGioco")
public class InserimentoGioco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserimentoGioco() {
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

		//inserimentoGioco.jsp
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/inserimentoGioco.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String titolo = request.getParameter("nuovoGioco");
		Gioco game = new Gioco(titolo);
		GiocoDAOint gioco = new GiocoDAO();
		System.out.println(game);
		try {
			gioco.inserisciGioco(game);
			boolean bool = true;
			request.setAttribute("mex",bool);
		} catch (SQLException e) {
			boolean bool = false;
			request.setAttribute("mex",bool);
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
