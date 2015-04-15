package edu.uniandes.service.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.service.entidades.Privilegio;

/**
 * Session Bean implementation class PrivilegioDAO
 */
@Stateless
@LocalBean
public class PrivilegioDAO  extends AbstractDAO<Privilegio>{

    /**
     * Default constructor. 
     */
    public PrivilegioDAO() {
        super(Privilegio.class);
    }
    
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<Privilegio> list(String userName,boolean detach){
		StringBuilder psql=new StringBuilder();
		psql.append("select rp.privilegio from Usuario u,RolPrivilegio rp");
		psql.append(" where u.rolBean=rp.rol and");
		psql.append("       u.username= :usuario");
		Query query= em.createQuery(psql.toString(),Privilegio.class);
		query.setParameter("usuario", userName);
		List<Privilegio> privilegios=query.getResultList();
		if(detach){
			detachList(privilegios);
		}		
		return privilegios;
	}
	
}
