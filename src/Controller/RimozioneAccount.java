package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private Utente utente;
	private UtenteDAOint utenteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RimozioneAccount() {
		super();
		utenteDAO = new UtenteDAO();
	}

	/**
	 * Metodo che visualizza la pagina rimozione utente
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}

		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");

		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Rimozione Account");
		//.Frame

		try {
			//ricerca lista utenti e moderatori
			ArrayList<Utente> utentiANDmoderatori=utenteDAO.listaUtentieModeratori();
			
			//Ordina per tipologia
			Collections.sort(utentiANDmoderatori,new Comparator<Utente>(){
				@Override
		        public int compare(Utente utente, Utente moderatore)
		        {
					return utente.getTipologia().compareTo(moderatore.getTipologia());
	
		        }
			});
			//.Ordinamento
			
			//set lista utenti e moderatori
			request.setAttribute("utentiANDmoderatori",utentiANDmoderatori);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/RimozioneAccount.jsp");
		rd.forward(request, response);
	}

	/**
	 * Metodo che rimuove account dal sistema
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param username
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		
		//creazione utente
		utente = new Utente(request.getParameter("username"));
		try {
			//rimozione utente
			utenteDAO.rimozioneUser(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
