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
 * Servlet implementation class InserisciLibro
 */
@WebServlet("/InserisciLibro")
public class InserisciLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}*/
		try {
			request.setAttribute("autori", it.bibliotecaweb.service.MyServiceFactory.getAutoreServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("insertLibro.jsp").forward(request, response);
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
		String titolo=request.getParameter("titolo");
		String genere=request.getParameter("genere");
		String trama=request.getParameter("trama");
		Autore a=null;
		
		try {
			a=MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("autore")));
			if(a==null)
				throw new NumberFormatException();

			Libro lb=new Libro(titolo,genere,trama);
			lb.setAutore(a);
			MyServiceFactory.getLibroServiceInstance().inserisciNuovo(lb);
			Set<Libro> libri=MyServiceFactory.getLibroServiceInstance().listAll();
			request.setAttribute("listaLibriparam", libri);
		}catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
			return;
		}
		session.setAttribute("filtro", false);
//		session.setAttribute("titolo", titolo);
//		session.setAttribute("genere", genere);
//		session.setAttribute("trama", trama);
//		session.setAttribute("autore", a);
		request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
	}

}
