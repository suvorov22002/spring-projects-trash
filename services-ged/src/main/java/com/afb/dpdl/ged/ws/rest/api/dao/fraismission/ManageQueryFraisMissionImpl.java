package com.afb.dpdl.ged.ws.rest.api.dao.fraismission;

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


public class ManageQueryFraisMissionImpl implements IManageQueryFraisMission{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_FRAIS_GRADE = "SELECT frais_territoire, frais_afrique, frais_monde FROM mis_grade where libelle = ? and activer = 'true'";
	
	private final String REQ_FBP_FRAIS_FCT = "SELECT frais_territoire, frais_afrique, frais_monde FROM mis_fonction where libelle = ? and activer = 'true'";
	
	private final String REQ_FBP_AUTO_FRAIS_TRANS = "SELECT autoriser_frais FROM mis_moyentransport WHERE libelle = ? ";
	
	private final String REQ_FBP_TRANS_GRADE = "SELECT f.frais from core_town dep, core_town arr, mis_trajet trj, mis_cate_trajet f, mis_grade gr WHERE trj.source_id = dep.id AND trj.desti_id = arr.id AND ((dep.name = ? AND arr.name = ?) OR (dep.name = ? AND arr.name = ?)) AND f.trajet_id = trj.id AND trj.activer = 'true' AND f.categorie_id = gr.categorie_id AND gr.libelle = ? AND gr.activer = 'true';";
	
	private final String REQ_FBP_TRANS_FCT = "SELECT f.frais from core_town dep, core_town arr, mis_trajet trj, mis_cate_trajet f, mis_fonction fc WHERE trj.source_id = dep.id AND trj.desti_id = arr.id AND ((dep.name = ? AND arr.name = ?) OR (dep.name = ? AND arr.name = ?)) AND f.trajet_id = trj.id AND trj.activer = 'true' AND f.categorie_id = fc.categorie_id AND fc.libelle = ? AND fc.activer = 'true';";
		
		
	@Override
	public Map<String, Object> getFraisMission(String lieu, String nbrJours, String grade, String fonction, String moyenTrans, String villedepart,
			String villedestinataire) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		ResultSet rs;
		boolean fraisTransAutorise = false;
		int transGrade = 0, transFonction = 0, missGrade = 0, missFonction = 0;
		Object[] parameters = new Object[] {grade};
		
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_FBP_FRAIS_GRADE);
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) {
			if("Cameroun".equalsIgnoreCase(lieu))
				missGrade = rs.getInt("frais_territoire");

			else if("Afrique".equalsIgnoreCase(lieu))
				missGrade = rs.getInt("frais_afrique");

			else if("Etranger".equalsIgnoreCase(lieu))
				missGrade = rs.getInt("frais_monde");
		}
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {fonction};

		st = con.prepareStatement(REQ_FBP_FRAIS_FCT);
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) {
			if("Cameroun".equalsIgnoreCase(lieu))
				missFonction = rs.getInt("frais_territoire");

			else if("Afrique".equalsIgnoreCase(lieu))
				missFonction = rs.getInt("frais_afrique");

			else if("Etranger".equalsIgnoreCase(lieu))
				missFonction = rs.getInt("frais_monde");
		}
		data.put("missionPropose", missGrade > missFonction ? missGrade+" * "+nbrJours+" = "+new DecimalFormat("#,##0").format(missGrade * Double.parseDouble(nbrJours)) : missFonction+" * "+nbrJours+" = "+new DecimalFormat("#,##0").format(missFonction * Double.parseDouble(nbrJours)));
		data.put("missionReel", missGrade > missFonction ? (int)(missGrade * Double.parseDouble(nbrJours)) : (int)(missFonction * Double.parseDouble(nbrJours)));
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {moyenTrans};
		st = con.prepareStatement(REQ_FBP_AUTO_FRAIS_TRANS);
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) {
			fraisTransAutorise  = rs.getBoolean("autoriser_frais");
		}
		Utils.closeResulSet(null, st);
		
		if(fraisTransAutorise){

			parameters = new Object[] {villedepart, villedestinataire, villedestinataire, villedepart, grade};
			st = con.prepareStatement(REQ_FBP_TRANS_GRADE);
			rs = runSqlQuery.selectData(st , parameters);
			if(rs != null && rs.next()) {
				transGrade = rs.getInt("frais");
			}
			Utils.closeResulSet(null, st);
			
			parameters = new Object[] {villedepart, villedestinataire, villedestinataire, villedepart, fonction};
			st = con.prepareStatement(REQ_FBP_TRANS_FCT);
			rs = runSqlQuery.selectData(st, parameters);
			if(rs != null && rs.next()) {
				transFonction = rs.getInt("frais");
			}
			Utils.closeResulSet(null, st);
			
			data.put("transportPropose", transGrade > transFonction ? new DecimalFormat("#,##0").format(transGrade) : new DecimalFormat("#,##0").format(transFonction));
			data.put("transportReel", transGrade > transFonction ? transGrade : transFonction);
		}
		else{
			data.put("transportPropose", 0);
			data.put("transportReel", 0);
		}
		
		Utils.closeResulSet(con, null);
		
		
		return data;
	}
	
    
	
}
