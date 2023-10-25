package com.afb.dpdl.ged.ws.rest.api.service.client.numpret;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceNumeroPret {

	/**
	 * 
	 * @param matricule
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getNumpretClientJSON(String matricule) throws ClassNotFoundException, SQLException;

}
