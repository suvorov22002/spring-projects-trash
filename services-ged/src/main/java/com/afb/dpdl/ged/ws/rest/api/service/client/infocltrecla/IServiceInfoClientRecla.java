package com.afb.dpdl.ged.ws.rest.api.service.client.infocltrecla;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceInfoClientRecla {
	
	/**
	 * Recuperation des infos d'un client sur la base de son matricule: uniquement pour les réclamations
	 * @param matricule
	 * @param login
	 * @param ageUser
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoCliReclaJSON(String matricule, String login, String ageUser) throws ClassNotFoundException, SQLException;
	

}
