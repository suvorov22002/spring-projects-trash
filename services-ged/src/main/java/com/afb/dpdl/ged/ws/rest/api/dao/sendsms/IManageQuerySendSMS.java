package com.afb.dpdl.ged.ws.rest.api.dao.sendsms;

import java.sql.SQLException;
import java.util.Map;

public interface IManageQuerySendSMS {

	/**
	 * Envoi de SMS
	 * @param message
	 * @param phoneNumber
	 * @param objet
	 * @return Statut du SMS envoyé
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, Object> sendSMS(String message, String phoneNumber, String objet)  throws ClassNotFoundException, SQLException;
	
}
