package edu.uniandes.service.daos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.service.entidades.Persona;
import edu.uniandes.service.entidades.Usuario;

@Stateless
@LocalBean
public class UsuarioDAO extends AbstractDAO<Usuario> {

	/**
	 * Default constructor.
	 */
	public UsuarioDAO() {
		super(Usuario.class);
	}

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Usuario getUsuario(String userName,boolean detach) {
		Usuario usuario = null;
		try {
			Query query = em.createQuery("select u from Usuario u where u.username= :user");
			query.setParameter("user", userName);
			usuario = (Usuario) query.getSingleResult();
			detachObject(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public List<Usuario> getUsuarioByIdPersona(Long personaId,boolean detach) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("select u from Usuario u where u.personaBean.id= :personaId");
			query.setParameter("personaId", personaId);
			usuarios = (List<Usuario>)query.getResultList();
			detachObject(usuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	public void crearUsuario(Usuario usuario, Persona persona)
	{
		usuario.setPersonaBean(persona);
		getEntityManager().persist(usuario);
	}
}
