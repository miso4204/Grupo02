package edu.uniandes.services.vos;

/**
 * Información de ventas por artista
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteVentaArtistaVO {

	private String nombresArtista;
	private String apellidosArtista;
	private String cantidadVentas;
	private String totalVentas;

	public String getNombresArtista() {
		return nombresArtista;
	}

	public void setNombresArtista(String nombresArtista) {
		this.nombresArtista = nombresArtista;
	}

	public String getApellidosArtista() {
		return apellidosArtista;
	}

	public void setApellidosArtista(String apellidosArtista) {
		this.apellidosArtista = apellidosArtista;
	}

	public String getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(String cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}

	public String getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(String totalVentas) {
		this.totalVentas = totalVentas;
	}
}
