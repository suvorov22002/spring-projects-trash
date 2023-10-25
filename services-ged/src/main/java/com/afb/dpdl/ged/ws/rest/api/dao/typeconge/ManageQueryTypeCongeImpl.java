package com.afb.dpdl.ged.ws.rest.api.dao.typeconge;

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


public class ManageQueryTypeCongeImpl implements IManageQueryTypeConge{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_ALTGRH_TYPE_CONGE = "select codeconge, nomconge from conge";
	
	
	@Override
	public Map<String, Object> getListeTypeConge() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
		PreparedStatement st = con.prepareStatement(REQ_ALTGRH_TYPE_CONGE);
		ResultSet rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
//			data.put(rs.getString("codeconge"), rs.getString("nomconge"));
			data.put(rs.getString("nomconge"), rs.getString("nomconge"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
    
	
}
