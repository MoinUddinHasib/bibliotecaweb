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
 * Servlet implementation class SearchAutore
 */
@WebServlet("/SearchAutore")
public class SearchAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAutore() {
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

		request.getRequestDispatcher("form_cercaAutore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		HttpSession session = request.getSession();-------------------------------------------------------------------------------------------------------------------------------------
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}*/
		HttpSession session = request.getSession();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		
		try {
			
			Set<Autore> autori=MyServiceFactory.getAutoreServiceInstance().findByParameter(nome,cognome);
			request.setAttribute("listaAutoriparam", autori);
		}catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("filtro", true);
		session.setAttribute("nome", nome);
		session.setAttribute("cognome", cognome);

		request.getRequestDispatcher("resultsAutori.jsp").forward(request, response);
	}

}
