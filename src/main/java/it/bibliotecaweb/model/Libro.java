package it.bibliotecaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "genere")
	private String genere;
	@Column(name = "trama")
	private String trama;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autore_fk_id")
	private Autore autore;
	
	public Libro() {
		super();
	}

	public Libro(String titolo, String genere, String trama) {
		super();
		this.titolo = titolo;
		this.genere = genere;
		this.trama = trama;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", genere=" + genere + ", trama=" + trama + ", autore="
				+ autore + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((genere == null) ? 0 : genere.toLowerCase().hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.toLowerCase().hashCode());
		result = prime * result + ((trama == null) ? 0 : trama.toLowerCase().hashCode());
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
		Libro other = (Libro) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!(genere.toLowerCase()).equals(other.genere.toLowerCase()))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!(titolo.toLowerCase()).equals(other.titolo.toLowerCase()))
			return false;
		if (trama == null) {
			if (other.trama != null)
				return false;
		} else if (!(trama.toLowerCase()).equals(other.trama.toLowerCase()))
			return false;
		return true;
	}	
	

}
