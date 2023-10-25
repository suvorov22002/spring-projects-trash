package com.afb.dpdl.ged.ws.rest.api.dao.insertmission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;
import com.afb.dpdl.ged.ws.rest.api.service.bank.ServiceRestBank;


public class ManageQueryInsertMissionPortalImpl implements IManageQueryInsertMissionPortal{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_FBP_INSERT_MISSION = "insert into afbportal.mis_transaction(refdos,nomempl,foncempl,gradeemploye,uniteempl,ncpempl,nbjrmiss,moytrans,unitbudg,vildep,paysmiss,vilmiss,codeope,frstrans,frsmiss,ncpfrsmiss,ncpfrstrans,libmiss,cutianalcomp,cutidcompt,datec,datem,dco,cutic,dexe,statut,libtrans, libchargmiss,libchargtrans)"
				     + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	

	Logger logger = Logger.getLogger(ManageQueryInsertMissionPortalImpl.class.getName());


	@Override
	public Map<String, Object> insertMissionPortal(String nom, String ref, String fonction, String grade, String cellule, String ncp,
		int nbrJours, String moyenTrans, String unitebudg, String villeDepart, String villeDestinataire, String paysAfricain, String paysEtranger,
		String codeOpe, double transportReel, double missionReel, String ncpTransport, String ncpMission, String cutiAnalComp, String cutiDcompt) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		String pays = "";
		
		if (paysAfricain==null || paysAfricain.trim().isEmpty()) {
			pays=paysEtranger;
		}else {
			pays=paysAfricain;
		}
		
		try {
			Object[] parameters = new Object[] {
					ref,
					nom,
					fonction,
					grade,
					cellule,
					ncp,
					nbrJours, 
					moyenTrans,
					unitebudg,
					villeDepart,
					pays,
					villeDestinataire,
					codeOpe,
					transportReel, 
					 missionReel, 
					ncpMission,
					ncpTransport,
					"V FRAIS MISS "+ (pays == null || pays.trim().isEmpty() ? villeDestinataire.toUpperCase().substring(0, 3) : (pays.length()<=6?pays.toUpperCase():pays.toUpperCase().substring(0,6))) + " "+ nbrJours +"J",
					cutiAnalComp,
					cutiDcompt,
					new java.sql.Date(new Date().getTime()),
					null,
					null,
					null,
					null,
					false,
					transportReel != 0 ? "V FRAIS TRANS "+ villeDestinataire.toUpperCase().substring(0, 3) + " "+ nbrJours +"J" : "",
					"RGLT MISS "+ (nom.length()<=8? nom.toUpperCase():nom.toUpperCase().substring(0,8))+ " " + (pays == null || pays.trim().isEmpty() ? villeDestinataire.toUpperCase().substring(0, 3) : (pays.length()<=6?pays.toUpperCase():pays.toUpperCase().substring(0,6))) + " "+ nbrJours +"J",
					transportReel != 0 ? "RGLT FRS DPLT "+ (nom.length()<=8? nom.toUpperCase():nom.toUpperCase().substring(0,8)) + " " +villeDestinataire.toUpperCase().substring(0, 3)+ " "+ nbrJours +"J" : ""
			};
			Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
			PreparedStatement st = con.prepareStatement(REQ_FBP_INSERT_MISSION);
			runSqlQuery.updateData(st , parameters);
			Utils.closeResulSet(con, st);
		
			//Si absence d'exception
			data.put("insertPortalOK", true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			data.put("insertPortalOK", false);
			e.printStackTrace();
		}
		
		return data;
	}
	
//	private final String REQ_FBP_INSERT_MISSION = "insert into afbportal.mis_transaction(refdos,nomempl,foncempl,gradeemploye,uniteempl,ncpempl,nbjrmiss,moytrans,unitbudg,vildep,paysmiss,vilmiss,codeope,frstrans,frsmiss,ncpfrsmiss,ncpfrstrans,libmiss,cutianalcomp,cutidcompt,datec,datem,dco,cutic,dexe,statut,libtrans) "
//				     + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//	
//	
//	@Override
//	public Map<String, Object> insertMissionPortal(String nom, String ref, String fonction, String grade, String cellule, String ncp,
//			int nbrJours, String moyenTrans, String unitebudg, String villeDepart, String villeDestinataire, String pays,
//			String codeOpe, double transportReel, double missionReel, String ncpTransport, String ncpMission, String cutiAnalComp, String cutiDcompt) throws ClassNotFoundException {
//		// TODO Auto-generated method stub
//		// Initialisation de la Map a retourner
//		Map<String, Object> data = new HashMap<String, Object>();
//
//		try {
//			Object[] parameters = new Object[] {
//					ref,
//					nom,
//					fonction,
//					grade,
//					cellule,
//					ncp,
//					nbrJours, 
//					moyenTrans,
//					unitebudg,
//					villeDepart,
//					pays,
//					villeDestinataire,
//					codeOpe,
//					transportReel, 
//					 missionReel, 
//					ncpMission,
//					ncpTransport,
//					"V FRS MISS "+ (pays == null || pays.trim().isEmpty() ? villeDestinataire : pays) + " "+ nbrJours +"J",
//					cutiAnalComp,
//					cutiDcompt,
//					new java.sql.Date(new Date().getTime()),
//					null,
//					null,
//					null,
//					null,
//					false,
//					transportReel != 0 ? "V FRS TRANS "+ villeDestinataire + " "+ nbrJours +"J" : ""
//			};
//		
//			runSqlQuery.updateData(IConnectionFactory.PORTAL_DB, REQ_FBP_INSERT_MISSION, parameters);
//	
//			//Si absence d'exception
//			data.put("insertPortalOK", true);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			data.put("insertPortalOK", false);
//			e.printStackTrace();
//		}
//		
//		return data;
//	}
	
    
	
}
