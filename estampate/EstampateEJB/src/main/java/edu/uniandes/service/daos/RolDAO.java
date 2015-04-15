package edu.uniandes.service.daos;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.Rol;

@Stateless
@LocalBean
public class RolDAO  extends AbstractDAO<Rol>{

    /**
     * Default constructor. 
     */
    public RolDAO() {
        super(Rol.class);
    }
    
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
}
