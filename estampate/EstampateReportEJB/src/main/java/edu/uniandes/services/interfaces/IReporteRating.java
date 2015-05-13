package edu.uniandes.services.interfaces;

import edu.uniandes.services.daos.ReportesDAO;


/**
 * Define los m�todo requeridos para la gesti�n de reportes de rating
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public interface IReporteRating {
	
	void setReportesDAO(ReportesDAO reportesDAO);

	/**
	 * Obtiene el reporte de rating
	 * 
	 * @return
	 */
	String obtenerReporte();
}
