package Controller;

import java.io.IOException;

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

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
		request.setAttribute("livello",utente.getLivello());
		request.setAttribute("esperienza",utente.getEsperienza());
		request.setAttribute("tipologia",utente.getTipologia());
		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Home.jsp");
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
