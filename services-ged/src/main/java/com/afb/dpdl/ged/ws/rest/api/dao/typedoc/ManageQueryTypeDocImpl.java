package com.afb.dpdl.ged.ws.rest.api.dao.typedoc;

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


public class ManageQueryTypeDocImpl implements IManageQueryTypeDoc{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_TYPE_DOC = "SELECT ID, PIECE FROM LT_TYPEDOC where TYPDOS_ID = ? and upper(TYPE_CLIENT)=? order by piece";
	
	
	@Override
	public Map<String, Object> getListeTypeDoc(String typedossier, String typeclient) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Object[] parameters = {Long.valueOf(typedossier), typeclient.toUpperCase()};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_TYPE_DOC);
		ResultSet rs = runSqlQuery.selectData(st , parameters);
		while(rs != null && rs.next()) {
			data.put(rs.getString("id"), rs.getString("piece"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
    
	
}
