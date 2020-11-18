package it.bibliotecaweb.service.ruolo;

import java.util.Set;

import javax.persistence.EntityManager;

import it.bibliotecaweb.dao.EntityManagerUtil;
import it.bibliotecaweb.dao.ruolo.RuoloDAO;
import it.bibliotecaweb.model.Ruolo;

public class RuoloServiceImpl implements RuoloService {

	private RuoloDAO ruoloDAO;

	@Override
	public Set<Ruolo> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void aggiorna(Ruolo o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);
			if(o.getCodice()==null || o.getDescrizione()==null || o.getDescrizione().isEmpty()
					|| ruoloDAO.list().contains(o) ) {
				throw new Exception("Errore in aggiorna ruolo");
			}
			// eseguo quello che realmente devo fare
			ruoloDAO.update(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void inserisciNuovo(Ruolo o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			if(o.getCodice()==null || o.getDescrizione()==null || o.getDescrizione().isEmpty()
					|| ruoloDAO.list().contains(o)) {
				throw new Exception("Errore in inserisci ruolo");
			}
			// eseguo quello che realmente devo fare
			ruoloDAO.insert(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void rimuovi(Ruolo o) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);

			if(o.getUtenti().size()>0) {
				throw new Exception("Errore in rimuovi ruolo");
			}
			// eseguo quello che realmente devo fare
			ruoloDAO.delete(o);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setRuoloDao(RuoloDAO ruoloDAO) {
		this.ruoloDAO=ruoloDAO;
	}
}
