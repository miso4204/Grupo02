package edu.uniandes.service.ws;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.PersonaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Persona;
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
	@EJB
	private PersonaDAO personaDAO;
	@GET
	public Usuario getUsuario(){
		String user=context.getCallerPrincipal().getName();
		Usuario usuario=usuarioDAO.getUsuario(user, true);
		return usuario;
	}
	@GET
	@Path("/ByPersona/{id}")
	public Usuario getUsuarioPorPersona(@PathParam("id") Long id){			
		return usuarioDAO.getUsuarioByIdPersona(id, true).get(0);
	}
	
	@POST
	public void create(Usuario usuario) {
		Persona persona = new Persona();
		//System.out.println("+++++ Inicio create");
		//System.out.println("username: " + usuario.getUsername());
		//System.out.println("password: " + usuario.getPassword());
		//System.out.println("identificacion: " + usuario.getPersonaBean().getIdentificacion());
		persona = personaDAO.getPersonaPorIdentifica(usuario.getPersonaBean().getIdentificacion(), true);
		//System.out.println("persona nombre: " + persona.getNombres());
		usuario.setPersonaBean(persona);
		//System.out.println("+++++ Fin create");
		usuarioDAO.create(usuario);	
	}
	
	@POST
	@Path("/UsuarioRol")
	public void createUsuarioRol(Usuario usuario) {
		Persona persona = new Persona();
		//System.out.println("+++++ Inicio create");
		//System.out.println("username: " + usuario.getUsername());
		//System.out.println("password: " + usuario.getPassword());
		//System.out.println("Rol: " + usuario.getRolBean().getId());
		//System.out.println("identificacion: " + usuario.getPersonaBean().getIdentificacion());
		persona = personaDAO.getPersonaPorIdentifica(usuario.getPersonaBean().getIdentificacion(), true);
		//System.out.println("persona nombre: " + persona.getNombres());
		usuario.setPersonaBean(persona);
		//System.out.println("+++++ Fin create");
		usuarioDAO.create(usuario);	
	}
	
	@PUT
	public void modificarUsuario(Usuario usuario){
		usuarioDAO.edit(usuario);
	}

}
