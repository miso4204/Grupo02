package edu.uniandes.service.ws;

import java.security.Principal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.CarritoDAO;
import edu.uniandes.service.daos.ColorCamisetaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.ColorCamiseta;
import edu.uniandes.service.entidades.Usuario;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
@LocalBean
public class VentasResource{
	@Resource
	private SessionContext context;
	@EJB
	ColorCamisetaDAO colorCamisetaDAO;
	@EJB
	RecaudoTarjetaCreditoDAO recaudoTarjetaCreditoDAO;
	@EJB
	RecaudoPSEDAO recaudoPSEDAO;
	@EJB
	RecaudoContraEntregaDAO recaudoContraEntregaDAO;
	@EJB
	private CarritoDAO carritoDAO;
	@EJB
	private UsuarioDAO usuarioDAO;

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
	public CarritoCompra createVenta(String cadena) {
			System.out.println("+++++ Inicio createVenta");
			System.out.println(cadena);
			String[] temp;
			temp = cadena.split("|");
			Long metodoEnvioId = Long.parseLong(temp[0]);
			Long medioPagoId = Long.parseLong(temp[0]);
			
			Principal principal=context.getCallerPrincipal();
			Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
			CarritoCompra carrito = carritoDAO.getCarritoPorUsuario(usuario);
			
			Venta venta = new Venta();
			
			//if(carrito.getVentas().get(0).getMetodoPagoBean().getId() == 1)
			if(metodoEnvioId.equals("1"))
			{
				//recaudoPSEDAO.hacerPago(carrito);
			}
			//else if(carrito.getVentas().get(0).getMetodoPagoBean().getId() == 2){
			else if(metodoEnvioId.equals("2")){
				//recaudoTarjetaCreditoDAO.hacerPago(carrito);
			}
			else if(metodoEnvioId.equals("3")){
				//recaudoContraEntregaDAO.hacerPago(carrito);
			}
			System.out.println("+++++ Fin");
		return carrito;
	}

}
