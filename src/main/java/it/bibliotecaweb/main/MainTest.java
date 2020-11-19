package it.bibliotecaweb.main;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Ruolo.Codice;
import it.bibliotecaweb.service.MyServiceFactory;
import it.bibliotecaweb.service.ruolo.RuoloService;

/**
 * Hello world!
 *
 */
public class MainTest 
{
    public static void main( String[] args )
    {
    	RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		
		try {
			Ruolo r1=new Ruolo(Codice.ADMIN_ROLE, "amministratore");
			Ruolo r2=new Ruolo(Codice.CLASSIC_ROLE, "utente_registrato");
			Ruolo r3=new Ruolo(Codice.GUEST_ROLE, "ospite");
			ruoloServiceInstance.inserisciNuovo(r1);
			ruoloServiceInstance.inserisciNuovo(r2);
			ruoloServiceInstance.inserisciNuovo(r3);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
    }
}
