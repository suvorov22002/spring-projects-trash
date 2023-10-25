package com.afb.dpdl.ged.ws.rest.api.dao.fraismission;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryFraisMission {

	
	/**
	 * Recuperation des frais de missions
	 * @param lieu Lieu de la mission
	 * @param nbrJours Nombre de jours de la mission
	 * @param grade Grade de l’employe
	 * @param fonction Fonction de l’employe
	 * @param moyenTrans Moyen de transport
	 * @param villedepart Ville de départ en mission
	 * @param villedestinataire Ville de la mission
	 * @return Map des frais de missions
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getFraisMission(String lieu, String nbrJours, String grade, String fonction, String moyenTrans, String villedepart,
			String villedestinataire)  throws ClassNotFoundException, SQLException;
	
}
