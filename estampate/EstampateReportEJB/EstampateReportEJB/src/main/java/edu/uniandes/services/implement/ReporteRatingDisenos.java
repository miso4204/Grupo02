package edu.uniandes.services.implement;

import java.io.File;
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
import edu.uniandes.services.vos.ReporteRatingDisenoVO;

/**
 * Producto reporte de ratting de dise�os
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteRatingDisenos implements IReporteRating {
	
	private ReportesDAO reportesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.interfaces.IReporteRating#obtenerReporte()
	 */
	@Override
	public byte[] obtenerReporte() {
		
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
//        JasperDesign jasperDesign = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        try {
//        	File reporteJasper = null;
//			reporteJasper = new File();
        	jasperReport = JasperCompileManager.compileReport("C:/Users/template/Desktop/Vabrik/Private Tunnel.lnk/estampate/EstampateReportEJB/EstampateReportEJB/src/main/java/edu/uniandes/services/implement/reporteRatingDiseno.jrxml");
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reportesDAO.consultarInfoReporteRatingDisenos()));
            JasperExportManager.exportReportToPdfFile(jasperPrint,"reporterRatingDise�os.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
        File file = new File("reporterRatingDise�os.pdf");
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ReporteRatingDisenoVO> getList(){
		return new ArrayList<ReporteRatingDisenoVO>();
	}

	public void setReportesDAO(ReportesDAO reportesDAO) {
		this.reportesDAO = reportesDAO;
	}
	
	

}
