package com.afb.dpdl.ged.ws.rest.api.dao.listeunitesv2;

import java.sql.SQLException;
import java.util.List;


public interface IManageQueryListeUnitesV2 {

	/**
	 * Liste des unites parametrees dans le Core Banking
	 * @return Map de liste des unites parametrees dans le Core Banking
	 * @throws ClassNotFoundExceptions
	 * @throws SQLException
	 */
	List<String> getListeUnitesV2()  throws ClassNotFoundException, SQLException;
	
}
