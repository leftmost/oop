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

import Model.Utente;
import Model.DAO.Concrete.UtenteDAO;
import Model.DAO.Interface.UtenteDAOint;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
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
		//Carica Login.jsp
		ServletContext sc = request.getSession().getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/SignUp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ricezione parametri
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String password=request.getParameter("password");
		//controllo email
		UtenteDAOint nuovoUtente=new UtenteDAO();
		try {
			if(nuovoUtente.emailEsistente(email)){
				System.out.println(nuovoUtente.emailEsistente(email));
				//notifica email esistente
				request.setAttribute("emailEsistente",true);
				//Ricarica Registrazione.jsp
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/SignUp.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//controllo username
		try {
			if(nuovoUtente.usernameEsistente(username)){
				//notifica email esistente
				request.setAttribute("usernameEsistente",true);

				//Ricarica Registrazione.jsp
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/SignUp.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//salvataggio utente
		try {
			nuovoUtente.inserisciUser(new Utente(username, email, password, nome, cognome,"Utente"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//reindirizzamento login
		response.sendRedirect("/oop17/Login");
	}

}
