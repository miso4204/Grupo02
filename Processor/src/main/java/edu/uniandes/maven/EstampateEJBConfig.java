package edu.uniandes.maven;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class EstampateEJBConfig extends Config{
	public final String COLOR = "Color";
	public final String SHOPPINGCART="ShoppingCart";
	public final String STAMPDESIGN="StampDesign";
	public final String FACEBOOK="Facebook";
	public final String SOCIALNETWORKS="SocialNetworks";
	public final String USER="User";
	public final String PRODUCTDESIGN="ProductDesign";
	public final String PAY="Pay";
	public final String UPDATEPROFILE="UpdateProfile";
	public final String SIZE="Size";
	public final String TEMA="Tema";
	public final String PRODUCTADMIN="ProductAdmin";
	
	public final String AREA = "<!--excludes-->";

	public void execute(List<String> features) throws IOException {
		File fileConfig=Paths.get("./src/main/java/edu/uniandes/maven/estampateEJB/pom.xml").toRealPath().toFile();
		String fileContent=leerArchivo(fileConfig);
		StringBuilder builder = new StringBuilder();
		if (!features.contains(COLOR)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.ColorResource</exclude>");	
		}
		if (!features.contains(SHOPPINGCART)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.CarritoResource</exclude>");	
		}
		if (!features.contains(STAMPDESIGN)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.EstampaResource</exclude>");	
		}
		if (!features.contains(FACEBOOK)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.FacebookResource</exclude>");	
		}
		if (!features.contains(SOCIALNETWORKS)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.FeaturesAppResource</exclude>");	
		}
		if (!features.contains(USER)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.FuncionalidadesResource</exclude>");
			builder.append("<exclude>**/edu.uniandes.service.ws.RolResource</exclude>");
			builder.append("<exclude>**/edu.uniandes.service.ws.UsuarioResource</exclude>");						
		}
		if (!features.contains(PRODUCTDESIGN)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.MaterialResource</exclude>");	
		}
		if (!features.contains(PAY)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.MetodoEnvioResource</exclude>");
			builder.append("<exclude>**/edu.uniandes.service.ws.VentasResource</exclude>");			
		}
		if (!features.contains(UPDATEPROFILE)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.PersonaResource</exclude>");	
		}
		if (!features.contains(SIZE)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.TallaResource</exclude>");	
		}
		if (!features.contains(TEMA)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.TemaResource</exclude>");	
		}
		if (!features.contains(PRODUCTADMIN)) {
			builder.append("<exclude>**/edu.uniandes.service.ws.TipoCamisaResource</exclude>");	
		}		
		
		fileContent=fileContent.replace(AREA, builder.toString());
		File destino=Paths.get("../estampate/estampateEJB/pom.xml").toRealPath().toFile();
		createArchivo(fileContent, destino);		
	}	
}
