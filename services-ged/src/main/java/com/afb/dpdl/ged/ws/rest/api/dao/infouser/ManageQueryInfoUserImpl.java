package com.afb.dpdl.ged.ws.rest.api.dao.infouser;

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


public class ManageQueryInfoUserImpl implements IManageQueryInfoUser{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_INFO_USER = "SELECT u.LOGIN, u.NAME, u.PHONE, u.EMAIL, b.Code, b.name as agence From CORE_USER u left join core_branch b on u.branch_id = b.id where login = ?";
	
	
	@Override
	public Map<String, Object> getInfoUser(String login) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		ResultSet rs;
		
		Object[] parameters = {login};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_INFO_USER);
		rs = runSqlQuery.selectData(st, parameters);
		
		// Parcours du resultat et chargement de la Map
		if(rs != null && rs.next()) {
			data.put("login", rs.getString("login"));
			data.put("userName", rs.getString("name"));
			data.put("userPhone", rs.getString("phone"));
			data.put("userEmail", rs.getString("email"));
			data.put("codeAgeUsr", rs.getString("code"));
			data.put("libAgeUsr", rs.getString("agence"));
		}
		Utils.closeResulSet(con, st);
		return data;
	}
	
	
}
