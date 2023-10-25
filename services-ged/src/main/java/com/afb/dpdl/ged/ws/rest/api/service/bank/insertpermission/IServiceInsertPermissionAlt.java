package com.afb.dpdl.ged.ws.rest.api.service.bank.insertpermission;

import java.sql.SQLException;
import java.text.ParseException;

import org.json.JSONObject;

public interface IServiceInsertPermissionAlt {
	
	
	/**
	 * Insertion des donnees relatives aux missions dans AltGRH
	 * @param matricule Matricule de l'employe
	 * @param dateDepart Date de depart en permission
	 * @param dateRetour Date de retour de la permission
	 * @param motif Motif de la permission
	 * @param loginUtil Login de l'utilisateur
	 * @param dateModif Date de modification de la permission
	 * @param priseCpte 
	 * @param nbjrs Nombre de jours de la permission
	 * @param codeConge Code du conge
	 * @return Le statut du traitement de l'insertion au format JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject insertPermissionAltJSON(String matricule, String dateDepart, String dateRetour, String motif, String loginUtil, String dateModif,
			int priseCpte, int nbjrs, String codeConge) throws ClassNotFoundException, SQLException, ParseException ;
	

}
