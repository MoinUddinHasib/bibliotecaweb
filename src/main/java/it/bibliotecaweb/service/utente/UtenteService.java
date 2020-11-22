package it.bibliotecaweb.service.utente;

import java.util.Set;

import it.bibliotecaweb.dao.utente.UtenteDAO;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.service.IBaseService;

public interface UtenteService extends IBaseService<Utente>{
	
	// questo mi serve per injection
	public void setUtenteDao(UtenteDAO utenteDao);
	
	public Utente caricaPerUsername(String us);
	
	public Utente inserisciRuolo(Utente u,Ruolo r) throws Exception;

	public Set<Utente> findByParameter(Utente u);

}
