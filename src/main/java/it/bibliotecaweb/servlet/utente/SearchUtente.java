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
import it.bibliotecaweb.model.Utente.Stato;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class SearchUtente
 */
@WebServlet("/SearchUtente")
public class SearchUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nome")!=null) {
			doPost(request, response);
			return;
		}
		try {
			request.setAttribute("ruoli", MyServiceFactory.getRuoloServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("form_cercaUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		String ruolo=request.getParameter("ruolo");
		String stato=request.getParameter("stato");
		Ruolo r=null;
		try {
			r=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(ruolo.isEmpty()?-1L:Long.parseLong(ruolo));
			Utente u=new Utente(nome,cognome,username,null);
			if(r!=null)
				u.getRuoli().add(r);
			u.setStato((stato==null || stato.isEmpty())?null:Stato.valueOf(stato));
			Set<Utente> utenti=MyServiceFactory.getUtenteServiceInstance().findByParameter(u);
			request.setAttribute("listaUtentiparam", utenti);
		}catch (NumberFormatException  e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("nome_utente", nome);
		session.setAttribute("cognome_utente", cognome);
		session.setAttribute("username", username);
		session.setAttribute("ruolo", r);
		session.setAttribute("stato", stato);

		request.getRequestDispatcher("resultsUtenti.jsp").forward(request, response);
	}

}
