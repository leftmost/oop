package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Utente;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Utente utente;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }


	/**
	 * Metodo che visualizza la home page
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Gestione sessione
		if(!GestoreSessione.sessione(request, response)){response.sendRedirect("/oop17/Logout"); return;}
		
		//recupero utente in sessione
		utente = (Utente) request.getSession().getAttribute("login");
		
		//Frame-public
		request.setAttribute("utente",utente);
		request.setAttribute("active","Home");
		//.Frame
		
		//Caricamento template
		RequestDispatcher rd = request.getSession().getServletContext().getRequestDispatcher("/Home.jsp");
		rd.forward(request, response);
	}

}
