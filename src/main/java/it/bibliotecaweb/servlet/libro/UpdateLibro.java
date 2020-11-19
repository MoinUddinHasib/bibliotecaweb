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
 * Servlet implementation class Updatelibro
 */
@WebServlet("/UpdateLibro")
public class UpdateLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Libro l = MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));

			request.setAttribute("id", l.getId());
			request.setAttribute("titolo", l.getTitolo());
			request.setAttribute("genere", l.getGenere());
			request.setAttribute("trama", l.getTrama());
			request.setAttribute("autore_fk", l.getAutore().getId());
		}  catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Set<Autore> autori=null;
		try {
			autori = MyServiceFactory.getAutoreServiceInstance().listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("autori", autori);
		
		request.getRequestDispatcher("editLibro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String trama = request.getParameter("trama");
		String autoreid = request.getParameter("autore");
		String id= request.getParameter("id");
		String ti=(String)session.getAttribute("titolo");
		String g=(String)session.getAttribute("genere");
		String tr=(String)session.getAttribute("trama");
		Autore au=(Autore)session.getAttribute("autore");


		if (titolo.isEmpty() || genere.isEmpty() || trama.isEmpty() || isNaN(autoreid)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("titolo", titolo);
			request.setAttribute("genere", genere);
			request.setAttribute("trama", trama);
			request.setAttribute("autore_fk", request.getParameter("autore_fk"));
			Set<Autore> autori=null;
			try {
				autori = MyServiceFactory.getAutoreServiceInstance().listAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("autori", autori);
			request.getRequestDispatcher("editLibro.jsp").forward(request, response);
			return;
		}


		try {
			Libro libroInstance = new Libro(titolo, genere, trama);
			libroInstance.setId(Long.parseLong(id));
			libroInstance.setAutore(MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(Long.parseLong(autoreid)));
			MyServiceFactory.getLibroServiceInstance().aggiorna(libroInstance);
			Set<Libro> libri=MyServiceFactory.getLibroServiceInstance().findByParameter(ti, g, tr, au);
			request.setAttribute("listaLibriparam", libri);
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}


		request.getRequestDispatcher("listaLibri.jsp").forward(request, response);

	}

	private boolean isNaN(String input) {
		try {
			Long.parseLong(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
