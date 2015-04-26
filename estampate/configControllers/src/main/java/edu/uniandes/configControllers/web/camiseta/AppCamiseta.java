package edu.uniandes.configControllers.web.camiseta;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppCamiseta extends App {

	public AppCamiseta(List<String> caracteristicas) {
		super(caracteristicas, "/camisa", "/estampateWEB/html/camiseta.html",
				"camisaCtrl");
	}

	@Override
	@HtmlFile(name="camiseta.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/camiseta/html/camiseta.html");
		return html.toString();
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js,"./src/main/java/edu/uniandes/configControllers/web/camiseta/js/camiseta.js");
		return js.toString();
	}
}
