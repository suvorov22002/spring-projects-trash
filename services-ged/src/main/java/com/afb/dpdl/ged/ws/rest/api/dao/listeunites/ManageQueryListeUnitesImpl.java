package com.afb.dpdl.ged.ws.rest.api.dao.listeunites;

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


public class ManageQueryListeUnitesImpl implements IManageQueryListeUnites{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_LIST_UNIT = "select distinct designation from affectation order by designation";
	
		
	@Override
	public Map<String, Object> getListeUnites()  throws ClassNotFoundException, SQLException{
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();

		Connection con = runSqlQuery.getConnection(IConnectionFactory.ANNUAIRE_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_LIST_UNIT);
		ResultSet rs = runSqlQuery.selectData(st, null);
		while(rs != null && rs.next()) {
			data.put(rs.getString("designation"), rs.getString("designation"));
		}
		Utils.closeResulSet(con,st);
				
		return data;
	}
	
    
	
}
