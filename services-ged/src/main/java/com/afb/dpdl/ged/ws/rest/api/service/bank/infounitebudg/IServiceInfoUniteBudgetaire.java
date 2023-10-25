package com.afb.dpdl.ged.ws.rest.api.service.bank.infounitebudg;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceInfoUniteBudgetaire {
	
	/**
	 * Recuperation des informations sur l'unite budgetaire
	 * @param unitebudg Libelle unite budgetaire
	 * @param lieu Lieu de la mission
	 * @return Objet JSON contenant les informations sur l'unite budgetaire
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoUniteBudgetaireJSON(String unitebudg, String lieu) throws ClassNotFoundException, SQLException;
	

}
