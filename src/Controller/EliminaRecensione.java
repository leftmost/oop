package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.Concrete.RecensioneDAO;
import Model.DAO.Interface.RecensioneDAOint;

/**
 * Servlet implementation class EliminaRecensione
 */
@WebServlet("/EliminaRecensione")
public class EliminaRecensione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Model.Recensione recensione;
	private RecensioneDAOint recensioneDAO;
       
    /**
     * Costruttore
     * @see HttpServlet#HttpServlet()
     */
    public EliminaRecensione() {
        super();
        recensioneDAO=new RecensioneDAO();
    }

	/**
	 * Metodo che elimina una recensione
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ricezione parametri da form
		String utente=request.getParameter("utente");
		int gioco =Integer.parseInt(request.getParameter("gioco"));
		
		recensione = new Model.Recensione(utente,gioco);
		
		try {
			recensioneDAO.eliminaRecensioneUtente(recensione);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//reindirizzamento
		response.sendRedirect("/oop17/gestioneRecensioni");
	}

}
