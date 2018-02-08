package Controller;

import javax.servlet.http.HttpSession;

public class GestorePagine {
	
	public void sessione() {
		//sessione corretta
				HttpSession session = request.getSession(false);
				if(session.getAttribute("login")==null) {response.sendRedirect("/oop17/Logout"); return;}
	}

}
