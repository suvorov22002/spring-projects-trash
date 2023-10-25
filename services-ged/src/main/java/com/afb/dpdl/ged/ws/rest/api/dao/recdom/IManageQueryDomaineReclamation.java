package com.afb.dpdl.ged.ws.rest.api.dao.recdom;

import java.sql.SQLException;
import java.util.List;
public interface IManageQueryDomaineReclamation {

	/**
	 * Liste des domaines de reclamations
	 * @return Map des domaines de reclamations
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<String> getListeDomaineReclamation()  throws ClassNotFoundException, SQLException;
	
}
