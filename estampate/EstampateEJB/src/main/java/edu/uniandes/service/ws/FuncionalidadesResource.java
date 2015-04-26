package edu.uniandes.service.ws;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ejb.SessionContext;


import edu.uniandes.service.daos.PrivilegioDAO;
import edu.uniandes.service.entidades.Privilegio;

@Path("/Funcionalidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class FuncionalidadesResource {
	@Resource
	private SessionContext context;
	@EJB
	private PrivilegioDAO privilegioDAO;
	@GET	
	public List<Privilegio> list(){
		Principal principal=context.getCallerPrincipal();
		String user=principal.getName();
		List<Privilegio> privilegios=privilegioDAO.list(user,true);
		return privilegios;
	}
}
