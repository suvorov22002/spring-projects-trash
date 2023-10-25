package com.afb.dpdl.ged.ws.rest.api.service.client.infoclt;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceInfoClient {
	
	/**
	 * 
	 * @param matricule
	 * @param login
	 * @param ageUser
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoCliJSON(String matricule, String login, String ageUser) throws ClassNotFoundException, SQLException;
	

}
