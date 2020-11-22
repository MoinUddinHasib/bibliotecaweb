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
		if(request.getParameter("nome")!=null) {
			doPost(request, response);
			return;
		}
		request.getRequestDispatcher("form_cercaAutore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		
		try {
			Autore a=new Autore(nome,cognome,null);
			Set<Autore> autori=MyServiceFactory.getAutoreServiceInstance().findByParameter(a);
			request.setAttribute("listaAutoriparam", autori);
		}catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("nome", nome);
		session.setAttribute("cognome", cognome);

		request.getRequestDispatcher("resultsAutori.jsp").forward(request, response);
	}

}
