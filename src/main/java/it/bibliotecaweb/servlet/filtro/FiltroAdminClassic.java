package it.bibliotecaweb.servlet.filtro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet Filter implementation class FiltroAdminClassic
 */
@WebFilter(urlPatterns = {"/CancellaAutore", "/InserisciAutore" , "/UpdateAutore", "/CancellaLibro", "/InserisciLibro", "/UpdateLibro"})
public class FiltroAdminClassic implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroAdminClassic() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session = req.getSession();	
		Set<Ruolo> ruoli=(Set<Ruolo>) session.getAttribute("ruoli");
		
		if(ruoli==null) {
			res.sendRedirect(req.getContextPath());
			return;
		}
		
		
		Ruolo ruolo_admin=null;
		Ruolo ruolo_classic=null;
		try {
			ruolo_admin = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(1l);
			ruolo_classic = MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(2l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Ruolo r: ruoli) {
			if(r.equals(ruolo_admin) || r.equals(ruolo_classic)) {
				chain.doFilter(request, response);		
				return;
			}
		}
		req.setAttribute("errorMessage", "Non hai i permessi");
		req.getRequestDispatcher("EntryPage.jsp").forward(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
