package edu.uniandes.service.patterns;

import java.util.Random;

import edu.uniandes.service.entidades.Venta;

public class PagarPSE implements IPagar{
    public Venta pagar(Venta venta) {
    	Random rnd = new Random();
    	venta.setReferenciaPagoPSE(rnd.toString());
    	return venta;
    }
}
