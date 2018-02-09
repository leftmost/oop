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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaRecensione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String utente=request.getParameter("utente");
		int gioco =Integer.parseInt(request.getParameter("gioco"));
		
		Model.Recensione recensione = new Model.Recensione(utente,gioco);
		System.out.println(recensione);
		RecensioneDAOint recensioneDAO = new RecensioneDAO();
		try {
			recensioneDAO.eliminaRecensioneUtente(recensione);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/oop17/gestioneRecensioni");
		
	}

}
