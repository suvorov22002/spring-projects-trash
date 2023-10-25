package com.afb.dpdl.ged.ws.rest.api.service.bank.listeanomalies;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeAnomalies {

	
	/**
	 * Recuperation de toutes les anomalies predefinies par le controle permanent
	 * @return Liste des anomalies sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeAnomaliesJSON() throws ClassNotFoundException, SQLException;

}
