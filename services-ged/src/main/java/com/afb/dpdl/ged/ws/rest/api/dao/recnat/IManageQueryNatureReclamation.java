package com.afb.dpdl.ged.ws.rest.api.dao.recnat;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryNatureReclamation {

	/**
	 * Liste des Natures de reclamations
	 * @param domaine Domaine de la reclamation
	 * @return Map des Natures de reclamations
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeNatureReclamation(String domaine)  throws ClassNotFoundException, SQLException;
	
}
