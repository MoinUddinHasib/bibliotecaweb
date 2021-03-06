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
 * Servlet implementation class SearchLibro
 */
@WebServlet("/SearchLibro")
public class SearchLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("titolo")!=null) {
			doPost(request, response);
			return;
		}
		try {
			request.setAttribute("autori", MyServiceFactory.getAutoreServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("form_cercaLibro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String titolo=request.getParameter("titolo");
		String genere=request.getParameter("genere");
		String trama=request.getParameter("trama");
		Long id_a;
		Autore a=null;
		
		try {
			if (request.getParameter("autore").isEmpty())
				id_a = -1l;
			else
				id_a = Long.parseLong(request.getParameter("autore"));
			a=MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(id_a);
			Libro l=new Libro(titolo,genere,trama);
			l.setAutore(a);
			Set<Libro> libri=MyServiceFactory.getLibroServiceInstance().findByParameter(l);
			request.setAttribute("listaLibriparam", libri);
		}catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("titolo", titolo);
		session.setAttribute("genere", genere);
		session.setAttribute("trama", trama);
		session.setAttribute("autore", a);
		request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
	}

}
