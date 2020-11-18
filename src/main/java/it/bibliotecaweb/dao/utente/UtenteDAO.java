package it.bibliotecaweb.dao.utente;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Utente findByUsername(String us);

}
