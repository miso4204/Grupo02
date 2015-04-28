package edu.uniandes.services.fabricas;

import edu.uniandes.services.implement.ReporteRatingDisenos;
import edu.uniandes.services.implement.ReporteVentasPorPeriodo;
import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.interfaces.IReporteVentas;

/**
 * Fabrica de reportes por default(para rating el reporte por defecto es por
 * dise�o, y para ventas el reporte por defecto es por periodo)
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class FabricaReportesDefault implements FabricaReportes {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.fabricas.FabricaReportes#crearReporteRating()
	 */
	@Override
	public IReporteRating crearReporteRating() {
		ReporteRatingDisenos reporteRatingDisenos = new ReporteRatingDisenos();
		return reporteRatingDisenos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.fabricas.FabricaReportes#crearReporteVentas()
	 */
	@Override
	public IReporteVentas crearReporteVentas() {
		System.out.println("Se crea reporte ventas");
		ReporteVentasPorPeriodo reporteVentasPorPeriodo = new ReporteVentasPorPeriodo();
		System.out.println("Listo reporte");
		return reporteVentasPorPeriodo;
	}

}
