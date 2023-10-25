package com.afb.dpdl.ged.ws.rest.api.dao.recnat;

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


public class ManageQueryNatureReclamationImpl implements IManageQueryNatureReclamation{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_NAT_REC = "SELECT n.ID, n.LIBELLE FROM REC_NATURE n left join rec_domain dom on n.domain_id = dom.id where dom.libelle=? order by n.libelle";
	
	
	@Override
	public Map<String, Object> getListeNatureReclamation(String domaine) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		Object[] parameters = {domaine};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_NAT_REC);
		ResultSet rs = runSqlQuery.selectData(st , parameters);
		while(rs != null && rs.next()) data.put(rs.getString("libelle"), rs.getString("libelle") );
		Utils.closeResulSet(con, st);
		System.out.println("DATA : "+data);		
		return data;
	}
	
    
	
}
