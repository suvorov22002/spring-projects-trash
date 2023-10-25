package com.afb.dpdl.ged.ws.rest.api.dao.typeconge;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryTypeConge {

	/**
	 * Liste des Types de Conges
	 * @return Map des Types de Conges
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeTypeConge()  throws ClassNotFoundException, SQLException;
	
}
