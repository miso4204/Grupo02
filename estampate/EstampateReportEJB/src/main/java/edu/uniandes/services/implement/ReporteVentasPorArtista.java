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
import edu.uniandes.services.interfaces.IReporteVentas;
import edu.uniandes.services.vos.ReporteVentaArtistaVO;

/**
 * Producto reporte de ventas por artista
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteVentasPorArtista implements IReporteVentas {
	
	private ReportesDAO reportesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.interfaces.IReporteVentas#obtenerReporte()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public byte[] obtenerReporte() {
		
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        Map parameters = new HashMap();
        try {
        	jasperReport = JasperCompileManager.compileReport("C:\\Users\\template\\Desktop\\Vabrik\\Private Tunnel.lnk\\estampate\\EstampateReportEJB\\src\\main\\java\\edu\\uniandes\\services\\implement\\reporteVentasArtista.jrxml");
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reportesDAO.consultarInformacionReporteVentasPorArtista()));
            JasperExportManager.exportReportToPdfFile(jasperPrint,"reporteVentasArtista.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
        File file = new File("reporteVentasArtista.pdf");
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ReporteVentaArtistaVO> getList(){
		return new ArrayList<ReporteVentaArtistaVO>();
	}

	public void setReportesDAO(ReportesDAO reportesDAO) {
		this.reportesDAO = reportesDAO;
	}

}
