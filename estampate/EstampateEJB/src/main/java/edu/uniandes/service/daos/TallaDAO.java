package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.TallaCamiseta;

@Stateless
@LocalBean
public class TallaDAO extends AbstractDAO<TallaCamiseta> {

	/**
	 * Default constructor.
	 */
	public TallaDAO() {
		super(TallaCamiseta.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
