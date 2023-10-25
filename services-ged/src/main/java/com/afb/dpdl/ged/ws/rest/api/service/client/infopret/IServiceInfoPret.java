package com.afb.dpdl.ged.ws.rest.api.service.client.infopret;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceInfoPret {
	
	/**
	 * Recuperation des informations sur le pret simule
	 * @param numPret Numero du pret
	 * @param ncp Numero de compte du client
	 * @return Objet JSON contenant les informations sur le pret simule
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoPretJSON(String numPret, String ncp) throws ClassNotFoundException, SQLException;
	

}
