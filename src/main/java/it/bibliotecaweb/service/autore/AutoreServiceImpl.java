package it.bibliotecaweb.service.autore;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;

public class AutoreServiceImpl implements AutoreService {
	
	private AutoreDAO autoreDAO;

	@Override
	public Set<Autore> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.list();

		} catch (Exception e) {
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Autore caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.get(id);

		} catch (Exception e) {
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Autore o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);
			if(o.getNome()==null || o.getNome().isEmpty() || o.getCognome()==null
					|| o.getCognome().isEmpty() || o.getData_di_nascita()==null || 
					autoreDAO.list().contains(o)){
				throw new Exception("Errore in aggiorna autore");
			}
			// eseguo quello che realmente devo fare
			autoreDAO.update(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Autore o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			if(o.getNome()==null || o.getNome().isEmpty() || o.getCognome()==null
					|| o.getCognome().isEmpty() || o.getData_di_nascita()==null ||
					autoreDAO.list().contains(o)){
				throw new Exception("Errore in inserisci autore");
			}
			
			// eseguo quello che realmente devo fare
			autoreDAO.insert(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void rimuovi(Autore o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			if(o.getLibri().size()!=0){
				throw new Exception("Errore in rimuovi autore");
			}
			// eseguo quello che realmente devo fare
			autoreDAO.delete(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void setAutoreDao(AutoreDAO autoreDAO) {
		this.autoreDAO=autoreDAO;
	}

	@Override
	public Set<Autore> findByParameter(Autore a) {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			autoreDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return autoreDAO.cercaInsieme(a);

		} catch (Exception e) {
			throw e;
		} finally {
			entityManager.close();
		}
	}
	

}
