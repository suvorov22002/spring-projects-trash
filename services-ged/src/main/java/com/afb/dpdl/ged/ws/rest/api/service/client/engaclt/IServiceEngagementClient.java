package com.afb.dpdl.ged.ws.rest.api.service.client.engaclt;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceEngagementClient {
	
	/**
	 * Recupere la liste des engagements du client
	 * @param matricule Matricule du client
	 * @param ncp Numero de compte du client
	 * @return Objet JSON contenant les information sur les engagements du client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getEngagementCliJSON(String matricule, String ncp) throws ClassNotFoundException, SQLException;
	

}
