package com.afb.dpdl.ged.ws.rest.api.dao.dernierjourouvre;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface IManageQueryDernierJourOuvre {
	
	/**
	 * Calcul du dernier jour d'une permission
	 * @param dateDeb Date de debut de la permission
	 * @param nbjrPerm Nombre de jours de la permission
	 * @return Map du dernier jour d'une permission
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */ 
	Map<String, Object> getDernierJourOuvre(Date dateDeb, Double nbjrPerm)  throws ClassNotFoundException, SQLException, ParseException;
	
}
