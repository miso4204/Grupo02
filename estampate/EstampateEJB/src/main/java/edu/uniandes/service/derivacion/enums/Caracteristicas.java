package edu.uniandes.service.derivacion.enums;

public enum Caracteristicas {
	REDES_SOCIALES(Boolean.FALSE), FACEBOOK(Boolean.FALSE), TWITTER(
			Boolean.FALSE);

	private Boolean seleccionada;

	private Caracteristicas(Boolean seleccionada) {
		this.seleccionada = seleccionada;
	}

	public Boolean getSeleccionada() {
		return seleccionada;
	}

	public void setSeleccionada(Boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
}
