package edu.uniandes.maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class EstampateEARConfig extends Config {
	public final String REPORTS = "Reports";
	public final String BASIC = "Basic";
	public final String INTERMEDIATE = "Intermediate";
	public final String ADVANCE = "Advance";
	public final String AREA = "<!--dependencias-->";

	public void execute(List<String> features) throws IOException {
		File fileConfig = Paths.get("./src/main/java/edu/uniandes/maven/estampateEAR/pom.xml").toRealPath().toFile();
		String fileContent = leerArchivo(fileConfig);
		StringBuilder builder = new StringBuilder();
		if (features.contains(REPORTS)) {
			builder.append("<dependency>").append("\n");
			builder.append("	<groupId>edu.uniandes.services</groupId>").append("\n");
			builder.append("	<artifactId>EstampateReportEJB</artifactId>").append("\n");
			builder.append("	<type>ejb</type>").append("\n");
			builder.append("<	version>1.0-SNAPSHOT</version>").append("\n");
			builder.append("</dependency>").append("\n");
		}
		if (features.contains(BASIC)) {
			builder.append("<dependency>").append("\n");
			builder.append("	<groupId>edu.uniandes</groupId>").append("\n");
			builder.append("	<artifactId>estampateWEB</artifactId>").append("\n");
			builder.append("	<version>1.0.0.1</version>").append("\n");
			builder.append("	<type>war</type>").append("\n");
			builder.append("</dependency>").append("\n");
		}
		if (features.contains(INTERMEDIATE)) {
			builder.append("<dependency>").append("\n");
			builder.append("	<groupId>edu.uniandes</groupId>").append("\n");
			builder.append("	<artifactId>estampateWEBIntermedio</artifactId>").append("\n");
			builder.append("	<version>1.0.0.1</version>").append("\n");
			builder.append("	<type>war</type>").append("\n");
			builder.append("</dependency>").append("\n");
		}
		if (features.contains(ADVANCE)) {
			builder.append("<dependency>").append("\n");
			builder.append("	<groupId>edu.uniandes</groupId>").append("\n");
			builder.append("	<artifactId>estampateWEBAvanzado</artifactId>").append("\n");
			builder.append("	<version>1.0.0.1</version>").append("\n");
			builder.append("	<type>war</type>").append("\n");
			builder.append("</dependency>").append("\n");
		}

		builder.append("<dependency>").append("\n");
		builder.append("	<groupId>edu.uniandes</groupId>").append("\n");
		builder.append("	<artifactId>EstampateEJB</artifactId>").append("\n");
		builder.append("	<version>1.0.0.1</version>").append("\n");
		builder.append("	<type>ejb</type>").append("\n");
		builder.append("</dependency>").append("\n");

		builder.append("<dependency>").append("\n");
		builder.append("	<groupId>edu.uniandes</groupId>").append("\n");
		builder.append("	<artifactId>persistencia</artifactId>").append("\n");
		builder.append("	<version>1.0.0.1</version>").append("\n");
		builder.append("	<type>jar</type>").append("\n");
		builder.append("</dependency>").append("\n");
		

		fileContent = fileContent.replace(AREA, builder.toString());
		File destino = Paths.get("../estampate/estampateEAR/pom.xml").toRealPath().toFile();
		createArchivo(fileContent, destino);
	}
}
