package edu.uniandes.service.ws;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import edu.uniandes.annotations.Feature;
import edu.uniandes.service.daos.CarritoDAO;
import edu.uniandes.service.daos.ColorCamisetaDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.ColorCamiseta;
import edu.uniandes.service.entidades.MetodoEnvio;
import edu.uniandes.service.entidades.MetodoPago;
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
@Feature(name="Pay")
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
	public void createVenta(String cadena) {
			System.out.println("+++++ Inicio createVenta");
			System.out.println(cadena);

			List<Venta> ventas = new ArrayList<Venta>();
			Venta venta = new Venta();
			MetodoEnvio metodoEnvio = new MetodoEnvio();
			MetodoPago metodoPago = new MetodoPago();
			Date fecha = new Date();
			String[] temp;
			temp = cadena.split("|");
			
			Long metodoEnvioId = Long.parseLong(temp[1]);
			Long medioPagoId = Long.parseLong(temp[3]);

			Principal principal=context.getCallerPrincipal();
			Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
			CarritoCompra carrito = carritoDAO.getCarritoPorUsuario(usuario);

			metodoPago.setId(medioPagoId);
			metodoEnvio.setId(metodoEnvioId);
			
			venta.setId(0);
			venta.setFecha(fecha);
			venta.setCarritoCompra(carrito);
			venta.setMetodoEnvioBean(metodoEnvio);
			venta.setMetodoPagoBean(metodoPago);
			
			carrito.setVentas(ventas);
			carrito.addVenta(venta);

			if(metodoEnvioId == 1)
			{
				System.out.println("equals recaudoPSEDAO ");
				recaudoPSEDAO.hacerPago(carrito);
			}
			else if(metodoEnvioId == 2){
				System.out.println("equals recaudoTarjetaCreditoDAO ");
				recaudoTarjetaCreditoDAO.hacerPago(carrito);
			}
			else if(metodoEnvioId == 3){
				System.out.println("equals recaudoContraEntregaDAO ");
				recaudoContraEntregaDAO.hacerPago(carrito);
			}
			System.out.println("+++++ Fin");
	}

}
