package edu.uniandes.service.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.TemaDAO;
import edu.uniandes.service.entidades.Tema;

@Path("/Tema")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TemaResource {
	@EJB 
	private TemaDAO temaDAO;
	@GET
	public List<Tema> list(){
		return temaDAO.findAll(true);
	}
}
