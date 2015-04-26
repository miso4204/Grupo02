package edu.uniandes.configControllers.web.estampa;

import java.util.List;

import edu.uniandes.configControllers.web.builder.App;

public class AppEstampa extends App {
	public final String OPCION_LISTA = "List";
	public final String OPCION_GALERY = "Galery";

	public AppEstampa(List<String> caracteristicas) {
		super(caracteristicas, "/estampa", "/estampateWEB/html/estampa.html","estampaCtrl");
	}

	@Override
	public String generateHtml() {
		boolean galery = aplicaCaracteristica(OPCION_GALERY);
		boolean list = aplicaCaracteristica(OPCION_LISTA);
		StringBuilder html = new StringBuilder();
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampa/html/estampaRoot.html");
		if (galery && list) {
			addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampa/html/estampaSelect.html");
		}
		if (galery) {
			addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampa/html/estampaGalery.html");
		}
		if (list) {
			addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampa/html/estampaList.html");			
		}
		addFile(html,"./src/main/java/edu/uniandes/configControllers/web/estampa/html/estampaFooter.html");
		String htmlFinal=html.toString();		
		if(galery){
			htmlFinal.replaceFirst(OPCION_LISTA,"vista=='Lista'");
		}
		if(list){
			htmlFinal.replaceFirst(OPCION_GALERY, "vista=='Galeria'");			
		} 
		return htmlFinal;
	}

	@Override
	public String getJS() {
		boolean galery = aplicaCaracteristica(OPCION_GALERY);
		boolean list = aplicaCaracteristica(OPCION_LISTA);
		StringBuilder js = new StringBuilder();
		addFile(js, "./src/main/java/edu/uniandes/configControllers/web/estampa/js/estampaRoot.js");
		if(galery && list){
			addFile(js, "./src/main/java/edu/uniandes/configControllers/web/estampa/js/estampaGaleryList.js");	
		}
		if (galery) {
			addFile(js,"./src/main/java/edu/uniandes/configControllers/web/estampa/js/estampaGalery.js");
		}
		if (list) {
			addFile(js,"./src/main/java/edu/uniandes/configControllers/web/estampa/js/estampaList.js");			
		}
		addFile(js, "./src/main/java/edu/uniandes/configControllers/web/estampa/js/estampaFooter.js");
		return js.toString();
	}
}
