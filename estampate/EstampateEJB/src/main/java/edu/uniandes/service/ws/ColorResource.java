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
import edu.uniandes.service.daos.ColorDAO;
import edu.uniandes.service.entidades.ColorCamiseta;

@Path("/Color")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Feature(name="Color")
public class ColorResource {
	@EJB 
	ColorDAO colorDAO;
	@GET
	public List<ColorCamiseta> list(){
		return colorDAO.findAll(true);
	}
}
