package edu.uniandes.services.interfaces;

import edu.uniandes.services.daos.ReportesDAO;

/**
 * Define los mtodos requeridos para el reporte de ventas
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public interface IReporteVentas {

	void setReportesDAO(ReportesDAO reportesDAO);
	
	/**
	 * Obtiene el reporte de ventas
	 * 
	 * @return
	 */
	byte[] obtenerReporte();
}
