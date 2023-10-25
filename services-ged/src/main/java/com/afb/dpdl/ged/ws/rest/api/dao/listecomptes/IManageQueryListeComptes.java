package com.afb.dpdl.ged.ws.rest.api.dao.listecomptes;

import java.sql.SQLException;
import java.util.List;

public interface IManageQueryListeComptes {

	/**
	 * Recuperation de la liste des Comptes a partir du matricule de l'employe dans GRH
	 * @param matricule Matricule du client
	 * @return Map de la liste des comptes de l'employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<String> getListeComptes(String matricule)  throws ClassNotFoundException, SQLException;
	
}
