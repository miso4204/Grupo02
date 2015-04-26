package edu.uniandes.configControllers.web.inicio;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppInicio extends App {

	public AppInicio(List<String> caracteristicas) {
		super(caracteristicas, "/", "/estampateWEB/html/inicio.html",
				"mainCtrl");
	}

	@Override
	@HtmlFile(name="inicio.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html, "./src/main/java/edu/uniandes/configControllers/web/inicio/html/inicio.html");
		return html.toString();
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js, "./src/main/java/edu/uniandes/configControllers/web/inicio/js/inicio.js");
		return js.toString();
	}
}
