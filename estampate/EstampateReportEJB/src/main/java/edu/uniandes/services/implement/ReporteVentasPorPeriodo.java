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
import edu.uniandes.services.interfaces.IReporteVentas;
import edu.uniandes.services.util.Constantes;
import edu.uniandes.services.vos.ReporteVentaPeriodoVO;

/**
 * Producto reporte de ventas por periodo
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
public class ReporteVentasPorPeriodo implements IReporteVentas {
	
	private ReportesDAO reportesDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.uniandes.services.interfaces.IReporteVentas#obtenerReporte()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String obtenerReporte() {
		System.out.println("Inicio obtener reporte");
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        Map parameters = new HashMap();
        String nombreArchivo ="reporteVentasPeriodo.pdf";
        
        try {
        	System.out.println("Try");
        	jasperReport = JasperCompileManager.compileReport(Constantes.REPORTES_RUTA + "reporteVentasPeriodo.jrxml");
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reportesDAO.consultarInfoReporteVentasPorPeriodo()));
            JasperExportManager.exportReportToPdfFile(jasperPrint,Constantes.PDF_RUTA + nombreArchivo);
            System.out.println("Termino");
			
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
	
	public static List<ReporteVentaPeriodoVO> getList(){
		return new ArrayList<ReporteVentaPeriodoVO>();
	}

	public void setReportesDAO(ReportesDAO reportesDAO) {
		this.reportesDAO = reportesDAO;
	}

}
