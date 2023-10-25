package com.afb.dpdl.ged.ws.rest.api.dao.listedev;

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


public class ManageQueryListeDeviseImpl implements IManageQueryListeDevive{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_LIST_DEV = "select trim(cacc)||' - '||trim(lib1) as devise from bknom where ctab = '002' order by lib1";
	
	
	@Override
	public List<String> getListeDevise() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		List<String> data = new ArrayList<>();
		
		ResultSet rs = null;
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_LIST_DEV);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("devise"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
		
	}

	
}
