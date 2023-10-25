package com.afb.dpdl.ged.ws.rest.api.service.bank.listedev;

import java.sql.SQLException;

import org.json.JSONArray;

public interface IServiceListeDevise {
	
	
	/**
	 * Recuperation de la liste des devises existantes dans Amplitude
	 * @return La liste des devises existantes dans Amplitude sous forme de tableau d'objet JSON
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */ 
	JSONArray getListeDeviseJSON() throws ClassNotFoundException, SQLException;
	

}
