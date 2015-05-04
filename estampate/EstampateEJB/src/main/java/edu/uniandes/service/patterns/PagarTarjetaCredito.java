package edu.uniandes.service.patterns;

import java.util.Random;

import edu.uniandes.service.entidades.Venta;

public class PagarTarjetaCredito implements IPagar{
    public Venta pagar(Venta venta) {
    	Random rnd = new Random();
    	venta.setReferenciaPagoTarjeta(rnd.toString());
    	return venta;
    }
}
