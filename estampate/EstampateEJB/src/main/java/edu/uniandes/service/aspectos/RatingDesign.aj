package edu.uniandes.service.aspectos;


import edu.uniandes.service.entidades.Estampa;
import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.ws.EstampaResource;
import java.util.List;
import java.math.BigDecimal;

import edu.uniandes.service.resources.CaracteristicasConf;
import edu.uniandes.service.util.Constantes;

public aspect RatingDesign {
 
 public pointcut editCall(Estampa e):
  (call(public void edit(Estampa)) || call(public void create(Estampa))) && within(edu.uniandes.service.ws.EstampaResource) && args(e);
/**
* En caso de que no este la opcion ratting no se ofrece.
*/
 before(Estampa e): editCall(e) {
 	CaracteristicasConf caracteristicas =  new CaracteristicasConf();
 	if(!caracteristicas.existeCaracteristica(Constantes.CARACTERISTICA_RATING_DESIGN))
	{
	  System.out.println("\n -- Rating de estampa --");
	  System.out.println("id estampa" + e.getId());
	  if((e.getCantidadVotos()).compareTo(BigDecimal.ZERO) > 0)
	  {
	  	e.setCantidadVotos(BigDecimal.ZERO);
	  	e.setRatting(BigDecimal.ZERO);
	  }
	  System.out.println(Constantes.EXCEPCION_CARACTERISTICA);
	}
 }
}