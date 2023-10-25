package com.afb.dpdl.ged.ws.rest.api.dao.moyentransport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryMoyenTransportImpl implements IManageQueryMoyenTransport{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_MOYEN_TRANSPORT = "SELECT CODE,LIBELLE From MIS_MOYENTRANSPORT";
	
		
	@Override
	public List<String> getMoyenTransport()  throws ClassNotFoundException, SQLException{
		// Initialisation de la Map a retourner
		List<String> data = new ArrayList<>();

		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_MOYEN_TRANSPORT);
		ResultSet rs = runSqlQuery.selectData(st, null);
		while(rs != null && rs.next()) {
			data.add(rs.getString("LIBELLE"));
		}
		Utils.closeResulSet(con, st);
				
		return data;
	}
	
    
	
}
