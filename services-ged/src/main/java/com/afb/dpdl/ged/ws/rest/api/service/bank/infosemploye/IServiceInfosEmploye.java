package com.afb.dpdl.ged.ws.rest.api.service.bank.infosemploye;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IServiceInfosEmploye {
	
	/**
	 * Recuperation des informations d'un employe sur la base de son matricule
	 * @param matricule Matricule du client
	 * @return Objet JSON contenant les informations de l'employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfosEmployeJSON(String matricule) throws ClassNotFoundException, SQLException;
	
	JSONArray getInfosEmployeJSON_TEST(String matricule) throws ClassNotFoundException, SQLException;
	
	JSONArray getMatVehiculeEmployeJSON(String matricule) throws ClassNotFoundException, SQLException;

}
