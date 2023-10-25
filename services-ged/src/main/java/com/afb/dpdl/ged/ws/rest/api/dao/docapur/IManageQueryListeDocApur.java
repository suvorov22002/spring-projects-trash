package com.afb.dpdl.ged.ws.rest.api.dao.docapur;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryListeDocApur {

	/**
	 * Liste des documents apures
	 * @param refDos Reference du dossier
	 * @return Map des documents apures
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeDocApur(String refDos)  throws ClassNotFoundException, SQLException;
	
}
