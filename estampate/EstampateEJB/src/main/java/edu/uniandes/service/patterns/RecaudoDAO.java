package edu.uniandes.service.patterns;

import edu.uniandes.service.patterns.IPagar;
import edu.uniandes.service.daos.AbstractDAO;
import edu.uniandes.service.entidades.CarritoCompra;
import edu.uniandes.service.entidades.Venta;

public abstract class RecaudoDAO extends AbstractDAO<Venta>{

    private IPagar pago;

    public abstract CarritoCompra hacerPago(CarritoCompra carrito);
    
	/**
	 * Default constructor.
	 */
	public RecaudoDAO() {
		super(Venta.class);
	}
	
    public IPagar getPago() {
        return pago;
    }

    public void setPago(IPagar pago) {
        this.pago = pago;
    } 
}
