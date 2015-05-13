package edu.uniandes.maven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Config {
	protected String leerArchivo(File file) {
		StringBuilder archivo = new StringBuilder();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String linea = bf.readLine();
			while (linea != null) {
				archivo.append(linea).append("\n");
				linea = bf.readLine();
			}
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return archivo.toString();
	}
	protected void createArchivo(String datos,File file){
		PrintWriter fileWriter;
		try {
			fileWriter = new PrintWriter(file);		
			fileWriter.write(datos);
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
