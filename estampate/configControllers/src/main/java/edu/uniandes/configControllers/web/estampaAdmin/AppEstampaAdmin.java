package edu.uniandes.configControllers.web.estampaAdmin;

import java.util.List;

import edu.uniandes.configControllers.web.annotations.HtmlFile;
import edu.uniandes.configControllers.web.builder.App;

public class AppEstampaAdmin extends App {

	public AppEstampaAdmin(List<String> caracteristicas) {
		super(
				caracteristicas,
				"/estampaAdmin",
				"/estampateWEB/html/estampaAdmin.html",
				"estampaAdminCtrl");
	}

	@Override
	@HtmlFile(name="estampaAdmin.html")
	public String generateHtml() {
		StringBuilder html=new StringBuilder();
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampaAdmin/html/estampaAdmin.html");
		return null;
	}

	@Override
	public String getJS() {
		StringBuilder js=new StringBuilder();
		addFile(js, "./src/main/java/edu/uniandes/configControllers/web/estampaAdmin/js/estampaAdmin.js");
		return js.toString();
	}
}
