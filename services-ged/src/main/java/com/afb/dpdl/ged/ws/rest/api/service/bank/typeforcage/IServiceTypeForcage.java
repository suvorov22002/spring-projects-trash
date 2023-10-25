package com.afb.dpdl.ged.ws.rest.api.service.bank.typeforcage;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceTypeForcage {

	/**
	 * Liste des Types de Forcage
	 * @return Liste des Types de Forcage sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeTypeForcageJSON() throws ClassNotFoundException, SQLException;

}
