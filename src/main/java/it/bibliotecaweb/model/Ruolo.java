package it.bibliotecaweb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ruolo")
public class Ruolo {
	
	public enum Codice{
		ADMIN_ROLE,
		CLASSIC_ROLE,
		GUEST_ROLE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "codice")
	private Codice codice;
	@Column(name = "descrizione")
	private String descrizione;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private Set<Utente> utenti= new HashSet<>();
	
	public Ruolo() {
		super();
	}
	public Ruolo(Codice codice, String descrizione) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Codice getCodice() {
		return codice;
	}
	public void setCodice(Codice codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Set<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}
	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", utenti=" + utenti.size() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.toLowerCase().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruolo other = (Ruolo) obj;
		if (codice != other.codice)
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!(descrizione.toLowerCase()).equals(other.descrizione.toLowerCase()))
			return false;
		return true;
	}
	
	
}


