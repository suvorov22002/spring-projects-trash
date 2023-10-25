package com.afb.dpdl.ged.ws.rest.api.dao.refqual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryReferantQualiteImpl implements IManageQueryReferantQualite{
	
	@Inject
	private IRunSqlQuery runSqlQuery;

	private final String REQ_FBP_ID_AGE_USER = "SELECT BRANCH_CODES FROM REC_BRANCH_CODES where BRANCH_CODES like ?"; // codeAgeUsr + %
	private final String REQ_FBP_ID_CIBLE = "select cible_id from rec_transfer where source_id=? and actif=true"; // id
	private final String REQ_FBP_CUTI_GFC_USER = "select login from core_user where id=?"; // id
	
	@Override
	public Map<String, Object> getReferantQualite(String codeAgeUser) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		ResultSet rs;
		
		Object[] parameters = {codeAgeUser + "%"};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_ID_AGE_USER);
		rs = runSqlQuery.selectData(st , parameters);
		String id = rs != null && rs.next() ? rs.getString("BRANCH_CODES") : "";
		Utils.closeResulSet(null, st);
		id = id.split("-")[2];
		
		parameters = new Object[] {Long.valueOf(id)};
		st = con.prepareStatement(REQ_FBP_ID_CIBLE);
		rs = runSqlQuery.selectData(st , parameters);
		id = rs != null && rs.next() ? rs.getString("cible_id") : id;
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {Long.valueOf(id)};
		st = con.prepareStatement(REQ_FBP_CUTI_GFC_USER);
		rs = runSqlQuery.selectData(st , parameters);
		
		data.put("codeUserRQ", rs != null && rs.next() ? rs.getString("login").trim() : null);
		
		Utils.closeResulSet(con, st);
		return data;
	}
	
	
}
