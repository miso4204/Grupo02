package edu.uniandes.service.ws;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uniandes.service.daos.CarritoDAO;
import edu.uniandes.service.daos.UsuarioDAO;
import edu.uniandes.service.entidades.Camiseta;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.Usuario;

/**
 * Servicio encargado de manejar el carrito de compras y la creacion de los items dentro de el.
 * @author vp.salcedo93
 */
@Path("/Carrito")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class CarritoResource {
	@Resource
	private SessionContext context;
	@EJB
	private CarritoDAO carritoDAO;
	@EJB
	private UsuarioDAO usuarioDAO;
	/**
	 * Metodo que obtiene la lista de items del carrito de compras de un usuario
	 * @return Lista de items(camiseta personalizadas) del carrito de un usuario
	 */
	@GET
	public List<Camiseta> listByUser(){
		List<Camiseta> items=null;
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
		System.out.println("Usuario encontrado");
		items=carritoDAO.getItemsByUser(usuario);
		System.out.println("Obtuvo lista de camisas.");
		return items;
	}
	@GET
	@Path("/ByUser")
	public CarritoCompra obtenerCarritoPorUsuario(){
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
		CarritoCompra carrito = carritoDAO.getCarritoPorUsuario(usuario);
		return carrito;
	}
	/**
	 * ADICIONA UNA CAMISETA COMO ITEM AL CARRITO
	 * @param camiseta
	 */
	@PUT
	public void addItem(Camiseta camiseta){
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
		carritoDAO.addItemByUser(usuario,camiseta);
	}
	/**
	 * ELIMINA UNA CAMISETA COMO ITEM AL CARRITO DE COMPRAS
	 * @param camiseta
	 */
	@PUT
	@Path("/Delete")
	public void removeItem(Camiseta camiseta){
		Principal principal=context.getCallerPrincipal();
		Usuario usuario=usuarioDAO.getUsuario(principal.getName(),false);
		carritoDAO.removeItemByUser(usuario, camiseta);
	}
	@PUT
	@Path("/ByCarrito")
	public CarritoCompra obtenerCarrito(CarritoCompra carrito){
		return carritoDAO.getCarritoPorId(carrito.getId(),true);
	}
}
