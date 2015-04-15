package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.MaterialCamiseta;

@Stateless
@LocalBean
public class MaterialDAO extends AbstractDAO<MaterialCamiseta>{
	public MaterialDAO() {
		super(MaterialCamiseta.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
