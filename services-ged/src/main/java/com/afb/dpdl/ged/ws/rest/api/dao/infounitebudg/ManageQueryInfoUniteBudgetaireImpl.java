package com.afb.dpdl.ged.ws.rest.api.dao.infounitebudg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInfoUniteBudgetaireImpl implements IManageQueryInfoUniteBudgetaire{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	//Requ�te permettant de r�cup�rer � partir de portal le param�trage sur les diff�rents comptes
	private final String REQ_FBP_PARAM_CPT = "SELECT ncp_deplperso, ncp_missetranperso, ncp_misscamperso, code_ope FROM mis_prmtrs;";
	//Requ�te permettant de r�cup�rer � partir de Portal le code op�ration particulier pour une unit� bugetrice
	private final String REQ_FBP_CODE_OPE = "SELECT code FROM mis_codeope WHERE libelle = ?";
	
	private final String REQ_CBS_CLC = "SELECT clc FROM bkcom WHERE age = ? AND ncp = ? AND dev = '001'";
	
	@Override
	public Map<String, Object> getInfoUniteBudgetaire(String unitebudg, String lieu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		ResultSet rs = null;
		String ncpMiss = null; //Num�ro de compte � d�biter pour les frais de mission
		String ncpTrans = null; //Num�ro de compte � d�biter pour les frais de transport
		String ageNcp = "00006";//Agence des comptes
		String clcMiss = null;//Cl� du compte � d�biter pour les frais de mission
		String clcTrans = null;//Cl� du compte � d�biter pour les frais de transport
		String codeOpe = "950";//Code op�ration � utiliser pour les �critures comptables
				
		if(unitebudg != null && !unitebudg.isEmpty()){
    		String[] tab = unitebudg.split("-");
    		if(tab.length == 2){
    			ageNcp = tab[0].trim();
    		}
    	}

		Object[] parameters = new Object[] {};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_PARAM_CPT);
		rs = runSqlQuery.selectData(st , null);
		if(rs != null && rs.next()) {
			if("Cameroun".equalsIgnoreCase(lieu)){
				ncpMiss = rs.getString("ncp_misscamperso");
				ncpTrans = rs.getString("ncp_deplperso");
			}

			else {
				ncpMiss = rs.getString("ncp_missetranperso");
			}
			
			if(rs.getString("code_ope") != null && !rs.getString("code_ope").trim().isEmpty())
				codeOpe =  rs.getString("code_ope");
			Utils.closeResulSet(con, st);
			con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
			if(ncpMiss != null){
				
				parameters = new Object[] {ageNcp, ncpMiss};
				st = con.prepareStatement(REQ_CBS_CLC);
				rs = runSqlQuery.selectData(st, parameters);
				if(rs != null && rs.next()) clcMiss = rs.getString("clc");
				Utils.closeResulSet(null, st);
				
			}
			if(ncpTrans != null){
				
				parameters = new Object[] {ageNcp, ncpTrans};
				st = con.prepareStatement(REQ_CBS_CLC);
				rs = runSqlQuery.selectData(st, parameters);
				if(rs != null && rs.next()) clcTrans = rs.getString("clc");
				Utils.closeResulSet(null,st);
				
			}
		}
		Utils.closeResulSet(con, st);
		
		parameters = new Object[] {unitebudg};
		con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		st = con.prepareStatement(REQ_FBP_CODE_OPE);
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) {
			if(rs.getString("code") != null && !rs.getString("code").trim().isEmpty())
				codeOpe =  rs.getString("code");
		}
		Utils.closeResulSet(con, st);

		data.put("ncpMission", ageNcp+'-'+ncpMiss+'-'+clcMiss);
		data.put("ncpTransport", ageNcp+'-'+ncpTrans+'-'+clcTrans);
		data.put("codeOpe", codeOpe);
		
		return data;
		
	}

	
}
