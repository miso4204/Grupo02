package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.Persona;

@Stateless
@LocalBean
public class PersonaDAO extends AbstractDAO<Persona> {

	/**
	 * Default constructor.
	 */
	public PersonaDAO() {
		super(Persona.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}