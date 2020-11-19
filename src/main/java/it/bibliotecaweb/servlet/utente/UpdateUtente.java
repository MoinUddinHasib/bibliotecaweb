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
 * Servlet implementation class UpdateUtente
 */
@WebServlet("/UpdateUtente")
public class UpdateUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Utente u = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
			request.setAttribute("id", u.getId());
			request.setAttribute("nome", u.getNome());
			request.setAttribute("cognome", u.getCognome());
			request.setAttribute("username", u.getUsername());
			request.setAttribute("password", u.getPassword());
			request.setAttribute("last", u.getUsername());
			Ruolo ra=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
			Ruolo rc=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l);
			Ruolo rg=MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(3l);
			
			for(Ruolo r: u.getRuoli()) {
				if(r.equals(ra))
					request.setAttribute("cond_admin", true);
				if(r.equals(rc))
					request.setAttribute("cond_classic", true);
				if(r.equals(rg))
					request.setAttribute("cond_guest", true);
			}
			
			request.setAttribute("stato", u.getStato().toString());
			
			request.setAttribute("admin", ra.getId());
			request.setAttribute("classic", rc.getId());
			request.setAttribute("guest", rg.getId());

		}  catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		request.getRequestDispatcher("editUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id=request.getParameter("id");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		String admin=request.getParameter("admin");
		String classic=request.getParameter("classic");
		String guest=request.getParameter("guest");
		
		String stato=request.getParameter("stato");
		
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
			if(!request.getParameter("last").equals(username) && cond || (admin==null && classic==null && guest==null)) {
				request.setAttribute("id", id);
				request.setAttribute("nome", nome);
				request.setAttribute("cognome", cognome);
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				
				request.setAttribute("cond_admin", admin!=null);

				request.setAttribute("cond_classic", classic!=null);

				request.setAttribute("cond_guest", guest!=null);
				
				
				request.setAttribute("stato", stato);
				
				request.setAttribute("admin", ar.getId());
				request.setAttribute("classic", cr.getId());
				request.setAttribute("guest", gr.getId());
				request.setAttribute("errorMessage", "Username già presente oppure selezionare almeno un ruolo");
				request.getRequestDispatcher("editUtente.jsp").forward(request, response);
				return;
			}
			
			if(nome.isEmpty() || cognome.isEmpty() || username.isEmpty() || password.isEmpty())
				throw new Exception("Errori di validazioni");

			Utente u=new Utente(nome,cognome,username,password);
			u.setStato(Stato.valueOf(stato));
			u.setId(Long.parseLong(id));
			if(admin !=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, ar);
			if(classic!=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, cr);
			if(guest!=null)
				u=MyServiceFactory.getUtenteServiceInstance().inserisciRuolo(u, gr);

			Set<Utente> utenti=MyServiceFactory.getUtenteServiceInstance().findByParameter(nome_utente, cognome_utente, us, r, st);
			request.setAttribute("listaUtentiparam", utenti);
		}catch (IllegalArgumentException|NullPointerException  e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			Ruolo ar = null;
			Ruolo cr = null;
			Ruolo gr = null;
			try {
				ar = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
				cr = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l);
				gr = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(3l);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			request.setAttribute("id", id);
			request.setAttribute("nome", nome);
			request.setAttribute("cognome", cognome);
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			
			request.setAttribute("cond_admin", admin!=null);

			request.setAttribute("cond_classic", classic!=null);

			request.setAttribute("cond_guest", guest!=null);
			
			
			request.setAttribute("stato", stato);
			
			request.setAttribute("admin", ar.getId());
			request.setAttribute("classic", cr.getId());
			request.setAttribute("guest", gr.getId());
			request.setAttribute("errorMessage", "Errori di validazioni");
			request.getRequestDispatcher("editUtente.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("resultsUtenti.jsp").forward(request, response);
	}

}
