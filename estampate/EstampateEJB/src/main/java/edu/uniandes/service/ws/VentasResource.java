package edu.uniandes.service.ws;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.uniandes.service.daos.ColorCamisetaDAO;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.ColorCamiseta;
import edu.uniandes.service.entidades.Venta;
import edu.uniandes.service.patterns.RecaudoContraEntregaDAO;
import edu.uniandes.service.patterns.RecaudoPSEDAO;
import edu.uniandes.service.patterns.RecaudoTarjetaCreditoDAO;

/**
 * Implementa los m�todos requeridos para la gesti�n de ventas.
 * 
 * @author JuanCamilo
 * 
 */
@Path("/Venta")
@Stateless
@LocalBean
public class VentasResource{
	
	@EJB
	ColorCamisetaDAO colorCamisetaDAO;
	@EJB
	RecaudoTarjetaCreditoDAO recaudoTarjetaCreditoDAO;
	@EJB
	RecaudoPSEDAO recaudoPSEDAO;
	@EJB
	RecaudoContraEntregaDAO recaudoContraEntregaDAO;;

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
	
	@POST
	@Path("/Pagar")
	public CarritoCompra createVenta(CarritoCompra carrito) {
			System.out.println("+++++ Inicio createVenta");
			System.out.println(carrito.getId());
			
			if(carrito.getVentas().get(0).getMetodoPagoBean().getId() == 1)
			{
				recaudoPSEDAO.hacerPago(carrito);
			}
			else if(carrito.getVentas().get(0).getMetodoPagoBean().getId() == 2){
				recaudoTarjetaCreditoDAO.hacerPago(carrito);
			}
			else if(carrito.getVentas().get(0).getMetodoPagoBean().getId() == 3){
				recaudoContraEntregaDAO.hacerPago(carrito);
			}
		
		return carrito;
	}

}
