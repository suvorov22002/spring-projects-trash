package com.afb.dpdl.ged.ws.rest.api.service.bank.sendsms;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.sendsms.IManageQuerySendSMS;

public class ServiceSendSMSImpl implements IServiceSendSMS{

	@Inject
	private IManageQuerySendSMS manageQuerySendSMS;
	

	@Override
	public JSONObject sendSMSJSON(String message, String phoneNumber, String objet)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQuerySendSMS.sendSMS(message, phoneNumber, objet);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
