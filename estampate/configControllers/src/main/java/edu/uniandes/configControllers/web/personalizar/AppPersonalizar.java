package edu.uniandes.configControllers.web.personalizar;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppPersonalizar extends App {

	public AppPersonalizar(List<String> caracteristicas) {
		super(
				caracteristicas,
				"/personalizar",
				"/estampateWEB/html/personalizar.html",
				"personalizarCtrl");
	}

	@Override
	@HtmlFile(name="personalizar.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/personalizar/html/personalizar.html");
		return html.toString();
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js,"./src/main/java/edu/uniandes/configControllers/web/personalizar/js/personalizar.js");
		return js.toString();
	}

}