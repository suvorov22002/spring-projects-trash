package com.afb.dpdl.ged.ws.rest.api.dao.refqual;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryReferantQualite {
	
	/**
	 * Recuperation du Referant Qualite d'une Agence
	 * @param codeAgeUser Code agence du client 
	 * @return Le Referant Qualite de Agence
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getReferantQualite(String codeAgeUser)  throws ClassNotFoundException, SQLException;
	
}
