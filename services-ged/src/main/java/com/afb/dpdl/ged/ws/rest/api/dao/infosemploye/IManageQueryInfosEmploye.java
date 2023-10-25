package com.afb.dpdl.ged.ws.rest.api.dao.infosemploye;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IManageQueryInfosEmploye {
	
	/**
	 * Liste des engagements du client
	 * @param matricule Matricule du client
	 * @return Map des engagements du client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getInfosEmploye(String matricule)  throws ClassNotFoundException, SQLException;
	
	List<String> getMatVehiculeEmploye(String matricule) throws ClassNotFoundException, SQLException;
	
}
