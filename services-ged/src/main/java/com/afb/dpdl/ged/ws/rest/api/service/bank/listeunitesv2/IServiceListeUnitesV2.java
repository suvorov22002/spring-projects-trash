package com.afb.dpdl.ged.ws.rest.api.service.bank.listeunitesv2;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeUnitesV2 {

	/**
	 * Liste des unites parametrees dans le Core Banking
	 * @return Liste des unites parametrees dans le Core Banking sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeUnitesV2JSON() throws ClassNotFoundException, SQLException;

}
