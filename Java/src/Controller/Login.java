package Controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Utente utente;
	UtenteDAOint utenteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		utenteDAO=new UtenteDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Carica template
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ricezione parametri
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		// interfaccia DAO

		try {

			//crea utente dalla ricerca
			utente= utenteDAO.ricercaUser(username);
			//controllo esistenza utente
			if(utente==null){
				//set errore utente
				request.setAttribute("ErrorUser","has-error");
				
				//Carico Template
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}

			if(!password.equals(utente.getPassword())){
				//set username corretto
				request.setAttribute("UserInsert",username);
				//set errore password
				request.setAttribute("ErrorPassword","has-error");

				//Carico Template
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}
			//Inizio sessione
			HttpSession session = request.getSession(true);
			session.setAttribute("login", utente);

			//redirect alla home
			response.sendRedirect("/oop17/Home");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

