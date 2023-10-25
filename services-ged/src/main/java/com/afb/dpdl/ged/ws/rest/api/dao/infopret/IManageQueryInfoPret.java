package com.afb.dpdl.ged.ws.rest.api.dao.infopret;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInfoPret {
	
	/**
	 * Recuperation des informations sur le pret simule
	 * @param numPret Numero du pret
	 * @param ncp Numero de compte du client
	 * @return Map des informations sur le pret simule
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getInfoPret(String numPret, String ncp)  throws ClassNotFoundException, SQLException;
	
}
