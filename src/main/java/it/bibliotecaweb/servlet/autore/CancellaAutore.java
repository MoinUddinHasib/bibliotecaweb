package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class CancellaAutore
 */
@WebServlet("/CancellaAutore")
public class CancellaAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaAutore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nome=(String) session.getAttribute("nome");
		String cognome=(String) session.getAttribute("cognome");
		try {
			Autore c = MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getAutoreServiceInstance().rimuovi(c);	
			
			Set<Autore> autori=MyServiceFactory.getAutoreServiceInstance().findByParameter(nome,cognome);
			request.setAttribute("listaAutoriparam", autori);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.getRequestDispatcher("resultsAutori.jsp").forward(request, response);
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