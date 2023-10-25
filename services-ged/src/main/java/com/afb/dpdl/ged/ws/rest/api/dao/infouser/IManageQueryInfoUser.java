package com.afb.dpdl.ged.ws.rest.api.dao.infouser;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInfoUser {
	
	
	/**
	 * Affichage des infos sur l'utilisateur connecte a partir de son login
	 * @param login Login de l'utilisateur
	 * @return Map des infos sur l'utilisateur connecte
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getInfoUser(String login)  throws ClassNotFoundException, SQLException;
	
}
