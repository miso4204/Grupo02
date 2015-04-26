package edu.uniandes.configControllers.web.camisetaAdmin;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppCamisetaAdmin extends App {

	public AppCamisetaAdmin(List<String> caracteristicas) {
		super(
				caracteristicas,
				"/camisetasAdmin",
				"/estampateWEB/html/camisetasAdmin.html",
				"camisetasAdminCtrl");
	}

	@Override
	@HtmlFile(name="camisetaAdmin.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/camisetaAdmin/htnl/camisetaAdmin.html");
		return html.toString();
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js,"./src/main/java/edu/uniandes/configControllers/web/camisetaAdmin/js/camisetaAdmin.js");
		return js.toString();
	}

}
