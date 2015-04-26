package edu.uniandes.configControllers.web.perfil;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppPerfil extends App {

	public AppPerfil(List<String> caracteristicas) {
		super(caracteristicas, "/perfil", "/estampateWEB/html/perfil.html",
				"perfilCtrl");
	}
	
	@Override
	@HtmlFile(name="perfil.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html, "./src/main/java/edu/uniandes/configControllers/web/perfil/html/perfil.html");
		return html.toString();
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js, "./src/main/java/edu/uniandes/configControllers/web/perfil/js/perfilController.js");
		return js.toString();
	}
}
