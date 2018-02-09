package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Utente;
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class RimozioneAccount
 */
@WebServlet("/RimozioneAccount")
public class RimozioneAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimozioneAccount() {
		super();
		// TODO Auto-generated constructor stub
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
		request.setAttribute("active","Home");
		//.Frame

		UtenteDAOint utenteDAO = new UtenteDAO();
		try {
			
			ArrayList<Utente> utentiANDmoderatori=utenteDAO.listaUtentieModeratori();
			//Ordina per tipologia
			Collections.sort(utentiANDmoderatori,new Comparator<Utente>(){
				@Override
		        public int compare(Utente utente, Utente moderatore)
		        {
					return utente.getTipologia().compareTo(moderatore.getTipologia());
	
		        }
			});
			
			request.setAttribute("utentiANDmoderatori",utentiANDmoderatori);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//RimozioneAccount.jsp
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/RimozioneAccount.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utente = new Utente(request.getParameter("username"));
		UtenteDAOint utenteDAO = new UtenteDAO();
		try {
			utenteDAO.rimozioneUser(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
