package com.afb.dpdl.ged.ws.rest.api.service.bank.testdispo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONObject;

public interface IServiceTestDispoPeriode {
	
	/**
	 * Recuperation des infos d'un client sur la base de son matricule: uniquement pour les r�clamations
	 * @param matricule Matricule du client
	 * @param dateDeb Date de debut de periode
	 * @param dateFin Date de fin de periode
	 * @return L'etat de disponibilite de l'employe sous forme d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 */
	JSONObject getTestDispoPeriodeJSON(String matricule, Date dateDeb, Date dateFin) throws ClassNotFoundException, SQLException, ParseException;
	
	JSONObject getTestDispoPeriodeMissionJSON(String matricule, Date dateDeb, Date dateFin) throws ClassNotFoundException, SQLException, ParseException;

}
