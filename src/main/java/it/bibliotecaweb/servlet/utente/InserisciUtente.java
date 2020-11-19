package it.bibliotecaweb.servlet.utente;

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
 * Servlet implementation class InserisciUtente
 */
@WebServlet("/InserisciUtente")
public class InserisciUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("admin", MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l).getId());
			request.setAttribute("classic", MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l).getId());
			request.setAttribute("guest", MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(3l).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}*/
		HttpSession session = request.getSession();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String admin=request.getParameter("admin");
		String classic=request.getParameter("classic");
		String guest=request.getParameter("guest");
		String stato=request.getParameter("stato");

		try {
			Ruolo ar=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
			Ruolo cr=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l);
			Ruolo gr=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(3l);
			Boolean cond=MyServiceFactory.getUtenteServiceInstance().caricaPerUsername(username)!=null;
			if(cond) {
				request.setAttribute("admin", ar.getId());
				request.setAttribute("classic", cr.getId());
				request.setAttribute("guest", gr.getId());
				request.setAttribute("errorMessage", "Username già presente");
				request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
				return;
			}
			Set<Utente> utenti=MyServiceFactory.getUtenteServiceInstance().listAll();
			request.setAttribute("listaUtentiparam", utenti);
			Utente u=new Utente(nome,cognome,username,password);
			if(admin !=null)
				MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, ar);
			if(classic!=null)
				MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, cr);
			if(guest!=null)
				MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, gr);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("filtro", false);
//		session.setAttribute("nome_utente", nome);
//		session.setAttribute("cognome_utente", cognome);
//		session.setAttribute("username", username);
//		session.setAttribute(name, value);
//		session.setAttribute("stato", stato);		
		request.getRequestDispatcher("resultsUtenti.jsp").forward(request, response);
	}

}
