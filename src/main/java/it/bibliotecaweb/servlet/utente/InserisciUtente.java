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
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		String admin=request.getParameter("admin");
		String classic=request.getParameter("classic");
		String guest=request.getParameter("guest");
		
		String nome_utente=(String)session.getAttribute("nome_utente");
		String cognome_utente=(String)session.getAttribute("cognome_utente");
		String us=(String)session.getAttribute("username");
		Ruolo r=(Ruolo)session.getAttribute("ruolo");
		String st=(String)session.getAttribute("stato");

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
			if(admin==null && classic==null && guest==null) {
				request.setAttribute("admin", ar.getId());
				request.setAttribute("classic", cr.getId());
				request.setAttribute("guest", gr.getId());
				request.setAttribute("errorMessage", "Selezionare almeno un ruolo");
				request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
				return;
			}
			
			if(nome==null || cognome==null || username==null || password ==null 
					|| nome.isEmpty() || cognome.isEmpty() || username.isEmpty() || password.isEmpty())
				throw new Exception("Errori di validazioni");

			Utente u=new Utente(nome,cognome,username,password);

			if(admin !=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, ar);
			if(classic!=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, cr);
			if(guest!=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, gr);
			
			Utente u1=new Utente(nome_utente,cognome_utente,us,null);
			u1.setStato(null);
			if(r!=null) {
				u1.getRuoli().add(r);
			}
			if(st!=null && !st.isEmpty()) {
				u1.setStato(Stato.valueOf(st));
			}
			
			Set<Utente> utenti=MyServiceFactory.getUtenteServiceInstance().findByParameter(u1);
			request.setAttribute("listaUtentiparam", utenti);
		}catch (IllegalArgumentException|NullPointerException  e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("resultsUtenti.jsp").forward(request, response);
	}

}
