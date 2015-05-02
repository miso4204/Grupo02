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
	
	public Persona getPersonaPorIdentifica(String identificacion,boolean detach) {
		Persona persona = null;
		try {
			System.out.println("Imprime identificación:");
			System.out.println(identificacion);
			Query query = em.createQuery("select p from Persona p where p.identificacion= :identificacion");
			query.setParameter("identificacion", identificacion);
			persona = (Persona)query.getSingleResult();

			System.out.println("Imprime consulta:");
			System.out.println(persona.getNombres());
			
			detachObject(persona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return persona;
	}
}