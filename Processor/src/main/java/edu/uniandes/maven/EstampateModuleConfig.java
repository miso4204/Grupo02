package edu.uniandes.maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class EstampateModuleConfig extends Config{
	public final String REPORTS = "Reports";
	public final String BASIC = "Basic";
	public final String INTERMEDIATE = "Intermediate";
	public final String ADVANCE = "Advance";
	public final String AREA = "<!--_MODULOS_-->";

	public void execute(List<String> features) throws IOException {
		File fileConfig=Paths.get("./src/main/java/edu/uniandes/maven/estampate/pom.xml").toRealPath().toFile();
		String fileContent=leerArchivo(fileConfig);
		StringBuilder builder = new StringBuilder();
		if (features.contains(REPORTS)) {
			builder.append("<module>../EstampateReportEJB</module>").append("\n");;
		}

		builder.append("<module>../estampateEAR</module>").append("\n");
		builder.append("<module>../EstampateEJB</module>").append("\n");;
		if (features.contains(BASIC)) {
			builder.append("<module>../estampateWEB</module>").append("\n");;
		}
		if (features.contains(INTERMEDIATE)) {
			builder.append("<module>../estampateWEBIntermedio</module>").append("\n");;
		}
		if (features.contains(ADVANCE)) {
			builder.append("<module>../estampateWEBAvanzado</module>").append("\n");;
		}		
		builder.append("<module>../persistencia</module>").append("\n");;
		fileContent=fileContent.replace(AREA, builder.toString());
		File destino=Paths.get("../estampate/estampate/pom.xml").toRealPath().toFile();
		createArchivo(fileContent, destino);		
	}	
}
