package com.afb.dpdl.ged.ws.rest.api.dao.affauto;

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


public class ManageQueryAffAutoImpl implements IManageQueryAffAuto{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_ENCOURS_CLI = "select sum(sde) as encours from bkcom where cli=? and ( (cha[1,2] in ('30', '31', '32', '54', '52') and cha[1,3] not in ('308', '318', '328', '324', '321')) or (cha[1,2] in ('54', '58') and sde<0) or (cha[1,3]='561' and sde<0) or cha='267200' )";
	
	private final String REQ_FBP_USERS_AFFAUTO_BTP = "select distinct login from core_user where id in ( SELECT USER_ID FROM GED_AFFECAUTO where (typeclient =? or typeclient is null) and phase=? and ( (mnt_min<=? and mnt_max>=?) or (mnt_min + mnt_max = 0) ) and ( secteur='BTP' or (typeclient is null and secteur is null) ) and (BRANCH_ID is null or BRANCH_ID in (select parent_id from core_branch where code=?)) )";
	
	private final String REQ_FBP_USERS_AFFAUTO = "select distinct login from core_user where id in ( SELECT USER_ID FROM GED_AFFECAUTO where (typeclient =? or typeclient is null) and phase=? and ( (mnt_min<=? and mnt_max>=?) or (mnt_min + mnt_max = 0) ) and ( secteur is null or secteur<>'BTP' ) and (BRANCH_ID is null or BRANCH_ID in (select parent_id from core_branch where code=?)) )";
	
	
	@SuppressWarnings("resource")
	@Override
	public Map<String, Object> getUsersAffAuto(String matricule, String typeClient, String codeAgeSaisie, Double montant,
			String secteurActivite, String currentPhase) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		String usernames="";
		
		if(codeAgeSaisie == null || codeAgeSaisie.isEmpty())
			codeAgeSaisie = "00001";
		Object[] parameters = {matricule};


		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_ENCOURS_CLI);
		
		ResultSet rs = runSqlQuery.selectData(st , parameters);
		
		if(rs != null && rs.next()) {
			montant += Math.abs(rs.getDouble("encours")) ;
		}
		Utils.closeResulSet(con, st);
		
		if(secteurActivite != null && secteurActivite.equalsIgnoreCase("BTP")){
			parameters = new Object[] {typeClient.toUpperCase().trim(), currentPhase.toUpperCase(), montant.longValue(), montant.longValue(), codeAgeSaisie};
			
			con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
			st = con.prepareStatement(REQ_FBP_USERS_AFFAUTO_BTP);
			rs = runSqlQuery.selectData(st , parameters);
		}
		else {
			parameters = new Object[] {typeClient.toUpperCase().trim(), currentPhase.toUpperCase(), montant.longValue(), montant.longValue(), codeAgeSaisie};
			con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
			st = con.prepareStatement(REQ_FBP_USERS_AFFAUTO);
			rs = runSqlQuery.selectData(st , parameters);
		}
		while(rs != null && rs.next()) {
			usernames += rs.getString("login") + ";";
		}
		data.put("usernames", usernames);
		Utils.closeResulSet(con, st);
				
		return data;
	}
	
    
	
}
