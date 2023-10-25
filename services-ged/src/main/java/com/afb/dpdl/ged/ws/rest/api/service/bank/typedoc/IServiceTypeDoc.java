package com.afb.dpdl.ged.ws.rest.api.service.bank.typedoc;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceTypeDoc {
	
	/**
	 * Liste des Types de Documents par dossier de financement
	 * @param typedossier ID du dossier
	 * @param typeclient Type de client
	 * @return Liste des Types de Documents par dossier de financement sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeTypeDocJSON(String typedossier, String typeclient) throws ClassNotFoundException, SQLException;

}
