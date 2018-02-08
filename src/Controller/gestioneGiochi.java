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

import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Interface.GiocoDAOint;

/**
 * Servlet implementation class gestoreGiochi
 */
@WebServlet("/gestioneGiochi")
public class gestioneGiochi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestioneGiochi() {
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
		request.setAttribute("tipologia",utente.getTipologia());
		
		
		
		String titolo = request.getParameter("nuovoGioco");
		Gioco game = new Gioco(8,titolo);
		GiocoDAOint gioco = new GiocoDAO();
		try {
			gioco.inserisciGioco(game);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		
		

		
		//Carica gestioneGiochi.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/inserimentoGioco.jsp");
		rd.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
