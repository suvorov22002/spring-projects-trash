package com.afb.dpdl.ged.ws.rest.api.dao.infounitebudg;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQueryInfoUniteBudgetaire {
	
	/**
	 * Recuperation des informations sur l'unite budgetaire
	 * @param unitebudg Libelle unite budgetaire
	 * @param lieu Lieu de la mission
	 * @return Map des informations sur l'unite budgetaire
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> getInfoUniteBudgetaire(String unitebudg, String lieu)  throws ClassNotFoundException, SQLException;
	
}
