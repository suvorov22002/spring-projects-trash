package com.afb.dpdl.ged.ws.rest.api.dao.sendsms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQuerySendSMSImpl implements IManageQuerySendSMS{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_NEXTVAL_SMS = "SELECT nextval('seq_sms_cont')";
	
	private final String REQ_FBP_INSERT_SMS = "insert INTO sms_cont(id, code, name, uti, message, telephs, statuts, acurecp, datesai, heure) "
			+ "VALUES (?, 'GED',?,'GED',?,?,'A',0,?,?);";
	
	
	@Override
	public Map<String, Object> sendSMS(String message, String phoneNumber, String objet) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		try {
			Object[] parameters = {};
			Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
			PreparedStatement st = con.prepareStatement(REQ_FBP_NEXTVAL_SMS);
			ResultSet rs = runSqlQuery.selectData(st , null);
			rs.next();
			long id = rs.getLong("nextval");
			Utils.closeResulSet(null, st);
			
			parameters = new Object[] {id, objet.length() > 15 ? objet.substring(0, 15) : objet, message, phoneNumber, new java.sql.Date(new Date().getTime()), new SimpleDateFormat("HH:mm:ss").format(new Date())};
			st = con.prepareStatement(REQ_FBP_INSERT_SMS);
			runSqlQuery.updateData(st , parameters);

			Utils.closeResulSet(con, st);
			
			//Si absence d'exception
			data.put("smsResponse", "1");
			data.put("idSms", id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			data.put("smsResponse", "0");
//			data.put("idSms", 0);
			e.printStackTrace();
		}
		
				
		return data;
	}
	
    
	
}
