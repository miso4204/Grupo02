package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import edu.uniandes.service.entidades.MetodoEnvio;

@Stateless
@LocalBean
public class MetodoEnvioDAO extends AbstractDAO<MetodoEnvio>{
	public MetodoEnvioDAO() {
		super(MetodoEnvio.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
