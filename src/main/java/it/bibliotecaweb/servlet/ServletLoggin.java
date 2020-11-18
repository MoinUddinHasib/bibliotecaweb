package it.bibliotecaweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ServletLoggin
 */
@WebServlet("/ServletLoggin")
public class ServletLoggin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoggin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {/*
		HttpSession session = request.getSession();
		if(session.getAttribute("ruolo")==null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		request.getRequestDispatcher("EntryPage.jsp").forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		try {
			Utente uten=MyServiceFactory.getUtenteServiceInstance().caricaPerUsername(username);		
			if(uten==null || !uten.getPassword().equals(password) || uten.getStato().equals("INATTIVO")) {
				request.setAttribute("errorMessage", "Credenziali sbagliate o account inattivo");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
			session.setAttribute("nome", uten.getNome());
			session.setAttribute("cognome", uten.getCognome());
			session.setAttribute("username", uten.getUsername());
			session.setAttribute("stato", uten.getStato());
			request.getRequestDispatcher("EntryPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
