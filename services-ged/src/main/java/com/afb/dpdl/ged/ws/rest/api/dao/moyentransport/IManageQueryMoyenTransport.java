package com.afb.dpdl.ged.ws.rest.api.dao.moyentransport;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryMoyenTransport {

	/**
	 * Recuperation des moyens de transport dans portal
	 * @return Map des moyens de transport dans portal
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<String> getMoyenTransport()  throws ClassNotFoundException, SQLException;
	
}
