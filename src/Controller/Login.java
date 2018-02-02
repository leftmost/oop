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
import Model.Database.DB;
import Model.Database.Database;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		UtenteDAOint utentei=new UtenteDAO();
		try {
			
			//crea utente dalla ricerca
			Utente utente= utentei.findUserbyUsername(username);
			//controllo esistenza utente
			if(utente==null){
				//set errore utente
				request.setAttribute("ErrorUser","has-error");
				//carico login.jsp
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}
			
			//controllo correttezza password
			if(!password.equals(utente.getPassword())){
				//set username corretto
				request.setAttribute("UserInsert",username);
				//set errore password
				request.setAttribute("ErrorPassword","has-error");
				//carico  login.jsp
				ServletContext sc = request.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
				return;
			}
			//Inizio sessione
			HttpSession session = request.getSession();
			session.setAttribute("login", utente);
			//redirect alla home
			response.sendRedirect("/oop17/Home");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

