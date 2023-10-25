package com.afb.dpdl.ged.ws.rest.api.dao.testdispo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface IManageQueryTestDispoPeriode {
	
	/**
	 * Test pour un employe que la periode sollicitee pour sa permission ou son conge est disponible
	 * @param matricule Matricule du client
	 * @param dateDeb Date de debut de periode
	 * @param dateFin Date de fin de periode
	 * @return Map de l'etat de disponibilite de l'employe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	Map getTestDispoPeriode(String matricule, Date dateDeb, Date dateFin)  throws ClassNotFoundException, SQLException, ParseException;
	
	Map getTestDispoPeriodeMission(String matricule, Date dateDeb, Date dateFin)  throws ClassNotFoundException, SQLException, ParseException;
	
}
