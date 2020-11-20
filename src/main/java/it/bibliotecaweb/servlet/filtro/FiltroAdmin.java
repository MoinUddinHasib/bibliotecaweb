package it.bibliotecaweb.servlet.filtro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.service.MyServiceFactory;

@WebFilter(urlPatterns = {"/CancellaUtente", "/InserisciUtente" ,"/SearchUtente", "/UpdateUtente", "/VisualizzaUtente"})
public class FiltroAdmin implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session = req.getSession();	
		Set<Ruolo> ruoli=(Set<Ruolo>) session.getAttribute("ruoli");
		
		if(ruoli==null) {
			res.sendRedirect(req.getContextPath());
			return;
		}
		
		
		Ruolo ruolo_admin=null;
		try {
			ruolo_admin = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Ruolo r: ruoli) {
			if(r.equals(ruolo_admin)) {
				chain.doFilter(request, response);		
				return;
			}
		}
		req.setAttribute("errorMessage", "Non hai i permessi");
		req.getRequestDispatcher("EntryPage.jsp").forward(req, res);

		
	}

}
