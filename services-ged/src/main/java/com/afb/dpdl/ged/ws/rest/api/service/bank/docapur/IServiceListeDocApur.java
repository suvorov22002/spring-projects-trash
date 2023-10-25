package com.afb.dpdl.ged.ws.rest.api.service.bank.docapur;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeDocApur {

	/**
	 * Liste des documents apures
	 * @param refDos Reference du dossier
	 * @return Liste des documents apures sous la forme d'un tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeDocApurJSON(String refDos) throws ClassNotFoundException, SQLException;
	

}
