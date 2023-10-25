package com.afb.dpdl.ged.ws.rest.api.service.bank.moyentransport;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceMoyenTransport {

	
	/**
	 * Recuperation des moyens de transport dans portal
	 * @return Les moyens de transport dans portal sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getMoyenTransportJSON() throws ClassNotFoundException, SQLException;

}
