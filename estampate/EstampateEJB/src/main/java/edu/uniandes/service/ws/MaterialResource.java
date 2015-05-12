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
import edu.uniandes.service.daos.MaterialDAO;
import edu.uniandes.service.entidades.MaterialCamiseta;

@Path("/Material")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Feature(name="ProductDesign")
public class MaterialResource {
	@EJB
	private MaterialDAO materialDAO;
	@GET
	public List<MaterialCamiseta> list(){
		return materialDAO.findAll(true);
	}
}
