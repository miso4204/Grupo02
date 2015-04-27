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
		
		return getByUser(usuario).getCamisetas();
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
		BigDecimal total=carrito.getValorTotal().subtract(camiseta.getPrecio());
		carrito.setValorTotal(total);
		carrito.removeCamiseta(camiseta);
		getEntityManager().merge(carrito);
	}

}
