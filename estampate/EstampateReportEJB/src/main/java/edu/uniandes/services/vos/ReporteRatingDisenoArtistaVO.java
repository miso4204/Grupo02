package edu.uniandes.services.vos;

/**
 * Informaci�n del reporte de dise�o por artista
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteRatingDisenoArtistaVO {
	
	private String nombresArtista;
	private String apellidosArtista;
	private String promedioRatingDiseno;

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

	public String getPromedioRatingDiseno() {
		return promedioRatingDiseno;
	}

	public void setPromedioRatingDiseno(String promedioRatingDiseno) {
		this.promedioRatingDiseno = promedioRatingDiseno;
	}

}
