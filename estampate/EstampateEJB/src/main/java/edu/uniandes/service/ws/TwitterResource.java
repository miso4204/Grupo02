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

/**
 * Administración de integración con facebook
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 *
 */
@Path("/twitter")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
@Feature(name="Twitter")
public class TwitterResource {

	@GET
	@Path("/habilitado")
	public boolean name() {
		CaracteristicasConf caracteristicasConf = new CaracteristicasConf();
		if (caracteristicasConf.existeCaracteristica("Twitter")) {
			return true;
		}else {
			return false;
		}
	}
	
}
