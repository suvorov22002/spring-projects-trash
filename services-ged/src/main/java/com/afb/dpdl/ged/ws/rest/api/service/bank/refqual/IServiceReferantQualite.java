package com.afb.dpdl.ged.ws.rest.api.service.bank.refqual;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceReferantQualite {
	
	/**
	 * Recuperation du Référant Qualité d'une Agence
	 * @param codeAgeUser Code de l'agence du client
	 * @return Le referant qualite sous forme d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getReferantQualiteJSON(String codeAgeUser) throws ClassNotFoundException, SQLException;
	

}
