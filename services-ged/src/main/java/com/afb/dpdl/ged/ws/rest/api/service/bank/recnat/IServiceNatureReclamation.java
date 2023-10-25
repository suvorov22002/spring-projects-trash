package com.afb.dpdl.ged.ws.rest.api.service.bank.recnat;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceNatureReclamation {
	
	/**
	 * Liste des Natures de reclamations
	 * @param domaine Domaine de la reclamation
	 * @return Liste des Natures de reclamations sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeNatureReclamationJSON(String domaine) throws ClassNotFoundException, SQLException;

}
