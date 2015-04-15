package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import edu.uniandes.service.entidades.ColorCamiseta;

@Stateless
@LocalBean
public class ColorDAO extends AbstractDAO<ColorCamiseta>{	
	public ColorDAO() {
		super(ColorCamiseta.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
