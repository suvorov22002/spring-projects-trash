package com.afb.dpdl.ged.ws.rest.api.dao.affauto;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryAffAuto {

	/**
	 * Recuperation des Utilisateurs a qui affecter le dossier de credit pour la phase en cours de traitement
	 * @param matricule
	 * @param typeClient
	 * @param codeAgeSaisie
	 * @param montant
	 * @param secteurActivite
	 * @param currentPhase
	 * @return La liste des utilisateurs a qui affecter le dossier de credit pour la phase en cours de traitement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getUsersAffAuto(String matricule, String typeClient, String codeAgeSaisie, Double montant, String secteurActivite, String currentPhase)  throws ClassNotFoundException, SQLException;
	
}
