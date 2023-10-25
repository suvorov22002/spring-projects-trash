package com.afb.dpdl.ged.ws.rest.api.service.client.affauto;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceAffAuto {

	/**
	 * 
	 * @param matricule
	 * @param typeClient
	 * @param codeAgeSaisie
	 * @param montant
	 * @param secteurActivite
	 * @param currentPhase
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getUsersAffAutoJSON(String matricule, String typeClient, String codeAgeSaisie, Double montant, String secteurActivite, String currentPhase) throws ClassNotFoundException, SQLException;

}
