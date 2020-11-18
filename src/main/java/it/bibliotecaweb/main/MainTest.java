package it.bibliotecaweb.main;

import it.bibliotecaweb.dao.EntityManagerUtil;

/**
 * Hello world!
 *
 */
public class MainTest 
{
    public static void main( String[] args )
    {/*
    	RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
    	AutoreService autoreServiceInstance = MyServiceFactory.getAutoreServiceInstance();
    	
    	LibroService libroServiceInstance = MyServiceFactory.getLibroServiceInstance();  	
    	UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
		*/
		try {/*
			Ruolo r1=new Ruolo(Codice.ADMIN_ROLE, "amministratore");
			Ruolo r2=new Ruolo(Codice.CLASSIC_ROLE, "utente_registrato");
			Ruolo r3=new Ruolo(Codice.GUEST_ROLE, "ospite");
			ruoloServiceInstance.inserisciNuovo(r1);
			ruoloServiceInstance.inserisciNuovo(r2);
			ruoloServiceInstance.inserisciNuovo(r3);*/
			/*
			Ruolo r1=ruoloServiceInstance.caricaSingoloElemento(1l);
			Utente u1=new Utente("Hasib","Moin Uddin", "hercules", "pass");
			utenteServiceInstance.inserisciRuolo(u1, r1);*/
			/*
			Date d1=new Date(96,0,22);//21/01/1996
			Autore a1 = new Autore("n1", "c1", d1);
			System.out.println(a1);
			autoreServiceInstance.inserisciNuovo(a1);*/
			/*
			Libro l1=new Libro("t1", "g1", "t1");
			Autore a1=autoreServiceInstance.caricaSingoloElemento(7l);		
			libroServiceInstance.inserisciAutore(l1,a1);*/

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
    }
}
