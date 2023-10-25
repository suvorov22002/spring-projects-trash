package com.afb.dpdl.ged.ws.rest.api.dao.numpret;

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


public class ManageQueryNumeroPretImpl implements IManageQueryNumeroPret{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_NUMPRET = "select age, eve, ave, nbe, map, mon_fra, mon, tau_int from bkdosprt where ( (eta='SI' and ctr in ('1', '0')) or (eta='VB' and ctr='1') or (eta='VA' and ctr <>'9')  or (eta='VC' and ctr='1') ) and cli = ? order by dou desc";

		
	@Override
	public List<String> getNumeroPret(String matricule) throws ClassNotFoundException, SQLException {
		List<String> listeCompte = new ArrayList<>();
		Object[] parameters = {matricule};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_NUMPRET);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		ResultSet rs = runSqlQuery.selectData(st, parameters);
		while(rs != null && rs.next()) {
			listeCompte.add(rs.getString("eve") + "-0" + rs.getString("ave"));
		}
		Utils.closeResulSet(con, st);
		return listeCompte;
	}
    
	
}
