package edu.uniandes.configControllers.web.builder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder {

	protected StringBuilder builder;

	public abstract void add(App app);

	protected abstract String create();

	public void toFile(String destFile) {
		List<String> content = new ArrayList<String>();
		content.add(create());
		try {
			Files.write(Paths.get(destFile), content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
