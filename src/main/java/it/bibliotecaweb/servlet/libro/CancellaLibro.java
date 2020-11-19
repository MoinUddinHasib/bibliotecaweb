package it.bibliotecaweb.servlet.libro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class CancellaLibro
 */
@WebServlet("/CancellaLibro")
public class CancellaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String titolo=(String) session.getAttribute("titolo");
		String genere=(String) session.getAttribute("genere");
		String trama=(String) session.getAttribute("trama");
		Autore a=(Autore)session.getAttribute("autore");
		try {
			Libro c = MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getLibroServiceInstance().rimuovi(c);	
			
			Set<Libro> libri=MyServiceFactory.getLibroServiceInstance().findByParameter(titolo,genere,trama,a);
			request.setAttribute("listaLibriparam", libri);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
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
