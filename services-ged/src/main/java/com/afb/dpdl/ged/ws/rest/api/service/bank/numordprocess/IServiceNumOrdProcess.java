package com.afb.dpdl.ged.ws.rest.api.service.bank.numordprocess;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceNumOrdProcess {
		
	/**
	 * Generation du numero d'ordre du processus
	 * @param codeAgeSaisie Code de l'agence de saisie
	 * @param idProcess ID du processus
	 * @return Objet JSON contenant le numero d'ordre du processus genere
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getNumOrdProcessJSON(String codeAgeSaisie, String idProcess) throws ClassNotFoundException, SQLException;
	

}
