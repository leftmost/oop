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
 * Servlet implementation class Play
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Play() {
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
		request.setAttribute("titolo",request.getParameter("Gioco"));


		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Play.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessione corretta
		HttpSession session = request.getSession(false);
		if(session.getAttribute("login")==null) {response.sendRedirect("/oop17/Logout"); return;}

		Utente utente = (Utente) session.getAttribute("login");
		//set parametri
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("tipologia",utente.getTipologia());
		request.setAttribute("titolo",request.getParameter("titolo"));
		
		
		//random vittoria
		boolean vittoria = Math.random() < 0.5;
		request.setAttribute("vittoria",vittoria);
		

		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Play.jsp");
		rd.forward(request, response);
	}

}
