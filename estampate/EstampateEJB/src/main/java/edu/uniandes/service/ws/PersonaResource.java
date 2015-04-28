package edu.uniandes.service.ws;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.daos.PersonaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Estampa;
import edu.uniandes.service.entidades.Persona;
import edu.uniandes.service.entidades.Usuario;
import edu.uniandes.service.util.Constantes;


@Path("/Persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class PersonaResource {
	@Resource
	private SessionContext context;
	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private PersonaDAO personaDAO;
	
	@GET
	@Path("/All")
	public List<Persona> list(){
		return personaDAO.findAll(true);
	}
	
	@GET
	public Persona getByUser() {
		String userName = context.getCallerPrincipal().getName();
		return usuarioDAO.getUsuario(userName, true).getPersonaBean();
	}

	@PUT
	public void edit(Persona persona) {
		personaDAO.edit(persona);		
	}
	
	@POST
	public void create(Persona persona) {
		personaDAO.create(persona);		
	}
	
	@DELETE
	public void delete(Persona estampa){
		personaDAO.remove(estampa);
	}
}
