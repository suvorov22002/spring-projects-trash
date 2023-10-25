package com.afb.dpdl.ged.ws.rest.api.service.client.cptclt;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceCompteClient {

	/**
	 * 
	 * @param matricule
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getComptesClientJSON(String matricule) throws ClassNotFoundException, SQLException;
	

}
