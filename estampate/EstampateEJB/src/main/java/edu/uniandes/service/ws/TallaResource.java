package edu.uniandes.service.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.TallaDAO;
import edu.uniandes.service.entidades.TallaCamiseta;

@Path("/Talla")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TallaResource {
	@EJB
	TallaDAO tallaDAO;
	@GET
	public List<TallaCamiseta> list(){
		return tallaDAO.findAll(true);
	}

}
