package com.afb.dpdl.ged.ws.rest.api.dao.depcamer;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryDeptCamer {
	
	/**
	 * Recuperation de la liste des villes dans portal
	 * @return Map de la liste des villes dans portal
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */ 
	List<String> getListDeptCamer();
	
}
