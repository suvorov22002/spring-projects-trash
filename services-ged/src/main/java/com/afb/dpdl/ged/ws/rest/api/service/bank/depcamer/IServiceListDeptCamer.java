package com.afb.dpdl.ged.ws.rest.api.service.bank.depcamer;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListDeptCamer {
	
	/**
	 * Recuperation des informations d'un employe sur la base de son matricule
	 * @param matricule Matricule du client
	 * @return Objet JSON contenant les informations de l'employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONArray getListDeptCamerJSON() ;
	

}
