package edu.uniandes.services.fabricas;

import edu.uniandes.services.implement.ReporteRatingDisenoPorArtista;
import edu.uniandes.services.implement.ReporteVentasPorArtista;
import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.interfaces.IReporteVentas;

/**
 * Fabrica de reportes por artista(ratting de dise�os por artista ventas por
 * artista)
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class FabricaReportesPorArtista implements FabricaReportes {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.fabricas.FabricaReportes#crearReporteRating()
	 */
	@Override
	public IReporteRating crearReporteRating() {
		ReporteRatingDisenoPorArtista reporteRatingDisenosPorArtista = new ReporteRatingDisenoPorArtista();
		return reporteRatingDisenosPorArtista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.fabricas.FabricaReportes#crearReporteVentas()
	 */
	@Override
	public IReporteVentas crearReporteVentas() {
		ReporteVentasPorArtista reporteVentasPorArtista = new ReporteVentasPorArtista();
		return reporteVentasPorArtista;
	}

}
