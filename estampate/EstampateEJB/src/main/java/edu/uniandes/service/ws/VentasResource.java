package edu.uniandes.service.ws;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.uniandes.service.daos.ColorCamisetaDAO;
import edu.uniandes.service.entidades.ColorCamiseta;

/**
 * Implementa los m�todos requeridos para la gesti�n de ventas.
 * 
 * @author JuanCamilo
 * 
 */
@Path("/ventas")
@Stateless
@LocalBean
public class VentasResource{
	
	@EJB
	ColorCamisetaDAO colorCamisetaDAO;

	/* (non-Javadoc)
	 * @see edu.estampate.service.ws.IVentasService#getColorCamiseta(long)
	 */
	
	@GET
	@Path("/getColorCamiseta/{id}")
	public ColorCamiseta getColorCamiseta(@PathParam("id") long id) {
		return colorCamisetaDAO.getColorCamiseta(id);
	}

	/* (non-Javadoc)
	 * @see edu.estampate.service.ws.IVentasService#insertarColorCamiseta(edu.estampate.service.entidades.ColorCamiseta)
	 */
	@POST
	public void insertarColorCamiseta(ColorCamiseta colorCamiseta) {
		colorCamisetaDAO.insertarColorCamiseta(colorCamiseta);
	}

}
