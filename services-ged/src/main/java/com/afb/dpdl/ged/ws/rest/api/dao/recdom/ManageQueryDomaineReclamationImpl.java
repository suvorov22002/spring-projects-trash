package com.afb.dpdl.ged.ws.rest.api.dao.recdom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryDomaineReclamationImpl implements IManageQueryDomaineReclamation{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_DOM_REC = "SELECT id, LIBELLE FROM REC_DOMAIN ORDER BY LIBELLE";
	
	
	@Override
	public List<String> getListeDomaineReclamation() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		List<String> data = new ArrayList<>();
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_DOM_REC);
		ResultSet rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("libelle"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
    
	
}
