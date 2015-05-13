package edu.uniandes.services.implement;

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

import edu.uniandes.services.daos.ReportesDAO;
import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.util.Constantes;
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
	public String obtenerReporte() {
		
		
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        Map parameters = new HashMap();
        String nombreArchivo ="reporterRatingDisenosArtistas.pdf";
        
        try {
        	jasperReport = JasperCompileManager.compileReport(Constantes.REPORTES_RUTA + "reporteRatingDisenoArtistas.jrxml");
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reportesDAO.consultarInfoReporteDisenosArtistas()));
//            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\template\\Desktop\\eclipse\\eclipse\\jboss-eap-6.3\\welcome-content\\reporterRatingDisenosArtistas.pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint,Constantes.PDF_RUTA + nombreArchivo);
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
		try {

			return nombreArchivo;
		} catch (Exception e) {
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
