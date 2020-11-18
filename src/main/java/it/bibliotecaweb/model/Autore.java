package it.bibliotecaweb.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "autore")
public class Autore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "data_di_nascita")
	private LocalDate data_di_nascita;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autore")
	private Set<Libro> libri = new HashSet<>();
	
	public Autore() {
		super();
	}
	public Autore(String nome, String cognome, LocalDate data_di_nascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.data_di_nascita = data_di_nascita;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getData_di_nascita() {
		return data_di_nascita;
	}
	public void setData_di_nascita(LocalDate data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}
	public Set<Libro> getLibri() {
		return libri;
	}
	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}
	@Override
	public String toString() {
		return "Autore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", data_di_nascita=" + data_di_nascita
				+ ", libri=" + libri.size() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.toLowerCase().hashCode());
		result = prime * result + ((data_di_nascita == null) ? 0 : data_di_nascita.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.toLowerCase().hashCode());
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
		Autore other = (Autore) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!(cognome.toLowerCase()).equals(other.cognome.toLowerCase()))
			return false;
		if (data_di_nascita == null) {
			if (other.data_di_nascita != null)
				return false;
		} else if (!data_di_nascita.equals(other.data_di_nascita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!(nome.toLowerCase()).equals(other.nome.toLowerCase()))
			return false;
		return true;
	}
	

}
