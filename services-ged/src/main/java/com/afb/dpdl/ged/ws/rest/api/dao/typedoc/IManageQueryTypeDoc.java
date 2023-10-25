package com.afb.dpdl.ged.ws.rest.api.dao.typedoc;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryTypeDoc {

	/**
	 * Liste des Types de Documents par dossier de financement
	 * @param typedossier ID du type de dossier
	 * @param typeclient Type de client
	 * @return Map des Types de Documents par dossier de financement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getListeTypeDoc(String typedossier, String typeclient)  throws ClassNotFoundException, SQLException;
	
}
