package edu.uniandes.configControllers.web.annotations;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



import edu.uniandes.configControllers.web.builder.App;

public class Processor {
	public void execute(String ruta, List<App> apps) {
		apps.stream().parallel().forEach(app -> {
			try {
				Method[] methods=app.getClass().getDeclaredMethods();
				for(Method method:methods){
					HtmlFile file =method.getAnnotation(HtmlFile.class);
					if (file != null) {
						List<String> datos = new ArrayList<String>();
						String htmlContent=(String)method.invoke(app,new Object[]{});
						datos.add(htmlContent);
						Files.write(Paths.get(ruta + "\\" + file.name()), datos);					
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
}
