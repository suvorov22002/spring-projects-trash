package com.afb.dpdl.ged.ws.rest.api.dao.enga;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryEngagements {
	
	/**
	 * Liste des engagements du client
	 * @param matricule Matricule du client
	 * @return Map des engagements du client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getEngagements(String matricule)  throws ClassNotFoundException, SQLException;
	
}
