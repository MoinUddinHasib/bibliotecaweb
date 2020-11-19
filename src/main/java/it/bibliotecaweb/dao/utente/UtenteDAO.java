package it.bibliotecaweb.dao.utente;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public Utente findByUsername(String us);

	public Set<Utente> cercaInsieme(String nome, String cognome, String username, Ruolo ruolo, String stato);

}
