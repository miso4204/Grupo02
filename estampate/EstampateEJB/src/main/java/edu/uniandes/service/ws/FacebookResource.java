package edu.uniandes.service.ws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.annotations.Feature;
import edu.uniandes.service.resources.CaracteristicasConf;

@Path("/facebook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
@Feature(name="Facebook")
public class FacebookResource {

	@GET
	@Path("/habilitado")
	public boolean compartirFacebook() {
		CaracteristicasConf caracteristicasConf = new CaracteristicasConf();
		if (caracteristicasConf.existeCaracteristica("Facebook")) {
			return true;
		}else {
			return false;
		}
		
	}
}
