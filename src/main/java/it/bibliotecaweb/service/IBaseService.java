package it.bibliotecaweb.service;

import java.util.Set;

public interface IBaseService<T> {

	public Set<T> listAll() throws Exception;

	public T caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(T o) throws Exception;

	public void inserisciNuovo(T o) throws Exception;

	public void rimuovi(T o) throws Exception;

}
