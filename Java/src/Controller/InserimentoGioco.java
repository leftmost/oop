package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
	private Utente utente;
	private GiocoDAOint giocoDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserimentoGioco() {
		super();
		giocoDAO = new GiocoDAO();
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
		request.setAttribute("active","Gestione Giochi");
		//.Frame

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/inserimentoGioco.jsp");
		rd.forward(request, response);

	}

	/**
	 * Metodo che consente l'inserimento di un gioco
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param titolo
	 * @param id gioco
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ricezione parametri form
		String titolo = request.getParameter("nuovoGioco");
		Gioco game = new Gioco(titolo);

		try {
			giocoDAO.inserisciGioco(game);
			request.setAttribute("mex",true);
		} catch (SQLException e) {
			request.setAttribute("mex",false);
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
