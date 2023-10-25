package com.afb.dpdl.ged.ws.rest.api.dao.typedossier;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryTypeDossierFin {

	/**
	 * Liste des Types de Dossiers de Financement
	 * @return Map des Types de Dossiers de Financement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeTypeDossierFin()  throws ClassNotFoundException, SQLException;
	
}
