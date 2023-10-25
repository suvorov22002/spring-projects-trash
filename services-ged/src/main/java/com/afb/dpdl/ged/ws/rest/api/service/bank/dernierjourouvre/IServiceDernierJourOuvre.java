package com.afb.dpdl.ged.ws.rest.api.service.bank.dernierjourouvre;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONObject;

public interface IServiceDernierJourOuvre {
	
	
	/**
	 * Calcul du dernier jour d'une permission
	 * @param dateDeb Date de debut de la permission
	 * @param nbjrPerm Nombre de jours de la permission
	 * @return La date du dernier jour d'une permission sous la forme d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */ 
	JSONObject getDernierJourOuvreJSON(Date dateDeb, Double nbjrPerm) throws ClassNotFoundException, SQLException, ParseException;
	

}
