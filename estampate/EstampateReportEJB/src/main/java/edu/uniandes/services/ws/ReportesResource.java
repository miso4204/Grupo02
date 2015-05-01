package edu.uniandes.services.ws;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.uniandes.services.daos.ReportesDAO;
import edu.uniandes.services.enums.TiposReportes;
import edu.uniandes.services.fabricas.FabricaReportes;
import edu.uniandes.services.fabricas.FabricaReportesDefault;
import edu.uniandes.services.fabricas.FabricaReportesPorArtista;
import edu.uniandes.services.interfaces.IReporteRating;
import edu.uniandes.services.interfaces.IReporteVentas;

/**
 * Web service exponiendo el servicio de generaci�n de reportes
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
@Path("/reportes")
@Stateless
@LocalBean
public class ReportesResource {
	
	@EJB
	ReportesDAO reportesDAO;

	@GET
	@Path("/getReporte/{idReporte}")
	public byte[] getReporte(@PathParam("idReporte") String idReporte) {
		byte[] reporte = null;

		FabricaReportes fabricaReportes = null;

		// Implementa una f�brica abstracta para la generaci�n de reportes
		if (idReporte.equals(TiposReportes.REPORTE_RATING_DISENO.getIdReporte())) {
			fabricaReportes = new FabricaReportesDefault();
			IReporteRating reporteRating = fabricaReportes.crearReporteRating();
			reporteRating.setReportesDAO(reportesDAO);
			return reporteRating.obtenerReporte();
		} else if (idReporte.equals(TiposReportes.REPORTE_VENTAS_PERIODO.getIdReporte())) {
			System.out.println("Inicio generar reporte");
			fabricaReportes = new FabricaReportesDefault();
			System.out.println("Creada fabrica");
			IReporteVentas reporteVentas = fabricaReportes.crearReporteVentas();
			System.out.println("Creado reporte");
			reporteVentas.setReportesDAO(reportesDAO);
			System.out.println("Set de reporte");
			return reporteVentas.obtenerReporte();
		} else if (idReporte.equals(TiposReportes.REPORTE_RATING_DISENO_ARTISTA.getIdReporte())) {
			fabricaReportes = new FabricaReportesPorArtista();
			IReporteRating reporteRating = fabricaReportes.crearReporteRating();
			reporteRating.setReportesDAO(reportesDAO);
			return reporteRating.obtenerReporte();
		} else if (idReporte.equals(TiposReportes.REPORTE_VENTAS_ARTISTA.getIdReporte())) {
			fabricaReportes = new FabricaReportesPorArtista();
			IReporteVentas reporteVentas = fabricaReportes.crearReporteVentas();
			reporteVentas.setReportesDAO(reportesDAO);
			return reporteVentas.obtenerReporte();
		}
		return reporte;
	}
}
