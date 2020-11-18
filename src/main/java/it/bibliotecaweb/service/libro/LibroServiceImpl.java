package it.bibliotecaweb.service.libro;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.libro.LibroDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;

public class LibroServiceImpl implements LibroService {

	private LibroDAO libroDAO;

	@Override
	public Set<Libro> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Libro caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Libro o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			if(o.getTitolo()==null || o.getTitolo().isEmpty() || o.getGenere()==null
					|| o.getGenere().isEmpty() || o.getTrama()==null ||
					o.getTrama().isEmpty() || o.getAutore()==null ||
					libroDAO.list().contains(o)){
				throw new Exception("Errore in aggiorna libro");
			}
			// eseguo quello che realmente devo fare
			libroDAO.update(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Libro o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			if(o.getTitolo()==null || o.getTitolo().isEmpty() || o.getGenere()==null
					|| o.getGenere().isEmpty() || o.getTrama()==null ||
					o.getTrama().isEmpty() || o.getAutore()==null ||
					libroDAO.list().contains(o)){
				throw new Exception("Errore in inserisci libro");
			}
			// eseguo quello che realmente devo fare
			libroDAO.insert(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Libro o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			libroDAO.delete(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setLibroDao(LibroDAO libroDAO) {
		this.libroDAO=libroDAO;
	}

	@Override
	public void inserisciAutore(Libro l, Autore a) {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se è già presente quel ruolo non deve essere inserito
			l = entityManager.merge(l);
			a = entityManager.merge(a);
			
			l.setAutore(a);
			a.getLibri().add(l);
			//l'update non viene richiamato a mano in quanto 
			//risulta automatico, infatti il contesto di persistenza
			//rileva che utenteEsistente ora è dirty vale a dire che una sua
			//proprieta ha subito una modifica (vale anche per i Set ovviamente)

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Set<Libro> findByParameter(String titolo, String genere, String trama, Autore a) {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			libroDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return libroDAO.prendiInsieme(titolo,genere,trama,a);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

}
