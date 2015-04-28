package edu.uniandes.services.fabricas;

import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.interfaces.IReporteVentas;

/**
 * Fabrica abstracta para la creación de reportes
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public interface FabricaReportes {

	/**
	 * Crear un reporte de rating
	 */
	IReporteRating crearReporteRating();

	/**
	 * Crea un reporte de ventas
	 */
	IReporteVentas crearReporteVentas();
}
