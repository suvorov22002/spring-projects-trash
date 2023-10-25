package com.afb.dpdl.ged.ws.rest.api.dao.calculquotite;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public interface IManageQueryCalculQuotite {
	
	/**
	 * Calcul de la quotite pour credit particuliers
	 * @param mensualite Mensualite du credit
	 * @param totMimp Total mensualites des impayees
	 * @param salm1 Salaire moyen
	 * @param salm2 Salaire moyen
	 * @param salm3 Salaire moyen
	 * @param autRev
	 * @param retenues Retenues
	 * @return Map de la quotite calculee pour credit particuliers
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> calculQuotite(String mensualite, String totMimp, String salm1, String salm2, String salm3,
			String autRev, String retenues, String montant)  throws ParseException;
	
}
