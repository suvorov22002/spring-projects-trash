package com.afb.dpdl.ged.ws.rest.api.dao.listedev;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryListeDevive {
	
	/**
	 * Recuperation de la liste des devises existantes dans Amplitude
	 * @return Map des devises existantes dans Amplitude
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */ 
	List<String> getListeDevise()  throws ClassNotFoundException, SQLException;
	
}
