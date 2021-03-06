package edu.uniandes.service.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.service.entidades.Camiseta;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.Usuario;


@Stateless
@LocalBean
public class CarritoDAO extends AbstractDAO<CarritoCompra>{
	public CarritoDAO() {
		super(CarritoCompra.class);
	}
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	/**
	 * Metodo que obtiene el carrito de compras de un usuario
	 * @param usuario. Usuario previamente logeado en el sistema
	 * @return CarritoCompra. En caso de que no exista se crea.
	 */
	public CarritoCompra getByUser(Usuario usuario){
		CarritoCompra carrito =null;
		Query query=em.createQuery("select e from CarritoCompra e  where e.usuarioBean= :usuario");
		query.setParameter("usuario",usuario);
		List<CarritoCompra> lista=query.getResultList();
		if(lista.size()== 0)
		{
			System.out.println("se va a crear");
			carrito=new CarritoCompra();
			System.out.println("ahora grega usuario");
			carrito.setUsuarioBean(usuario);
			carrito.setValorTotal(new BigDecimal(0));
			System.out.println("Con usuario");
			create(carrito);
			System.out.println("nuevo carrito");
			getEntityManager().persist(carrito);
		}
		else{
			carrito=lista.get(0);
		}
		return carrito;
	}
	/**
	 * Obtiene la lista de items del carrito de compras de un usuario
	 * @param usuario. Usuario previamente logeado en el sistema
	 * @return Lista de Camisetas en el carrito de compras de un usuario.
	 */
	public List<Camiseta> getItemsByUser(Usuario usuario){
		System.out.println("Entro");
		CarritoCompra carrito = getByUser(usuario);
		System.out.println("obtuvo carrito");
		List<Camiseta> lista = carrito.getCamisetas();
		System.out.println("detach" + lista.size());
		
		return lista;
	}
	/**
	 * Agrega un item al carrito de compras
	 * @param usuario. Usuario previamente logeado en el sistema
	 * @param camiseta. Camiseta a agregar
	 */
	public void addItemByUser(Usuario usuario, Camiseta camiseta){
		CarritoCompra carrito = getByUser(usuario);
		BigDecimal total=carrito.getValorTotal().add(camiseta.getPrecio());
		carrito.setValorTotal(total);
		if(carrito.getCamisetas()==null)
		{
			carrito.setCamisetas(new ArrayList<Camiseta>() );
		}
		Camiseta c=carrito.addCamiseta(camiseta);
		getEntityManager().persist(c);
		getEntityManager().merge(carrito);
	}
	/**
	 * Elimina un item al carrito de compras
	 * @param usuario. Usuario previamente logeado en el sistema
	 * @param camiseta. Camiseta a eliminar
	 */
	public void removeItemByUser(Usuario usuario, Camiseta camiseta){
		CarritoCompra carrito = getByUser(usuario);
		BigDecimal total=carrito.getValorTotal().subtract(camiseta.getPrecio().multiply(new BigDecimal(camiseta.getCantidad())));
		carrito.setValorTotal(total);
		Camiseta c= carrito.removeCamiseta(camiseta);
		getEntityManager().remove(getEntityManager().merge(c));
		getEntityManager().merge(carrito);
		
		
	}
	
	public CarritoCompra getCarritoPorId(Long idCarrito,boolean detach) {
		CarritoCompra carrito = null;

		try {
			Query query = em.createQuery("select c from CarritoCompra c where c.id= :id");
			query.setParameter("id", idCarrito);
			carrito = (CarritoCompra) query.getSingleResult();
			detachObject(carrito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	
	public CarritoCompra getCarritoPorUsuario(Usuario usuario) {
		CarritoCompra carrito = null;

		try {
			Query query = em.createQuery("select c from CarritoCompra c where c.usuarioBean= :usuario");
			query.setParameter("usuario",usuario);
			carrito = (CarritoCompra) query.getSingleResult();
			detachObject(carrito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carrito;
	}
	/**
	 * Actualiza el total del carrito con base en el item modificado
	 * @param carrito.  Carrito sobre el cual se va a modificar el total
	 * @param item. Item modificado
	 */
	public void updateItem(CarritoCompra carrito, Camiseta old, Camiseta item) {
		BigDecimal total=carrito.getValorTotal().subtract(old.getPrecio().multiply(new BigDecimal(old.getCantidad())));
		total=total.add(item.getPrecio().multiply(new BigDecimal(item.getCantidad())));
		carrito.setValorTotal(total);
		getEntityManager().merge(carrito);
		
	}

}
