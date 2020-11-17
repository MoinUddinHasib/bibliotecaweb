package it.bibliotecaweb.service.autore;

import it.bibliotecaweb.dao.autore.AutoreDAO;
import it.bibliotecaweb.model.Autore;
import it.bibliotecaweb.service.IBaseService;

public interface AutoreService extends IBaseService<Autore>{
	
	// questo mi serve per injection
	public void setAutoreDao(AutoreDAO autoreDao);


}
