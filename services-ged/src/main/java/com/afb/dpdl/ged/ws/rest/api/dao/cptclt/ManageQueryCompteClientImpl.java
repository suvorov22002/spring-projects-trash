package com.afb.dpdl.ged.ws.rest.api.dao.cptclt;

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


public class ManageQueryCompteClientImpl implements IManageQueryCompteClient{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_LISTCOMPTE_CLI = "select age || '-' || ncp || '-' || clc as numcpt, cli, inti, dou, sde from bkcom where cli= ? and ncp[8,10] in ('100', '105', '170', '504')  and cfe='N' and ife='N' order by dou";
	
		
	@Override
	public List<String> getComptesClient(String matricule)  throws ClassNotFoundException, SQLException{
		List<String> listeCompte = new ArrayList<>();
		Object[] parameters = {matricule};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_LISTCOMPTE_CLI);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		ResultSet rs = runSqlQuery.selectData(st, parameters);
		while(rs != null && rs.next()) {
			listeCompte.add(rs.getString("numcpt"));
		}
		Utils.closeResulSet(con, st);
		return listeCompte;
	}
	
    
	
}
