package it.bibliotecaweb.service;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.dao.autore.AutoreDAOImpl;
import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.dao.libro.LibroDAOImpl;
import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.dao.ruolo.RuoloDAOImpl;
import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.dao.utente.UtenteDAOImpl;
import it.bibliotecaweb.service.autore.AutoreService;
import it.bibliotecaweb.service.autore.AutoreServiceImpl;
import it.bibliotecaweb.service.libro.LibroService;
import it.bibliotecaweb.service.libro.LibroServiceImpl;
import it.bibliotecaweb.service.ruolo.RuoloService;
import it.bibliotecaweb.service.ruolo.RuoloServiceImpl;
import it.bibliotecaweb.service.utente.UtenteService;
import it.bibliotecaweb.service.utente.UtenteServiceImpl;

public class MyServiceFactory {

	// implementiamo il singleton in modo da evitare
	// proliferazione di riferimenti
	private static AutoreService AUTORE_SERVICE_INSTANCE = null;
	private static AutoreDAO AUTOREDAO_INSTANCE = null;
	
	private static LibroService LIBRO_SERVICE_INSTANCE = null;
	private static LibroDAO LIBRODAO_INSTANCE = null;
	
	private static RuoloService RUOLO_SERVICE_INSTANCE = null;
	private static RuoloDAO RUOLODAO_INSTANCE = null;
	
	private static UtenteService UTENTE_SERVICE_INSTANCE = null;
	private static UtenteDAO UTENTEDAO_INSTANCE = null;

	public static AutoreService getAutoreServiceInstance() {
		if (AUTORE_SERVICE_INSTANCE == null)
			AUTORE_SERVICE_INSTANCE = new AutoreServiceImpl();

		if (AUTOREDAO_INSTANCE == null)
			AUTOREDAO_INSTANCE = new AutoreDAOImpl();

		AUTORE_SERVICE_INSTANCE.setAutoreDao(AUTOREDAO_INSTANCE);

		return AUTORE_SERVICE_INSTANCE;
	}
	
	public static LibroService getLibroServiceInstance() {
		if (LIBRO_SERVICE_INSTANCE == null)
			LIBRO_SERVICE_INSTANCE = new LibroServiceImpl();

		if (LIBRODAO_INSTANCE == null)
			LIBRODAO_INSTANCE = new LibroDAOImpl();

		LIBRO_SERVICE_INSTANCE.setLibroDao(LIBRODAO_INSTANCE);

		return LIBRO_SERVICE_INSTANCE;
	}
	
	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLODAO_INSTANCE == null)
			RUOLODAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDao(RUOLODAO_INSTANCE);

		return RUOLO_SERVICE_INSTANCE;
	}
	
	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTEDAO_INSTANCE == null)
			UTENTEDAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDao(UTENTEDAO_INSTANCE);

		return UTENTE_SERVICE_INSTANCE;
	}

}
