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

/**
 * Servlet Filter implementation class FiltroJSP
 */
@WebFilter(urlPatterns = {"/editAutore.jsp","/editLibro.jsp","/editUtente.jsp","/EntryPage.jsp","/footer.jsp","/form_cercaAutore.jsp","/form_cercaLibro.jsp",
		"/form_cercaUtente.jsp","/header.jsp","/insertAutore.jsp","/insertLibro.jsp","/insertUtente.jsp","/listaLibri.jsp","/navbar.jsp","/resultsAutori.jsp",
		"/resultsUtenti.jsp","/showAutore.jsp","/showLibro.jsp","/showUtente.jsp","/confermaLibro.jsp","/confermaAutore.jsp","/confermaUtente.jsp"})
public class FiltroJSP implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroJSP() {
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
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
