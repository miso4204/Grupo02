package edu.uniandes.service.patterns;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.Venta;

@Stateless
@LocalBean
public class RecaudoContraEntregaDAO extends RecaudoDAO{
	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
    @Override
    public CarritoCompra hacerPago(CarritoCompra carrito) {
        IPagar pago = getPago();
        pago = new PagarContraEntrega();

		for(Venta venta : carrito.getVentas()) {
			getEntityManager().persist(pago.pagar(venta));
		}
        return carrito;
    }
}
