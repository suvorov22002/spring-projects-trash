package com.afb.dpdl.ged.ws.rest.api.service.client.enga;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceEngagements {
	
	/**
	 * Recupere la liste des engagements du client
	 * @param matricule Matricule du client
	 * @return Objet JSON contenant les informations sur les engagements du client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getEngagementsJSON(String matricule) throws ClassNotFoundException, SQLException;
	

}
