package com.afb.dpdl.ged.ws.rest.api.dao.listeunites;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryListeUnites {

	/**
	 * Recuperation de toutes les unites/Directions dans l'annuaire
	 * @return Map des unites parametrees dans l'annuaire
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeUnites()  throws ClassNotFoundException, SQLException;
	
}
