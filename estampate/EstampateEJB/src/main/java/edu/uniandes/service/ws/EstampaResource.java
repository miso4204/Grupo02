package edu.uniandes.service.ws;

import java.security.Principal;
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
import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Estampa;
import edu.uniandes.service.entidades.Usuario;
import edu.uniandes.service.util.Constantes;

@Path("/Estampa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Feature(name="StampDesign")
public class EstampaResource {
	@Resource
	private SessionContext context;
	@EJB
	private EstampaDAO estampaDAO;
	@EJB
	private UsuarioDAO usuarioDAO;
	@GET	
	public List<Estampa> list(){
		return estampaDAO.findAll(true);
	}
	@GET
	@Path("/User")
	public List<Estampa> listByUser(){
		List<Estampa> estampas=null;
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
		if(Constantes.ADMIN_ROL.equals(usuario.getRolBean().getNombre())){
			estampas=estampaDAO.findAll(true);
		}else {
			estampas=estampaDAO.getByUser(usuario,true);
		}
		return estampas;
	}
	@GET
	@Path("{id}")
	public Estampa getById(@PathParam("id") Long id){			
		return estampaDAO.find(id,true);
	}
	@POST
	public void create(Estampa estampa){
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),true);
		estampa.setUsuarioBean(usuario);
		estampaDAO.create(estampa);
	}
	@PUT
	public void edit(Estampa estampa){
		estampaDAO.edit(estampa);
	}
	@Path("{id}")
	@DELETE
	public void delete(@PathParam("id") Long id){
		try{
			estampaDAO.remove(estampaDAO.find(id, false));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
