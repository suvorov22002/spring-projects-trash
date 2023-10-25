package com.afb.dpdl.ged.ws.rest.api.dao.docapur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryListeDocApurImpl implements IManageQueryListeDocApur{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_GED_LIST_DOCAPUR = "select docapur,docapurok from afb_dos_comex where refdos = ?";
	
	
	@Override
	public Map<String, Object> getListeDocApur(String refDos) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Object[] parameters = {refDos};
		
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_GED_LIST_DOCAPUR);
		
		ResultSet rs = runSqlQuery.selectData(st , parameters);
		while(rs != null && rs.next()) {
			String[] tab = rs.getString("docapur") != null ? rs.getString("docapur").split(";") : null;
			List<String> listApurOk = new ArrayList<>();
			String[] tab2 = rs.getString("docapurok") != null ? rs.getString("docapurok").split(",") : null;
			if(tab2 != null && tab2.length>0 )
				listApurOk.addAll(Arrays.asList(tab2));
			if (tab != null && tab.length>0 ) 
				for (String str : tab){
					if(str != null && !str.isEmpty() && !listApurOk.contains(str))
						data.put(str, str);
				}
		}
		Utils.closeResulSet(con, st);
				
		return data;
	}
	
    
	
}
