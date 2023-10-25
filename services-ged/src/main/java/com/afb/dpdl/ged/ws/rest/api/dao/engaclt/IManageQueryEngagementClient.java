package com.afb.dpdl.ged.ws.rest.api.dao.engaclt;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryEngagementClient {
	
	
	/**
	 * Liste des engagements du client
	 * @param matricule Matricule du client
	 * @param ncp Numero de compte du client
	 * @return Map de la liste des engagements du client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getEngagementClient(String matricule, String ncp)  throws ClassNotFoundException, SQLException;
	
}
