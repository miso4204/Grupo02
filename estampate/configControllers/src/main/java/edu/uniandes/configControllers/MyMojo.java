package edu.uniandes.configControllers;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import edu.uniandes.configControllers.web.annotations.Processor;
import edu.uniandes.configControllers.web.builder.App;
import edu.uniandes.configControllers.web.builder.BuilderController;
import edu.uniandes.configControllers.web.builder.BuilderRoute;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * 
 * @phase process-sources
 */
@Mojo(name = "mojito")
public class MyMojo extends AbstractMojo {
	/**
	 * Location of the file.
	 * 
	 * @parameter expression="${project.build.directory}"
	 * @required
	 */
	@Parameter
	private String rutaJs;
	@Parameter
	private String rutaHtml;

	public void execute() throws MojoExecutionException {

	}

	public static void main(String[] arg) {
		try {
			List<String> caracteristicas=new ArrayList<String>();
			caracteristicas.add("List");
			caracteristicas.add("Galery");
			List<App> apps = new ArrayList<App>();
			BuilderRoute builderRoute = new BuilderRoute();
			BuilderController builderController=new BuilderController();
			Files.lines(Paths.get("./config.ini")).forEach(clase -> {
				try {
					Class appClass=Class.forName(clase);
					Constructor appConstructor=appClass.getConstructor(List.class);
					App app = (App)appConstructor.newInstance(caracteristicas) ;
					builderRoute.add(app);
					builderController.add(app);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			builderRoute.toFile("C:\\Users\\lgamboa\\git\\Grupo02\\estampate\\estampateWeb\\src\\main\\webapp\\js\\estampate\\app.js");
			builderController.toFile("C:\\Users\\lgamboa\\git\\Grupo02\\estampate\\estampateWeb\\src\\main\\webapp\\js\\estampate\\controllers.js");
			Processor processor=new Processor();
			processor.execute("C:\\Users\\lgamboa\\git\\Grupo02\\estampate\\estampateWeb\\src\\main\\webapp\\html", apps);
			System.out.println("PASOO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
