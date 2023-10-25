package com.afb.dpdl.ged.ws.rest.api.dao.cptclt;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryCompteClient {

	/**
	 * Liste des Comptes du Client
	 * @param matricule Matricule du client
	 * @return Liste des Comptes du Client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<String> getComptesClient(String matricule)  throws ClassNotFoundException, SQLException;
	
}
