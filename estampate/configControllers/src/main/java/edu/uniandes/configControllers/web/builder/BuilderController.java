package edu.uniandes.configControllers.web.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuilderController extends AbstractBuilder{
	

	public BuilderController() {
		super();
		builder = new StringBuilder();
		try {
			Files.lines(
					Paths.get("./src/main/java/edu/uniandes/configControllers/web/builder/controllers.js"))
					.forEachOrdered(linea -> {
						builder.append(linea);
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(App app) {
		builder.append(app.getJS());		
	}

	public String create() {
		return builder.toString();
	}
}
