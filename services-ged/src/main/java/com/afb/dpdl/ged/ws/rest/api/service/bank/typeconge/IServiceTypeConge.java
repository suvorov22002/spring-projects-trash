package com.afb.dpdl.ged.ws.rest.api.service.bank.typeconge;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceTypeConge {

	/**
	 * Liste des Types de Conges
	 * @return Liste des Types de Conges sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeTypeCongeJSON() throws ClassNotFoundException, SQLException;

}
