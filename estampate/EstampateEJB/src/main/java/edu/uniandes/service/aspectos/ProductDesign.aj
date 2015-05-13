package edu.uniandes.service.aspectos;



import edu.uniandes.service.entidades.Camiseta;
import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.ws.CarritoResource;
import java.util.List;
import java.math.BigDecimal;

import edu.uniandes.service.resources.CaracteristicasConf;
import edu.uniandes.service.util.Constantes;

public aspect ProductDesign {
 
 public pointcut productDesignCall(Camiseta c):
  (call(public void addItem(Camiseta)) || call(public void updateCarrito(Camiseta))) && within(edu.uniandes.service.ws.CarritoResource) && args(c);
/**
* En caso de que no este la opcion Texto o Color no se ofrece.
*/
 before(Camiseta c): productDesignCall(c) {
 	CaracteristicasConf caracteristicas =  new CaracteristicasConf();
 	if(!caracteristicas.existeCaracteristica(Constantes.CARACTERISTICA_TEXTO))
	{
	  System.out.println("\n -- Personalizar Camiseta --");
	  if((c.getTexto())!=null)
	  {
	  	c.setTexto(null);
	  }
	  System.out.println(Constantes.EXCEPCION_CARACTERISTICA);
	}
	if(!caracteristicas.existeCaracteristica(Constantes.CARACTERISTICA_COLOR))
	{
	  System.out.println("\n -- Personalizar Camiseta --");
	  if(((c.getColorCamiseta()).getId())!=1)
	  {
	  	System.out.println("Error");
	  }
	  System.out.println(Constantes.EXCEPCION_CARACTERISTICA);
	}
 }
}