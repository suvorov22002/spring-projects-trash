package com.afb.dpdl.ged.ws.rest.api.service.bank.sendsms;

import java.sql.SQLException;

import org.json.JSONObject;

public interface IServiceSendSMS {
	
	/**
	 * Envoi de SMS
	 * @param message
	 * @param phoneNumber
	 * @param objet
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject sendSMSJSON(String message, String phoneNumber, String objet) throws ClassNotFoundException, SQLException;
	

}
