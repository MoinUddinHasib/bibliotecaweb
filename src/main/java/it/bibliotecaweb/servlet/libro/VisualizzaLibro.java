package it.bibliotecaweb.servlet.libro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.bibliotecaweb.model.Libro;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class VisualizzaLibro
 */
@WebServlet("/VisualizzaLibro")
public class VisualizzaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaLibro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Libro a = null;
		try {
			a = MyServiceFactory.getLibroServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
		}catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("id", a.getId());
		request.setAttribute("titolo", a.getTitolo());
		request.setAttribute("genere", a.getGenere());
		request.setAttribute("trama", a.getTrama());
		request.setAttribute("nome_autore", a.getAutore().getNome());
		request.setAttribute("cognome_autore", a.getAutore().getCognome());
		request.getRequestDispatcher("showLibro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
