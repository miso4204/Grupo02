package edu.uniandes.configControllers.web.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuilderRoute  extends AbstractBuilder{
	
	
	public BuilderRoute() {
		super();
		builder=new StringBuilder();
		try {
			Files.lines(
					Paths.get("./src/main/java/edu/uniandes/configControllers/web/builder/app.js"))
					.forEachOrdered(linea -> {
						builder.append(linea);
					});
		} catch (IOException e) {
			e.printStackTrace();
		}		
		builder.append("estampateApp.config([ '$routeProvider', function($routeProvider) { $routeProvider");		
	}

	public void add(App app){
		builder.append(app.generateRoute());
	}	
	public String create(){
		builder.append("} ]);");
		return builder.toString();		
	}
	
}
