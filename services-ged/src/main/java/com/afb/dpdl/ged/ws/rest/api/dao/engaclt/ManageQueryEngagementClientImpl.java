package com.afb.dpdl.ged.ws.rest.api.dao.engaclt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryEngagementClientImpl implements IManageQueryEngagementClient{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_ENCOURS = "select sum(sde) as encours from bkcom where cli=? and ( (cha[1,2] in ('30', '31', '32', '54', '52') and cha[1,3] not in ('308', '318', '328', '324', '321')) or (cha[1,2] in ('54', '58') and sde<0) or (cha[1,3]='561' and sde<0) or cha='267200' )";
	private final String REQ_CBS_MESUALITE_IMPAYES = "select sum(map) as totalmap from bkdosprt where eta='VA' AND CTR='1' and cli = ?";
	private final String REQ_CBS_AGIOS = "select sum(sde) as agios from bkcom where cli=? and cha[1,3]='985'";
	private final String REQ_CBS_IMPAYES = "select sum(sde) as impayes from bkcom where cli=? and (cha[1,2] = '34' or (cha[1,3] = '561' and sde<0) or (cha[1,2] in ('54', '58') and sde<0) )";
	private final String REQ_CBS_LAST_DMEP = "select dmep from bkdosprt where cli=? and eta='VA' order by dmep desc";
	private final String REQ_CBS_SALAIRE = "select * from tbsalaire where age = ? and ncp = ?";
	private final String REQ_CBS_SOLDE = "select sde, dou from bkcom where age=? and ncp=? and clc=?";
	
	
	@Override
	public Map<String, Object> getEngagementClient(String matricule, String ncp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		// Execution de la requete
		ResultSet rs = null;
		
		DecimalFormat df = new DecimalFormat("###0");

		Object[] parameters = new Object[] {matricule};
		
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_ENCOURS);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("encours", df.format(rs.getDouble("encours")));
		Utils.closeResulSet(null, st);

		st = con.prepareStatement(REQ_CBS_MESUALITE_IMPAYES);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("totMimp", df.format(rs.getDouble("totalmap"))); 
		Utils.closeResulSet(null, st);

		st = con.prepareStatement(REQ_CBS_AGIOS);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("agios", df.format(rs.getDouble("agios")));
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_IMPAYES);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("impayes", df.format(rs.getDouble("impayes")));
		Utils.closeResulSet(null, st);

		if(ncp != null && !ncp.trim().isEmpty()){
			parameters = new Object[] {ncp.split("-")[0], ncp.split("-")[1], ncp.split("-")[2]};
			st = con.prepareStatement(REQ_CBS_SOLDE);
			con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
			rs = runSqlQuery.selectData(st, parameters);
			if(rs != null && rs.next()) {
				data.put("solde", df.format(rs.getDouble("sde")));
				data.put("dou", rs.getDate("dou"));
			}
			Utils.closeResulSet(null, st);
		}

		parameters = new Object[] {matricule};
		st = con.prepareStatement(REQ_CBS_LAST_DMEP);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);;
		if(rs != null && rs.next()) data.put("lastDmep", rs.getDate("dmep"));
		Utils.closeResulSet(null, st);

		if(ncp != null){
			parameters = new Object[] {ncp.split("-")[0], ncp.split("-")[1]};
			st = con.prepareStatement(REQ_CBS_SALAIRE);
			rs = runSqlQuery.selectData(st , parameters);
			if(rs != null && rs.next()) {
				data.put("salm1", df.format(rs.getDouble("m1"))); 
				data.put("salm2", df.format(rs.getDouble("m2")));
				data.put("salm3", df.format(rs.getDouble("m3")));
				data.put("mvtc", df.format(rs.getDouble("mvtc")));
				data.put("nbSal", rs.getDouble("nbre"));
			}
			Utils.closeResulSet(null, st);
		}

		Utils.closeResulSet(con, null);
		
		return data;
		
	}
	
	
}
