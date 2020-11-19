package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class InserisciAutore
 */
@WebServlet("/InserisciAutore")
public class InserisciAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciAutore() {
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
		}
		session = request.getSession();
		if(session.getAttribute("ruolo").equals("GUEST")) {
			response.sendRedirect(request.getContextPath()+"/ListArticoliServlet");
			return;
		}
		request.setAttribute("fil", request.getParameter("fil"));
		request.setAttribute("co", request.getParameter("co"));
		request.setAttribute("de", request.getParameter("de"));
		request.setAttribute("pr", request.getParameter("pr"));
		request.setAttribute("cat", request.getParameter("cat"));
		*/

		request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();/*
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}*/
		// validiamo input
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		LocalDate data=null;
		try {

			String[] dat=request.getParameter("data").split("-");
			dat[2]=String.valueOf(Integer.parseInt(dat[2])+1);

			data = LocalDate.parse(dat[0]+"-"+dat[1]+"-"+dat[2]);
			
		} catch (DateTimeParseException|NumberFormatException e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
			return;
		}


		if (nome.isEmpty() || cognome.isEmpty() ) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
			return;
		}
		
		try {
			Autore a = new Autore(nome, cognome, data);			
			MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(a);
			
			request.setAttribute("listaAutoriparam", MyServiceFactory.getAutoreServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insertAutore.jsp").forward(request, response);
			return;
		}
		session.setAttribute("filtro", false);
		request.getRequestDispatcher("resultsAutori.jsp").forward(request, response);
	}

}
