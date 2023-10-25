package com.afb.dpdl.ged.ws.rest.api.dao.typedossier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryTypeDossierFinImpl implements IManageQueryTypeDossierFin{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_TYPE_DOS = "SELECT ID, NAME FROM LT_TYPEDOSSIER order by name";
	
	
	@Override
	public Map<String, Object> getListeTypeDossierFin() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_TYPE_DOS);
		ResultSet rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
			data.put(rs.getString("id"), rs.getString("name"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
    
	
}
