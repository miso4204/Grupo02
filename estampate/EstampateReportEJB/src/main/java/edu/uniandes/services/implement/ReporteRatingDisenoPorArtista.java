package edu.uniandes.services.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.io.FileUtils;

import edu.uniandes.services.daos.ReportesDAO;
import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.vos.ReporteRatingDisenoArtistaVO;

/**
 * Producto reporte de rating de dise�o por artista
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteRatingDisenoPorArtista implements IReporteRating {
	
	private ReportesDAO reportesDAO;
	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.interfaces.IReporteRating#obtenerReporte()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public byte[] obtenerReporte() {
		
		
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        Map parameters = new HashMap();
        try {
        	jasperReport = JasperCompileManager.compileReport("C:\\Users\\template\\Desktop\\Vabrik\\Private Tunnel.lnk\\estampate\\EstampateReportEJB\\src\\main\\java\\edu\\uniandes\\services\\implement\\reporteRatingDisenoArtistas.jrxml");
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reportesDAO.consultarInfoReporteDisenosArtistas()));
//            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\template\\Desktop\\eclipse\\eclipse\\jboss-eap-6.3\\welcome-content\\reporterRatingDisenosArtistas.pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint,"reporterRatingDisenosArtistas.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
        File file = new File("reporterRatingDisenosArtistas.pdf");
		try {
			try {
				File outFile = new File("C:\\Users\\template\\Desktop\\eclipse\\eclipse\\jboss-eap-6.3\\welcome-content\\reporterRatingDisenosArtistas.pdf");

				FileInputStream in = new FileInputStream(file);
				FileOutputStream out = new FileOutputStream(outFile);

				int c;
				while( (c = in.read() ) != -1)
					out.write(c);

				in.close();
				out.close();
			} catch(IOException e) {
				System.err.println("Hubo un error de entrada/salida!!!");
			}
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ReporteRatingDisenoArtistaVO> getList(){
		return new ArrayList<ReporteRatingDisenoArtistaVO>();
	}

	public void setReportesDAO(ReportesDAO reportesDAO) {
		this.reportesDAO = reportesDAO;
	}

}
