package com.afb.dpdl.ged.ws.rest.api.service.bank.typedossier;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceTypeDossierFin {

	/**
	 * Liste des Types de Dossiers de Financement
	 * @return Liste des Types de Dossiers de Financement sous forme de tableau d'objets JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeTypeDossierFinJSON() throws ClassNotFoundException, SQLException;

}
