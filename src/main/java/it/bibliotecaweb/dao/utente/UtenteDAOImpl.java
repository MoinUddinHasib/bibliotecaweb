package it.bibliotecaweb.dao.utente;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Ruolo;
import it.bibliotecaweb.model.Utente;
import it.bibliotecaweb.model.Utente.Stato;

public class UtenteDAOImpl implements UtenteDAO {

	private EntityManager entityManager;

	@Override
	public Set<Utente> list() throws Exception {
		return entityManager.createQuery("from Utente",Utente.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Utente get(Long id) throws Exception {
		return entityManager.find(Utente.class, id);
	}

	@Override
	public void update(Utente o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Utente o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Utente o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(o));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Utente findByUsername(String us) {		
		String q="from Utente u where u.username=?1";
		TypedQuery<Utente> query = entityManager.createQuery(q,Utente.class);
		List<Utente> ris=query.setParameter(1, us).getResultList();
		return ris.isEmpty()?null:ris.get(0);
	}

	@Override
	public Set<Utente> cercaInsieme(Utente u) {
		String q="select distinct u from Utente u join u.ruoli r where (u.nome like ?1 or ?1 = null) and (u.cognome like ?2 or ?2 = null)"
				+ " and (u.username like ?3 or ?3 = null) and (r.id = ?4 or ?4 = null) and (u.stato = ?5 or ?5 = null)";
		TypedQuery<Utente> query=entityManager.createQuery(q,Utente.class);
		String nome=u.getNome();
		String cognome=u.getCognome();
		String username=u.getUsername();
		Long ruolo=u.getRuoli().size()==0?null:((Ruolo)u.getRuoli().toArray()[0]).getId();
		Stato stato=u.getStato();
		
		query.setParameter(1, (nome==null || nome.isEmpty())?null:"%"+nome+"%");
		query.setParameter(2, (cognome==null || cognome.isEmpty())?null:"%"+cognome+"%");
		query.setParameter(3, (username==null || username.isEmpty())?null:"%"+username+"%");
		query.setParameter(4, ruolo);
		query.setParameter(5, stato);
		return query.getResultList().stream().collect(Collectors.toSet());
	}


}
