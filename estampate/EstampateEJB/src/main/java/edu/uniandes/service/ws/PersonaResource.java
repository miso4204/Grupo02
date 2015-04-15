package edu.uniandes.service.ws;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Persona;

@Path("/Persona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class PersonaResource {
	@Resource
	private SessionContext context;
	@EJB
	private UsuarioDAO usuarioDAO;
	@GET
	@Path("ByUser")
	public Persona getByUser(){
		String userName=context.getCallerPrincipal().getName();		
		return usuarioDAO.getUsuario(userName,true).getPersonaBean();
	}	
}
