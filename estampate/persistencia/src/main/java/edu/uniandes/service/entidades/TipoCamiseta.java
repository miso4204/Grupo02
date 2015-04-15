package edu.uniandes.service.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="TIPO_CAMISETA")
public class TipoCamiseta implements Serializable {
	@Id
	@SequenceGenerator(name = "TIPO_CAMISETA_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_CAMISETA_ID_GENERATOR")
	private long id;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="UBICACION")
	private String ubicacion;
	@Column(name="VALOR")
	private BigDecimal valor; 
	@OneToMany(mappedBy="tipoCamiseta")
	private List<Camiseta> camisetas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Camiseta> getCamisetas() {
		return camisetas;
	}

	public void setCamisetas(List<Camiseta> camisetas) {
		this.camisetas = camisetas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
