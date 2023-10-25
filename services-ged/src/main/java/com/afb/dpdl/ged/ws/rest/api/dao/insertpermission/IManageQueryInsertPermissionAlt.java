package com.afb.dpdl.ged.ws.rest.api.dao.insertpermission;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public interface IManageQueryInsertPermissionAlt {

	/**
	 * Insertion des données relatives aux missions dans AltGRH
	 * @param matricule Matricule de l'employe
	 * @param dateDepart Date de depart en permission
	 * @param dateRetour Date de retour de la permission
	 * @param motif Motif de la permission
	 * @param loginUtil Login de l'utilisateur
	 * @param dateModif Date de modification de la permission
	 * @param priseCpte 
	 * @param nbjrs Nombre de jours de la permission
	 * @param codeConge Code du conge
	 * @return Map du statut du traitement de l'insertion
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> insertPermissionAlt(String matricule, String dateDepart, String dateRetour, String motif, String loginUtil, String dateModif,
			int priseCpte, int nbjrs, String codeConge)  throws ClassNotFoundException, SQLException, ParseException;
	
}
