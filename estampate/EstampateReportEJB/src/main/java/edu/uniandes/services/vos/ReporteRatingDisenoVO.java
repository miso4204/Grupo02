package edu.uniandes.services.vos;

/**
 * Información del reporte de rating de diseño
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteRatingDisenoVO {
	
	private String nombreEstampa;
	private String informacionEstampa;
	private String rating;
	private String cantidadVotos;
	private String nombresArtista;
	private String apellidosArtista;
	public String getNombreEstampa() {
		return nombreEstampa;
	}
	public void setNombreEstampa(String nombreEstampa) {
		this.nombreEstampa = nombreEstampa;
	}
	public String getInformacionEstampa() {
		return informacionEstampa;
	}
	public void setInformacionEstampa(String informacionEstampa) {
		this.informacionEstampa = informacionEstampa;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getCantidadVotos() {
		return cantidadVotos;
	}
	public void setCantidadVotos(String cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}
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
}
