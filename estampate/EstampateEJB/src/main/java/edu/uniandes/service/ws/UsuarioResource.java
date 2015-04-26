package edu.uniandes.service.ws;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Usuario;

@Path("/Usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsuarioResource {
	@Resource
	private SessionContext context;
	@EJB 
	private UsuarioDAO usuarioDAO;
	@GET
	public Usuario getUsuario(){
		String user=context.getCallerPrincipal().getName();
		Usuario usuario=usuarioDAO.getUsuario(user, true);
		return usuario;
	}
	@PUT
	public void modificarUsuario(Usuario usuario){
		usuarioDAO.edit(usuario);
	}

}
