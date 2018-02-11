package Controller;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class gestioneModeratore
 */
@WebServlet("/gestioneModeratore")
public class gestioneModeratore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
	private 	UtenteDAOint utenteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public gestioneModeratore() {
		super();
		utenteDAO = new UtenteDAO();
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
		request.setAttribute("active","Gestione Moderatori");
		//.Frame
		
		//set lista moderatori e utenti
		try {
			request.setAttribute("UteMod",utenteDAO.listaUtentieModeratori());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/gestioneModeratori.jsp");
		rd.forward(request, response);

	}

	/**
	 * Metodo che promuove gli utenti e retrocede i moderatori
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param promuovi
	 * @param retrocedi
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		utente = (Utente) request.getSession().getAttribute("login");
		
		if(request.getParameter("promuovi")==null){
			//retrocessione
			try {
				//ricerca utente da retrocedere
				Utente utenteDaRetrocedere = utenteDAO.ricercaUser(request.getParameter("username"));
				if(utenteDaRetrocedere.getTipologia().equals("Utente")) {
					//impossibile retrocedere un utente
					request.setAttribute("mexags",false);
				}else {
				//retrocessione moderatore
				utenteDAO.retrocediModeratore(utenteDaRetrocedere);
				request.setAttribute("mexag",false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			//promozione
			try {
				//ricerca utente da promuovere
				Utente utenteDaPromuovere = utenteDAO.ricercaUser(request.getParameter("username"));
				if(utenteDaPromuovere.getTipologia().equals("Moderatore")) {
					//impossibile promuovere un moderatore
					request.setAttribute("mexags",true);
				}else {
				//promozione utente
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
