package com.afb.dpdl.ged.ws.rest.api.dao.listeanomalies;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryListeAnomalies {

	/**
	 * Recuperation de toutes les anomalies predefinies par le controle permanent
	 * @return Map des anomalies
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeAnomalies()  throws ClassNotFoundException, SQLException;
	
}
