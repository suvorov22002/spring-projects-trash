package com.afb.dpdl.ged.ws.rest.api.dao.townlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryListeVilleImpl implements IManageQueryListeVille{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_LIST_VILLE = "select code,name from core_town order by name";
	
	
	@Override
	public List<String> getListeVille() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		List<String> data = new ArrayList<>();
		
		ResultSet rs = null;
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_LIST_VILLE);
		rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("name"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
		
	}

	
}
