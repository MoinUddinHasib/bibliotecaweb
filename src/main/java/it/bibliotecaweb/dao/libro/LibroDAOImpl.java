package it.bibliotecaweb.dao.libro;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.model.Libro;

public class LibroDAOImpl implements LibroDAO {

	private EntityManager entityManager;

	@Override
	public Set<Libro> list() throws Exception {
		return entityManager.createQuery("from Libro",Libro.class).getResultList().stream().collect(Collectors.toSet());
	}

	@Override
	public Libro get(Long id) throws Exception {
		return entityManager.find(Libro.class, id);
	}

	@Override
	public void update(Libro o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		o = entityManager.merge(o);
	}

	@Override
	public void insert(Libro o) throws Exception {
		if (o == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(o);
	}

	@Override
	public void delete(Libro o) throws Exception {
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
	public Set<Libro> prendiInsieme(Libro l) {
		String q ="from Libro l where (l.titolo like ?1 or ?1 = null) and (l.genere like ?2 or ?2 = null) and (l.trama like ?3 or ?3 = null) and (l.autore.id=?4 or ?4 = null)";
		TypedQuery<Libro> query=entityManager.createQuery(q,Libro.class);
		String titolo=l.getTitolo();
		String genere=l.getGenere();
		String trama=l.getTrama();
		Autore a=l.getAutore();
		query.setParameter(1, titolo.isEmpty()?null:"%"+titolo+"%");
		query.setParameter(2, genere.isEmpty()?null:"%"+genere+"%");
		query.setParameter(3, trama.isEmpty()?null:"%"+trama+"%");
		query.setParameter(4, a==null?null:a.getId());
		return query.getResultList().stream().collect(Collectors.toSet());
	}
}
