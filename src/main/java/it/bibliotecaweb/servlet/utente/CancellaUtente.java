package it.bibliotecaweb.servlet.utente;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.model.Utente.Stato;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class CancellaUtente
 */
@WebServlet("/CancellaUtente")
public class CancellaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nome_utente=(String)session.getAttribute("nome_utente");
		String cognome_utente=(String)session.getAttribute("cognome_utente");
		String username=(String)session.getAttribute("username");
		Ruolo r=(Ruolo)session.getAttribute("ruolo");
		String st=(String)session.getAttribute("stato");
		
		try {
			Utente c = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getUtenteServiceInstance().rimuovi(c);			
			Utente u1=new Utente(nome_utente,cognome_utente,username,null);
			u1.setStato(null);
			if(r!=null) {
				u1.getRuoli().add(r);
			}
			if(st!=null && !st.isEmpty()) {
				u1.setStato(Stato.valueOf(st));
			}
			Set<Utente> utenti=MyServiceFactory.getUtenteServiceInstance().findByParameter(u1);
			request.setAttribute("listaUtentiparam", utenti);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.getRequestDispatcher("resultsUtenti.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
