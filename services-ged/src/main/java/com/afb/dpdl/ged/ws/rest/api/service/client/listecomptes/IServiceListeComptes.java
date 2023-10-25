package com.afb.dpdl.ged.ws.rest.api.service.client.listecomptes;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeComptes {

	/**
	 * Recuperation de la liste des Comptes a partir du matricule de l'employe dans GRH
	 * @param matricule Matricule du client
	 * @return La liste des comptes de l'employe sous la forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeComptesJSON(String matricule) throws ClassNotFoundException, SQLException;
	

}
