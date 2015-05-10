package edu.uniandes.service.ws;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uniandes.service.derivacion.enums.Caracteristicas;

@Path("/featuresApp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
public class FeaturesAppResource {

	@POST
	@Path("/setFeatures")
	public Response setFeatures(String features){
		System.out.println("ENTRO AL SERVICIO WEB: "+features);
		if (features.contains(Caracteristicas.REDES_SOCIALES.toString())) {
			System.out.println("La configuracion tiene REDES_SOCIALES");
			Caracteristicas.REDES_SOCIALES.setSeleccionada(Boolean.TRUE);
		}
		if (features.contains(Caracteristicas.FACEBOOK.toString())) {
			System.out.println("La configuracion tiene FACEBOOK");
			Caracteristicas.FACEBOOK.setSeleccionada(Boolean.TRUE);
		}
		if (features.contains(Caracteristicas.TWITTER.toString())) {
			System.out.println("La configuracion tiene TWITTER");
			Caracteristicas.TWITTER.setSeleccionada(Boolean.TRUE);
		}
		return Response.status(201).entity(features).build();
	}
}
