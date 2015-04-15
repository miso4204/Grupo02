package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.Camiseta;

@Stateless
@LocalBean
public class CamisetaDAO extends AbstractDAO<Camiseta>{
	
	public CamisetaDAO() {
		super(Camiseta.class);
	}
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	public Camiseta getCamiseta(){
		return null;
	}

	
}
