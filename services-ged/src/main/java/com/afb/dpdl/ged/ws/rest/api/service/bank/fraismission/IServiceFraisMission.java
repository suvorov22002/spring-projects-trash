package com.afb.dpdl.ged.ws.rest.api.service.bank.fraismission;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceFraisMission {
	
	
	
	/**
	 * Recuperation des frais de missions
	 * @param lieu Lieu de la mission
	 * @param nbrJours Nombre de jours de la mission
	 * @param grade Grade de l’employe
	 * @param fonction Fonction de l’employe
	 * @param moyenTrans Moyen de transport
	 * @param villedepart Ville de départ en mission
	 * @param villedestinataire Ville de la mission
	 * @return Les frais de missions sous forme d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getFraisMissionJSON(String lieu, String nbrJours, String grade, String fonction, String moyenTrans, String villedepart,
			String villedestinataire) throws ClassNotFoundException, SQLException;
	

}
