package it.bibliotecaweb.main;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.utente.UtenteService;

/**
 * Hello world!
 *
 */
public class MainTest 
{
    public static void main( String[] args )
    {
    	AutoreService autoreServiceInstance = MyServiceFactory.getAutoreServiceInstance();
    	LibroService libroServiceInstance = MyServiceFactory.getLibroServiceInstance();
    	RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
    	UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
    }
}
