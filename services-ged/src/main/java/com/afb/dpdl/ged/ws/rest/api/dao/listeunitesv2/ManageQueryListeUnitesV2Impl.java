package com.afb.dpdl.ged.ws.rest.api.dao.listeunitesv2;

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


public class ManageQueryListeUnitesV2Impl implements IManageQueryListeUnitesV2{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_LIST_DIRECTION = "SELECT LIBELLE From MIS_CODEOPE";
	
	private final String REQ_CBS_LIST_UNIT = "select trim(cacc)||' - '||trim(lib1) as unite from bknom where ctab = '001' order by cacc";
	
		
	@Override
	public List<String> getListeUnitesV2()  throws ClassNotFoundException, SQLException{
		// Initialisation de la Map a retourner
		List<String> data = new ArrayList<>();

		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_LIST_DIRECTION);
		ResultSet rs = runSqlQuery.selectData(st, null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("LIBELLE"));
		}
		Utils.closeResulSet(con, st);
		
		con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		st = con.prepareStatement(REQ_CBS_LIST_UNIT);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("unite"));
		}
		Utils.closeResulSet(con, st);
				
		return data;
	}
	
    
	
}
