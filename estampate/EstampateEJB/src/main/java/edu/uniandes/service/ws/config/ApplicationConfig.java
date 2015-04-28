/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.service.ws.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Listman G.A
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        // following code can be used to customize Jersey 1.x JSON provider:
        try {
            Class jacksonProvider = Class.forName("org.codehaus.jackson.jaxrs.JacksonJsonProvider");
            resources.add(jacksonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(edu.uniandes.service.ws.VentasResource.class);        
        resources.add(edu.uniandes.service.ws.PersonaResource.class);
        resources.add(edu.uniandes.service.ws.FuncionalidadesResource.class);
        resources.add(edu.uniandes.service.ws.TipoCamisaResource.class);
        resources.add(edu.uniandes.service.ws.EstampaResource.class);
        resources.add(edu.uniandes.service.ws.ColorResource.class);
        resources.add(edu.uniandes.service.ws.TallaResource.class);
        resources.add(edu.uniandes.service.ws.MaterialResource.class);
        resources.add(edu.uniandes.service.ws.MetodoEnvioResource.class);
        resources.add(edu.uniandes.service.ws.UsuarioResource.class);
        resources.add(edu.uniandes.service.ws.TemaResource.class);
        resources.add(edu.uniandes.service.ws.CarritoResource.class);
        resources.add(edu.uniandes.services.ws.ReportesResource.class);
        
    }
    
}
