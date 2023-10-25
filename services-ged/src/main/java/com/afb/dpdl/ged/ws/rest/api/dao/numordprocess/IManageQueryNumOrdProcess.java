package com.afb.dpdl.ged.ws.rest.api.dao.numordprocess;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryNumOrdProcess {
	
	/**
	 * Generation du numero d'ordre du processus
	 * @param codeAgeSaisie Code de l'agence de saisie
	 * @param idProcess ID du processus
	 * @return Map du numero d'ordre du processus genere
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getNumOrdProcess(String codeAgeSaisie, String idProcess)  throws ClassNotFoundException, SQLException;
	
}
