package com.afb.dpdl.ged.ws.rest.api.service.bank.townlist;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeVille {
	
		
	/**
	 * Recuperation de la liste des villes dans portal
	 * @return La liste des villes dans portal sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeVilleJSON() throws ClassNotFoundException, SQLException;
	

}
