package edu.uniandes.service.ws;

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

import edu.uniandes.annotations.Feature;
import edu.uniandes.service.daos.TipoCamisetaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.TipoCamiseta;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Path("/TipoCamiseta")
@Feature(name="ProductAdmin")
public class TipoCamisaResource {
	@Resource
	private SessionContext context;
	@EJB
	private TipoCamisetaDAO  tipoCamisetaDAO;
	@EJB
	private UsuarioDAO usuarioDAO;
	@GET
	public List<TipoCamiseta> list(){
		return tipoCamisetaDAO.findAll(true);
	}	
	@POST
	public void create(TipoCamiseta tipoCamiseta){
		tipoCamisetaDAO.create(tipoCamiseta);
	}
	@PUT
	public void edit(TipoCamiseta tipoCamiseta){
		tipoCamisetaDAO.edit(tipoCamiseta);
	}
	@DELETE
	public void delete(TipoCamiseta tipoCamiseta){
		tipoCamisetaDAO.remove(tipoCamiseta);
	}
}
