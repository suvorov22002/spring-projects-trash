package com.afb.dpdl.ged.ws.rest.api.dao.insertmission;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInsertMissionPortal {

	
	/**
	 * Insertion des donnees relatives aux missions dans portal
	 * @param nom Nom de la mission
	 * @param ref Reference de la mission
	 * @param fonction Fonction de l’employe
	 * @param grade Grade de l’employe
	 * @param cellule Unite de l’employe
	 * @param ncp Numero de compte de l’employe
	 * @param nbrJours Nombre de jours de la mission
	 * @param moyenTrans Moyen de transport de l’employe
	 * @param unitebudg Unite budgetaire
	 * @param villeDepart Ville de depart de l’employe
	 * @param villeDestinataire Ville de la mission
	 * @param paysAfricain Pays Africain de la mission
	 * @param paysEtranger Pays Etranger de la mission
	 * @param codeOpe Code operation
	 * @param transportReel Frais de transport reel
	 * @param missionReel  Frais de mission reel
	 * @param ncpTransport Numero de compte frais de transport
	 * @param ncpMission Numero de compte frais de mission
	 * @param cutiAnalComp Code utilisateur ***
	 * @param cutiDcompt Code utilisateur ***
	 * @return Statut de l'insertion des donnees de la mission
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> insertMissionPortal(String nom, String ref, String fonction, String grade, String cellule, String ncp, int nbrJours, String moyenTrans, 
			String unitebudg, String villeDepart, String villeDestinataire, String paysAfricain, String paysEtranger, String codeOpe, double transportReel, 
			double missionReel, String ncpTransport, String ncpMission, String cutiAnalComp, String cutiDcompt)  throws ClassNotFoundException, SQLException;
	
}
