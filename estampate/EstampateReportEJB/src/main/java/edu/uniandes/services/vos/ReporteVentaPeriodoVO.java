package edu.uniandes.services.vos;

/**
 * Información de ventas por periodos.
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteVentaPeriodoVO {
	
	private String mesVenta;
	private String anioVenta;
	private String cantidadVentas;
	private String totalVentas;

	public String getMesVenta() {
		return mesVenta;
	}

	public void setMesVenta(String mesVenta) {
		this.mesVenta = mesVenta;
	}

	public String getAnioVenta() {
		return anioVenta;
	}

	public void setAnioVenta(String anioVenta) {
		this.anioVenta = anioVenta;
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
