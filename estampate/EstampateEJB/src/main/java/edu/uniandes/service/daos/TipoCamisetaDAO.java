package edu.uniandes.service.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.service.entidades.Estampa;
import edu.uniandes.service.entidades.TipoCamiseta;
import edu.uniandes.service.entidades.Usuario;
@Stateless
@LocalBean
public class TipoCamisetaDAO extends AbstractDAO<TipoCamiseta> {
	
	public TipoCamisetaDAO() {
		super(TipoCamiseta.class);
	}
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}	
}
