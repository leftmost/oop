package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Gioco;
import Model.Utente;
import Model.DAO.Concrete.GiocoDAO;
import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Interface.GiocoDAOint;
import Model.DAO.Interface.RecensioneDAOint;

/**
 * Servlet implementation class Recensione
 */
@WebServlet("/Recensione")
public class Recensione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recensione() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessione corretta
		HttpSession session = request.getSession(false);
		if(session.getAttribute("login")==null) {response.sendRedirect("/oop17/Logout"); return;}
		Utente utente = (Utente) session.getAttribute("login");

		String username_utente = utente.getUsername(); //username utente
		String testo= request.getParameter("recensione"); // testo
		//voto TODO


	

		RecensioneDAOint recensioneDAO = new RecensioneDAO();
		GiocoDAOint giocoDAO = new GiocoDAO();

		try {
			Gioco gioco = giocoDAO.ricercaGioco(request.getParameter("Gioco"));
			int idGioco = gioco.getId(); // id gioco

			ArrayList<Model.Recensione> listaApprovate= recensioneDAO.approvateGioco(request.getParameter("Gioco"));
			for(Model.Recensione x:listaApprovate){
				
				if(x.getGioco_id()==idGioco) {
					recensioneDAO.eliminaRecensioneUtente(x);
				}
			}

			Model.Recensione nuovoRecensione = new Model.Recensione(username_utente,idGioco,5,testo);

			RecensioneDAOint recensione = new RecensioneDAO();
			recensione.inserisciRecensione(nuovoRecensione);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		response.sendRedirect("/oop17/Play?Gioco="+request.getParameter("Gioco"));
	}

}
