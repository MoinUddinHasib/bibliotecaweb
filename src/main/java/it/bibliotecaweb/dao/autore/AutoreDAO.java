package it.bibliotecaweb.dao.autore;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Autore;

public interface AutoreDAO extends IBaseDAO<Autore> {

	Set<Autore> cercaInsieme(String nome, String cognome);

}
