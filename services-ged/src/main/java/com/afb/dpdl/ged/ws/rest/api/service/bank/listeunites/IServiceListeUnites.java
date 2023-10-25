package com.afb.dpdl.ged.ws.rest.api.service.bank.listeunites;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeUnites {

	/**
	 * Recuperation de toutes les unites/Directions dans l'annuaire
	 * @return Liste des unites parametrees dans l'annuaire sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeUnitesJSON() throws ClassNotFoundException, SQLException;

}
