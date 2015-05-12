package edu.uniandes.service.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import edu.uniandes.service.util.*;

public class CaracteristicasConf {

	private ArrayList listaCaracteristicas = new ArrayList();
	
	public CaracteristicasConf() {
		// TODO Auto-generated constructor stub
		cargarArchivo();
	}
	
	private void cargarArchivo(){
		try {
			//System.out.println("+++ Inicia lectura archivo");
			String linea;
			/*
			File miDir = new File (".");
			System.out.println ("+++ Directorio actual: " + miDir.getAbsolutePath());
			System.out.println ("+++ URI actual: " + miDir.toURI());
			System.out.println ("+++ URL actual: " + miDir.toURL());
			*/
			
			FileReader fr = new FileReader(Constantes.RUTA_ARCHIVO_CARACTERISTICAS 
										   + Constantes.NOMBRE_ARCHIVO_CARACTERISTICAS);
			BufferedReader bf = new BufferedReader(fr);
		    
		      while((linea = bf.readLine()) != null)
		      {
		    	  listaCaracteristicas.add(linea);
		      }
		      
		      fr.close();
		      bf.close();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
	}

	public boolean existeCaracteristica(String caractaristica){
		boolean existe = false;
		
		if(listaCaracteristicas.contains(caractaristica))
		{
			existe = true;
		}
		
		return existe;
	}
}
