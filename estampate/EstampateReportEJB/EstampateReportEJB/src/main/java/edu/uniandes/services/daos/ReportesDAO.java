package edu.uniandes.services.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.services.vos.ReporteRatingDisenoArtistaVO;
import edu.uniandes.services.vos.ReporteRatingDisenoVO;
import edu.uniandes.services.vos.ReporteVentaArtistaVO;
import edu.uniandes.services.vos.ReporteVentaPeriodoVO;

/**
 * Acceso a datos para la generacion de reportes.
 * 
 * @author Juan Camilo Cerquera Lozada<jc.cerquera10@uniandes.edu.co>
 * 
 */
@Stateless
@LocalBean
public class ReportesDAO {

	@PersistenceContext(unitName = "estampatePU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Consulta la informacion de rating de disenos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteRatingDisenoVO> consultarInfoReporteRatingDisenos() {
		List<ReporteRatingDisenoVO> list = new ArrayList<>();
		Query query = em.createNativeQuery(""
		    +"SELECT ESTAMPA.NOMBRE, "+
			  "ESTAMPA.INFORMACION, "+
			  "ESTAMPA.RATTING, "+
			  "ESTAMPA.CANTIDAD_VOTOS, "+
			  "PERSONA.NOMBRES, "+
			  "PERSONA.APELLIDOS "+
			"FROM ESTAMPA, "+
			  "USUARIO, "+
			  "PERSONA "+
			"WHERE ESTAMPA.USUARIO = USUARIO.ID "+
			"AND USUARIO.PERSONA   = PERSONA.ID "+
			"ORDER BY RATTING DESC");
		List<Object[]> result = query.getResultList();
		for (Object[] objects : result) {
			ReporteRatingDisenoVO info = new ReporteRatingDisenoVO();
			info.setNombreEstampa((String) objects[0]);
			info.setInformacionEstampa((String) objects[1]);
			info.setRating(((BigDecimal) objects[2]).toString());
			info.setCantidadVotos(((BigDecimal) objects[3]).toString());
			info.setNombresArtista((String) objects[4]);
			info.setApellidosArtista((String) objects[5]);
			list.add(info);
		}
		return list;
	}
	
	/**
	 * Consulta la informacion de rating de disenos por artistas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteRatingDisenoArtistaVO> consultarInfoReporteDisenosArtistas(){
		List<ReporteRatingDisenoArtistaVO> list = new ArrayList<>();
		Query query = em.createNativeQuery(""
				+ "SELECT PERSONA.NOMBRES, "
				+ "PERSONA.APELLIDOS, "
				+ "AVG(ESTAMPA.RATTING) AS PROMEDIO_disenoS "
				+ "FROM USUARIO, "
				+ "PERSONA, "
				+ "ESTAMPA "
				+ "WHERE USUARIO.PERSONA = PERSONA.ID "
				+ "AND ESTAMPA.USUARIO   = USUARIO.ID "
				+ "GROUP BY PERSONA.NOMBRES, "
				+ "PERSONA.APELLIDOS");
		List<Object[]> result = query.getResultList();
		for (Object[] objects : result) {
			ReporteRatingDisenoArtistaVO info = new ReporteRatingDisenoArtistaVO();
			info.setNombresArtista((String) objects[0]);
			info.setApellidosArtista((String) objects[1]);
			info.setPromedioRatingDiseno(((BigDecimal) objects[2]).toString());
			list.add(info);
		}
		return list;
	}
	
	/**
	 * Consulta la informacion para el reporte de ventas por periodo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteVentaPeriodoVO> consultarInfoReporteVentasPorPeriodo(){
		List<ReporteVentaPeriodoVO> list = new ArrayList<>();
		Query query = em.createNativeQuery(""
				+ "SELECT TO_CHAR(VENTA.FECHA, 'MM') AS MES, "
				+ "TO_CHAR(VENTA.FECHA, 'YYYY')    AS ANIO, "
				+ "COUNT(*) AS CANTIDAD_VENTAS, "
				+ "SUM(CARRITO.VALOR_TOTAL) AS TOTAL_VENTAS "
				+ "FROM VENTA, "
				+ "CARRITO_COMPRAS CARRITO, "
				+ "CAMISETA, "
				+ "ESTAMPA, "
				+ "USUARIO, "
				+ "PERSONA "
				+ "WHERE VENTA.CARRITO  = CARRITO.ID "
				+ "AND CAMISETA.CARRITO = CARRITO.ID "
				+ "AND CAMISETA.ESTAMPA = ESTAMPA.ID "
				+ "AND ESTAMPA.USUARIO  = USUARIO.ID "
				+ "AND USUARIO.PERSONA  = PERSONA.ID "
				+ "GROUP BY TO_CHAR(VENTA.FECHA, 'MM'), "
				+ "TO_CHAR(VENTA.FECHA, 'YYYY')");
		List<Object[]> result = query.getResultList();
		for (Object[] objects : result) {
			ReporteVentaPeriodoVO info = new ReporteVentaPeriodoVO();
			info.setMesVenta(((String) objects[0]).toString());
			info.setAnioVenta(((String) objects[1]).toString());
			info.setCantidadVentas(((BigDecimal) objects[2]).toString());
			info.setTotalVentas(((BigDecimal) objects[3]).toString());
			list.add(info);
		}
		return list;
	}
	
	/**
	 * Consulta la informacion de ventas por artista
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ReporteVentaArtistaVO> consultarInformacionReporteVentasPorArtista(){
		List<ReporteVentaArtistaVO> list = new ArrayList<>();
		Query query = em.createNativeQuery(""
				+ "SELECT PERSONA.NOMBRES, "
				+ "PERSONA.APELLIDOS, "
				+ "COUNT(*) CANTIDAD_VENTAS, "
				+ "SUM(CARRITO.VALOR_TOTAL) TOTAL_VENTAS "
				+ "FROM VENTA, "
				+ "CARRITO_COMPRAS CARRITO, "
				+ "CAMISETA, "
				+ "ESTAMPA, "
				+ "USUARIO, "
				+ "PERSONA "
				+ "WHERE VENTA.CARRITO  = CARRITO.ID "
				+ "AND CAMISETA.CARRITO = CARRITO.ID "
				+ "AND CAMISETA.ESTAMPA = ESTAMPA.ID "
				+ "AND ESTAMPA.USUARIO  = USUARIO.ID "
				+ "AND USUARIO.PERSONA  = PERSONA.ID "
				+ "GROUP BY PERSONA.NOMBRES, "
				+ "PERSONA.APELLIDOS");
		List<Object[]> result = query.getResultList();
		for (Object[] objects : result) {
			ReporteVentaArtistaVO info = new ReporteVentaArtistaVO();
			info.setNombresArtista((String) objects[0]);
			info.setApellidosArtista((String) objects[1]);
			info.setCantidadVentas(((BigDecimal) objects[2]).toString());
			info.setTotalVentas(((BigDecimal) objects[3]).toString());
			list.add(info);
		}
		
		return list;
	}
	
	

}
