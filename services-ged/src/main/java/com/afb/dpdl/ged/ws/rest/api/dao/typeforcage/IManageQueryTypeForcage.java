package com.afb.dpdl.ged.ws.rest.api.dao.typeforcage;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryTypeForcage {

	/**
	 * Liste des Types de Forcage
	 * @return Map des Types de Forcage
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeTypeForcage()  throws ClassNotFoundException, SQLException;
	
}
