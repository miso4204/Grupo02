package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.Tema;
@Stateless
@LocalBean
public class TemaDAO extends AbstractDAO<Tema>{

	/**
	 * Default constructor.
	 */
	public TemaDAO() {
		super(Tema.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
