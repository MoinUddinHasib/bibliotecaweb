package it.bibliotecaweb.dao.libro;

import java.util.Set;

import it.bibliotecaweb.dao.IBaseDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;

public interface LibroDAO extends IBaseDAO<Libro> {

	Set<Libro> prendiInsieme(String titolo, String genere, String trama, Autore a);

}
