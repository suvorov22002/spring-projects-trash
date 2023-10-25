package com.afb.dpdl.ged.ws.rest.api.service.client.calculquotite;

import java.text.ParseException;

import org.json.JSONObject;

public interface IServiceCalculQuotite {
	
	/**
	 * Calcul de la quotite pour credit particuliers
	 * @param mensualite Mensualite du credit
	 * @param totMimp Total mensualites des impayees
	 * @param salm1 Salaire moyen
	 * @param salm2 Salaire moyen
	 * @param salm3 Salaire moyen
	 * @param autRev
	 * @param retenues Retenues
	 * @return La quotite calculee pour credit particuliers sous la forme d'un objet JSON
	 * @throws ParseException 
	 */
	JSONObject calculQuotiteJSON(String mensualite, String totMimp, String salm1, String salm2, String salm3, String autRev, String retenues, String montant) throws ParseException;
	

}
