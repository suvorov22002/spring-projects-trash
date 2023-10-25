package com.afb.dpdl.ged.ws.rest.api.dao.listeanomalies;

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


public class ManageQueryListeAnomaliesImpl implements IManageQueryListeAnomalies{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_GED_LIST_ANOMALIES = "SELECT libanomalie FROM public.afb_listeanomalies order by libanomalie;";
	
		
	@Override
	public Map<String, Object> getListeAnomalies()  throws ClassNotFoundException, SQLException{
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		Connection con = runSqlQuery.getConnection(IConnectionFactory.GED_DB);
		PreparedStatement st = con.prepareStatement(REQ_GED_LIST_ANOMALIES);
		ResultSet rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
			data.put(rs.getString("libanomalie"), rs.getString("libanomalie"));
		}
		Utils.closeResulSet(con, st);
				
		return data;
	}
	
    
	
}
