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
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class gestioneModeratore
 */
@WebServlet("/gestioneModeratore")
public class gestioneModeratore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public gestioneModeratore() {
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
		request.setAttribute("active","Gestione Moderatori");
		//.Frame
		//set parametri
		request.setAttribute("username",utente.getUsername());
		request.setAttribute("tipologia",utente.getTipologia());
		
		UtenteDAOint utenteDAO = new UtenteDAO();
		try {
			request.setAttribute("UteMod",utenteDAO.listaUtentieModeratori());
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		//Carica Home.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/gestioneModeratori.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		Utente utente = (Utente) request.getSession().getAttribute("login");
		
		UtenteDAOint utenteDAO = new UtenteDAO();
		if(request.getParameter("promuovi")==null){
			
			try {
				Utente utenteDaRetrocedere = utenteDAO.ricercaUser(request.getParameter("username"));
				if(utenteDaRetrocedere.getTipologia().equals("Utente")) {
					request.setAttribute("mexags",false);
				}else {
				utenteDAO.retrocediModeratore(utenteDaRetrocedere);
				request.setAttribute("mexag",false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			try {
				Utente utenteDaPromuovere = utenteDAO.ricercaUser(request.getParameter("username"));
				if(utenteDaPromuovere.getTipologia().equals("Moderatore")) {
					request.setAttribute("mexags",true);
				}else {
				utenteDAO.nominaModeratore(utenteDaPromuovere);
				request.setAttribute("mexag",true);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}

}
