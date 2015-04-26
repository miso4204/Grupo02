package edu.uniandes.configControllers.web.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class App {

	private String url;
	private String templateUrl;
	private String controller;
	private List<String> caracteristicas;

	public App(List<String> caracteristicas, String url, String templateUrl,
			String controller) {
		super();
		this.url = url;
		this.templateUrl = templateUrl;
		this.controller = controller;
		this.caracteristicas = caracteristicas;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}

	public String getController() {
		return controller;
	}

	public abstract String getJS();

	public abstract String generateHtml();

	public String generateRoute() {
		StringBuilder builder = new StringBuilder();
		builder.append(".when('").append(url).append("', { templateUrl : '");
		builder.append(templateUrl).append("',controller : '");
		builder.append(controller).append("'})");
		return builder.toString();
	}

	public boolean aplicaCaracteristica(String caracteristica) {
		for (String valor : caracteristicas) {
			if (valor.equals(caracteristica)) {
				return true;
			}
		}
		return false;
	}

	protected void addFile(StringBuilder file, String ruta) {
		try {
			Files.lines(Paths.get(ruta)).forEachOrdered(line -> {
				file.append(line);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
