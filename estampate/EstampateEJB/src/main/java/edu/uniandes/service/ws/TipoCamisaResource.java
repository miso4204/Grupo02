package edu.uniandes.service.ws;

import java.security.Principal;
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






import edu.uniandes.service.daos.TipoCamisetaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Estampa;
import edu.uniandes.service.entidades.TipoCamiseta;
import edu.uniandes.service.entidades.Usuario;
import edu.uniandes.service.util.Constantes;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Path("/TipoCamiseta")
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
}
