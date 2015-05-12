package edu.uniandes.service.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.annotations.Feature;
import edu.uniandes.service.daos.MetodoEnvioDAO;
import edu.uniandes.service.entidades.MetodoEnvio;


@Path("/MetodoEnvio")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Feature(name="Pay")
public class MetodoEnvioResource {
	@EJB
	private MetodoEnvioDAO metodoEnvioDAO;
	@GET
	public List<MetodoEnvio> list(){
		return metodoEnvioDAO.findAll(true);
	}
}