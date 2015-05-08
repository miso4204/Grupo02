package edu.uniandes.service.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the VENTA database table.
 * 
 */
@Entity
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VENTA_ID_GENERATOR", sequenceName="VENTA_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VENTA_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to CarritoCompra
	@ManyToOne
	@JoinColumn(name="CARRITO")
	private CarritoCompra carritoCompra;

	//bi-directional many-to-one association to MetodoEnvio
	@ManyToOne
	@JoinColumn(name="METODO_ENVIO")
	private MetodoEnvio metodoEnvioBean;

	//bi-directional many-to-one association to MetodoPago
	@ManyToOne
	@JoinColumn(name="METODO_PAGO")
	private MetodoPago metodoPagoBean;
	@Transient
	private String referenciaPagoPSE;
	@Transient
	private String referenciaPagoTarjeta;
	@Transient
	private String referenciaPagoPIN;

	public Venta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public CarritoCompra getCarritoCompra() {
		return this.carritoCompra;
	}

	public void setCarritoCompra(CarritoCompra carritoCompra) {
		this.carritoCompra = carritoCompra;
	}

	public MetodoEnvio getMetodoEnvioBean() {
		return this.metodoEnvioBean;
	}

	public void setMetodoEnvioBean(MetodoEnvio metodoEnvioBean) {
		this.metodoEnvioBean = metodoEnvioBean;
	}

	public MetodoPago getMetodoPagoBean() {
		return this.metodoPagoBean;
	}

	public void setMetodoPagoBean(MetodoPago metodoPagoBean) {
		this.metodoPagoBean = metodoPagoBean;
	}

	public String getReferenciaPagoPSE() {
		return referenciaPagoPSE;
	}

	public void setReferenciaPagoPSE(String referenciaPagoPSE) {
		this.referenciaPagoPSE = referenciaPagoPSE;
	}

	public String getReferenciaPagoTarjeta() {
		return referenciaPagoTarjeta;
	}

	public void setReferenciaPagoTarjeta(String referenciaPagoTarjeta) {
		this.referenciaPagoTarjeta = referenciaPagoTarjeta;
	}

	public String getReferenciaPagoPIN() {
		return referenciaPagoPIN;
	}

	public void setReferenciaPagoPIN(String referenciaPagoPIN) {
		this.referenciaPagoPIN = referenciaPagoPIN;
	}

}