package it.bibliotecaweb.dao.autore;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Autore;

public class AutoreDAOImpl implements AutoreDAO {
	
	private EntityManager entityManager;

	@Override
	public Set<Autore> list() throws Exception {
		return entityManager.createQuery("from Autore",Autore.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Autore get(Long id) throws Exception {
		return entityManager.find(Autore.class, id);
	}

	@Override
	public void update(Autore o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Autore o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Autore o) throws Exception {
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
	public Set<Autore> cercaInsieme(String nome, String cognome) {
		String q ="from Autore a where (a.nome like ?1 or ?1 =null) and (a.cognome like ?2 or ?2 = null)";
		TypedQuery<Autore> query=entityManager.createQuery(q,Autore.class);
		query.setParameter(1, nome.isEmpty()?null:"%"+nome+"%");
		query.setParameter(2, cognome.isEmpty()?null:"%"+cognome+"%");

		return query.getResultList().stream().collect(Collectors.toSet());
	}

}
