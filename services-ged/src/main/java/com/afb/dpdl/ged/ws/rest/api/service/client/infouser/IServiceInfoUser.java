package com.afb.dpdl.ged.ws.rest.api.service.client.infouser;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceInfoUser {
	
	/**
	 * 
	 * @param login
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoUserJSON(String login) throws ClassNotFoundException, SQLException;
	

}
