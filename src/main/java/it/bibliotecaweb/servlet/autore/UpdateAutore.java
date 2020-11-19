package it.bibliotecaweb.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
 * Servlet implementation class UpdateAutore
 */
@WebServlet("/UpdateAutore")
public class UpdateAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAutore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Autore a = MyServiceFactory.getAutoreServiceInstance().caricaSingoloElemento(Long.parseLong(request.getParameter("id")));
			request.setAttribute("id", a.getId());
			request.setAttribute("nome", a.getNome());
			request.setAttribute("cognome", a.getCognome());
			request.setAttribute("data_di_nascita", a.getData_di_nascita());

		}  catch (NumberFormatException e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("editAutore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String nascita = request.getParameter("data_di_nascita");
		
		String n=(String) session.getAttribute("nome");
		String c=(String) session.getAttribute("cognome");

		if (nome.isEmpty() || cognome.isEmpty() || isNaD(nascita)) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");		
			request.setAttribute("nome", request.getParameter("nome"));
			request.setAttribute("cognome", request.getParameter("cognome"));
			request.setAttribute("data_di_nascita", request.getParameter("data_di_nascita"));
			request.getRequestDispatcher("editAutore.jsp").forward(request, response);
			return;
		}
		
		try {
			String[] dat=nascita.split("-");
			dat[2]=String.valueOf(Integer.parseInt(dat[2])+1);

			LocalDate data_nascita = LocalDate.parse(dat[0]+"-"+dat[1]+"-"+dat[2]);
			
			Autore au = new Autore(nome,cognome,data_nascita);
			au.setId(Long.parseLong(request.getParameter("id")));
			MyServiceFactory.getAutoreServiceInstance().aggiorna(au);
			Set<Autore> utenti=MyServiceFactory.getAutoreServiceInstance().findByParameter(n, c);
			request.setAttribute("listaAutoriparam", utenti);	
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			request.getRequestDispatcher("/ServletLogOut").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("resultsAutori.jsp").forward(request, response);
	}

	private boolean isNaD(String input) {
		try {
			LocalDate.parse(input);
		} catch (DateTimeParseException  e) {
			return true;
		}
		return false;
	}

}
