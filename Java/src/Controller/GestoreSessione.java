package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GestoreSessione  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean sessione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//sessione corretta
		HttpSession session = request.getSession(false);
		if(session.getAttribute("login")==null) {
			return false;
			}else {return true;}
	}

}
