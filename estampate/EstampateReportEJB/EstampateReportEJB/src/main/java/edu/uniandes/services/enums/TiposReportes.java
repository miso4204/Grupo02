package edu.uniandes.services.enums;

/**
 * Tipo de reportes
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public enum TiposReportes {

	REPORTE_RATING_DISENO("1"), 
	REPORTE_RATING_DISENO_ARTISTA("2"), 
	REPORTE_VENTAS_PERIODO("3"), 
	REPORTE_VENTAS_ARTISTA("4");

	private String idReporte;
	
	private TiposReportes(String idReporte) {
		this.idReporte = idReporte;
	}

	public String getIdReporte() {
		return idReporte;
	}
}
