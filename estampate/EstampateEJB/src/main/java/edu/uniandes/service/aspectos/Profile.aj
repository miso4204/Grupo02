package edu.uniandes.service.aspectos;



import edu.uniandes.service.entidades.Persona;
import edu.uniandes.service.daos.EstampaDAO;
import edu.uniandes.service.ws.PersonaResource;
import java.util.List;
import java.math.BigDecimal;

import edu.uniandes.service.resources.CaracteristicasConf;
import edu.uniandes.service.util.Constantes;

public aspect Profile {
 
 public pointcut editProfileCall(Persona e):
  call(public void edit(Persona)) && within(edu.uniandes.service.ws.EstampaResource) && args(e);
/**
* En caso de que no este la opcion ratting no se ofrece.
*/
 before(Persona e): editProfileCall(e) {
 	CaracteristicasConf caracteristicas =  new CaracteristicasConf();
 	if(!caracteristicas.existeCaracteristica(Constantes.CARACTERISTICA_PASS))
	{
	  
	  System.out.println(Constantes.EXCEPCION_CARACTERISTICA);
	}
	if(!caracteristicas.existeCaracteristica(Constantes.CARACTERISTICA_EMAIL))
	{
	  
	  System.out.println(Constantes.EXCEPCION_CARACTERISTICA);
	}
 }
}