package edu.uniandes.annotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;


@SupportedAnnotationTypes("edu.uniandes.annotations.Feature")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class FeatureProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Set<? extends Element> annotationsSet = roundEnv
				.getElementsAnnotatedWith(Feature.class);
		try {
			if (!annotations.isEmpty()) {
				StringBuilder builde = new StringBuilder();
				builde.append(" /* \n");
				builde.append("  * To change this license header, choose License Headers in Project Properties.  \n");
				builde.append("  * To change this template file, choose Tools | Templates  \n");
				builde.append("  * and open the template in the editor.  \n");
				builde.append("  */  \n");
				builde.append(" package edu.uniandes.service.ws.config;  \n");
				builde.append("   \n");
				builde.append(" import java.util.Set;  \n");
				builde.append(" import javax.ws.rs.core.Application;  \n");
				builde.append("   \n");
				builde.append(" /**  \n");
				builde.append("  *  \n");
				builde.append("  * @author Listman G.A  \n");
				builde.append("  */  \n");
				builde.append(" @javax.ws.rs.ApplicationPath(\"webresources\")  \n");
				builde.append(" public class ApplicationConfig extends Application {  \n");
				builde.append("   \n");
				builde.append("     @Override  \n");
				builde.append("     public Set<Class<?>> getClasses() {  \n");
				builde.append("         Set<Class<?>> resources = new java.util.HashSet<Class<?>>();  \n");
				builde.append("         // following code can be used to customize Jersey 1.x JSON provider:  \n");
				builde.append("         try {  \n");
				builde.append("             Class jacksonProvider = Class.forName(\"org.codehaus.jackson.jaxrs.JacksonJsonProvider\");  \n");
				builde.append("             resources.add(jacksonProvider);  \n");
				builde.append("         } catch (ClassNotFoundException ex) {  \n");
				builde.append("             java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);  \n");
				builde.append("         }  \n");
				builde.append("         addRestResourceClasses(resources);  \n");
				builde.append("         return resources;  \n");
				builde.append("     }  \n");
				builde.append("  ");
				builde.append("     /** \n ");
				builde.append("      * Do not modify addRestResourceClasses() method.  \n");
				builde.append("      * It is automatically populated with  \n");
				builde.append("      * all resources defined in the project.  \n");
				builde.append("      * If required, comment out calling this method in getClasses().  \n");
				builde.append("      */  \n");
				builde.append("     private void addRestResourceClasses(Set<Class<?>> resources) {  \n");
				List<String> datos = new ArrayList<String>();
				List<String> configuraciones = new ArrayList<String>();
				File fileConfig=Paths.get("../../EstampateVabrik/configs/default.config").toRealPath().toFile();
				FileReader fr = new FileReader(fileConfig);
				BufferedReader bf = new BufferedReader(fr);
				String linea = bf.readLine();
				while (linea!= null) {
					configuraciones.add(linea);
					linea = bf.readLine();
				}

				fr.close();
				bf.close();								
				Path file = Paths
						.get("../EstampateEJB/src/main/java/edu/uniandes/service/ws/config/ApplicationConfig.java")
						.toRealPath();
				for (Element element : annotationsSet) {
					Feature feature = element.getAnnotation(Feature.class);
					if (configuraciones.contains(feature.name())) {
						builde.append("         resources.add(");
						builde.append(element.toString());
						builde.append(".class); \n");
					}
				}
				builde.append("     }  \n");
				builde.append("       \n");
				builde.append(" }  /*V1*/\n");				
				PrintWriter fileWriter=new PrintWriter(file.toFile());
				fileWriter.write(builde.toString());
				fileWriter.flush();
				fileWriter.close();
				
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
}
