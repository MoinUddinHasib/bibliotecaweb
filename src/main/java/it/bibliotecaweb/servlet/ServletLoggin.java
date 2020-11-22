package it.bibliotecaweb.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ServletLoggin
 */
@WebServlet("/ServletLoggin")
public class ServletLoggin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoggin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("stato_login")==null) {
			response.sendRedirect(request.getContextPath());
			session.invalidate();
			return;
		} 
		request.getRequestDispatcher("EntryPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		try {
			Utente uten=MyServiceFactory.getUtenteServiceInstance().caricaPerUsername(username);		
			if(uten==null || !uten.getPassword().equals(password) || uten.getStato().equals(Utente.Stato.INATTIVO)) {
				request.setAttribute("errorMessage", "Credenziali sbagliate o account inattivo");
				session.invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("nome_login", uten.getNome());
			session.setAttribute("cognome_login", uten.getCognome());
			session.setAttribute("username_login", uten.getUsername());
			session.setAttribute("stato_login", uten.getStato());
			session.setAttribute("ruoli", uten.getRuoli());
			autenticazione(session);
			request.getRequestDispatcher("EntryPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void autenticazione(HttpSession session) {		
		Set<Ruolo> ruoli=(Set<Ruolo>) session.getAttribute("ruoli");
		
		Ruolo ruolo_admin=null;
		Ruolo ruolo_classic=null;
		try {
			ruolo_admin = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
			ruolo_classic = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("admin_cond", false);
		session.setAttribute("classic_cond", false);
		
		for(Ruolo r: ruoli) {
			if(r.equals(ruolo_admin)) {
				session.setAttribute("admin_cond", true);
			}
			if(r.equals(ruolo_classic)) {
				session.setAttribute("classic_cond", true);
			}
		}
		
	}
}
