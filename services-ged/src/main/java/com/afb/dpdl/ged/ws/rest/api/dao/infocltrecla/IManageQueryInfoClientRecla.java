package com.afb.dpdl.ged.ws.rest.api.dao.infocltrecla;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInfoClientRecla {
	
	/**
	 * Recuperation des informations d'un client sur la base de son matricule: uniquement pour les réclamations
	 * @param matricule Matricule du client
	 * @param login Login de l'utilisateur
	 * @param codeAgeUsr Code de l'agence de l'utilisateur
	 * @return Map des informations d'un client
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getInfoClientRecla(String matricule, String login, String codeAgeUsr)  throws ClassNotFoundException, SQLException;
	
}
