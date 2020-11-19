package it.bibliotecaweb.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class VisualizzaUtente
 */
@WebServlet("/VisualizzaUtente")
public class VisualizzaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente a = null;
		try {
			a = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
		}catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("id", a.getId());
		request.setAttribute("nome", a.getNome());
		request.setAttribute("cognome", a.getCognome());
		request.setAttribute("username", a.getUsername());
		request.setAttribute("ruoli", a.getRuoli());
		request.setAttribute("stato", a.getStato());

		request.getRequestDispatcher("showUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
