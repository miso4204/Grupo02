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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.annotations.Feature;
import edu.uniandes.service.daos.PersonaDAO;
import edu.uniandes.service.daos.RolDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Persona;
import edu.uniandes.service.entidades.Rol;
import edu.uniandes.service.util.Constantes;

@Path("/Rol")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
@Feature(name="User")
public class RolResource {
	@Resource
	private SessionContext context;
	@EJB
	private RolDAO rolDAO;
	
	@GET
	public Rol getById(@PathParam("id") Long id){			
		Rol rol = rolDAO.find(id,true);
		return rol;
	}
	
}
