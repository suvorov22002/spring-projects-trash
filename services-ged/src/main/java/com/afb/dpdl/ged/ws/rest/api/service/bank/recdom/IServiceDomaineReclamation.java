package com.afb.dpdl.ged.ws.rest.api.service.bank.recdom;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceDomaineReclamation {
	
	/**
	 * Liste des domaines de reclamations
	 * @return Liste des domaines de reclamations sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListeDomaineReclamationJSON() throws ClassNotFoundException, SQLException;

}
