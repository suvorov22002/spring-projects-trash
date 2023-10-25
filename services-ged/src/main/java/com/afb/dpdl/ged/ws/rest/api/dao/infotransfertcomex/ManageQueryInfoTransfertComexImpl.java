package com.afb.dpdl.ged.ws.rest.api.dao.infotransfertcomex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.text.DateFormatter;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInfoTransfertComexImpl implements IManageQueryInfoTransfertComex{
	
	public static final String[] LISTE_NATURE_OPERATIION = {};
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
//	private final String REQ_CBS_REFDOS = "SELECT eta, bkdopi.nombf AS beneficiaire, t_paysdp.lib1 AS paysdp, bkdopi.mdev AS montantDev, bkdopi.tdev AS tauxDevise, bkdopi.mnet AS montantXaf, t_devise.lib1 AS libelleDevise, t_devise.lib2 AS devise, bkdopi.dva AS dateOperation, bkdopi.ndos AS refTrans "+
//			"FROM bkdopi, bknom AS t_paysdp, bknom AS t_devise "+
//			"WHERE refint = ? "+
//			"AND bkdopi.paysdp = t_paysdp.cacc AND t_paysdp.ctab='040' "+ 
//			"AND bkdopi.dev = t_devise.cacc AND t_devise.ctab = '005'";
//
//	private final String REQ_CBS_REFTRANS = "SELECT eta, bkdopi.nombf AS beneficiaire, t_paysdp.lib1 AS paysdp, bkdopi.mdev AS montantDev, bkdopi.tdev AS tauxDevise, bkdopi.mnet AS montantXaf, t_devise.lib1 AS libelleDevise, t_devise.lib2 AS devise, bkdopi.dva AS dateOperation, bkdopi.ndos AS refTrans "+
//	"FROM bkdopi, bknom AS t_paysdp, bknom AS t_devise "+
//	"WHERE ndos = ? "+
//	"AND bkdopi.paysdp = t_paysdp.cacc AND t_paysdp.ctab='040' "+ 
//	"AND bkdopi.dev = t_devise.cacc AND t_devise.ctab = '005'";
	
//	private final String REQ_CBS_INFOTRF = "SELECT eta, bkdopi.nombf AS beneficiaire, t_paysdp.lib1 AS paysdp, bkdopi.mdev AS montantDev, "+
//			"bkdopi.tdev AS tauxDevise, bkdopi.mnet AS montantXaf, t_devise.lib1 AS libelleDevise, t_devise.lib2 AS devise, bkdopi.dva AS dateOperation, "+
//			"bkdopi.ndos AS refTrans, bkdopi.ncpdo AS NumCptDo, bkdopi.dou AS dateDemand, bkdopi.refint AS dateApure, bkdopi.age AS codeAgeSaisie, "+
//			"bkdopi.nomdo AS nomDordre, bkdopi.uti AS cuti FROM bkdopi, bknom AS t_paysdp, bknom AS t_devise WHERE bkdopi.ndos = ? "+
//			"AND bkdopi.paysdp = t_paysdp.cacc AND t_paysdp.ctab='040' AND bkdopi.dev = t_devise.cacc AND t_devise.ctab = '005'";
	
	private final String REQ_CBS_INFOTRF = "SELECT eta, bkdopi.nombf AS beneficiaire, t_paysdp.lib1 AS paysdp, bkdopi.mdev AS montantDev, "+
			"bkdopi.tdev AS tauxDevise, bkdopi.mnet AS montantXaf, t_devise.lib1 AS libelleDevise, t_devise.lib2 AS devise, bkdopi.dexec AS dateOperation, "+
			"bkdopi.ndos AS refTrans, bkdopi.ncpdo AS NumCptDo, bkdopi.dou AS dateDemand, bkdopi.refint AS dateApure, bkdopi.age AS codeAgeSaisie, "+
			"bkdopi.nomdo AS nomDordre, bkdopi.uti AS cuti, bkcom.age || '-' || bkcom.ncp || '-'|| bkcom.clc as numCompte "+
			"FROM bkdopi, bknom AS t_paysdp, bknom AS t_devise, bkcom "+
			"WHERE bkdopi.ndos = ? AND bkdopi.ncpdo = bkcom.ncp AND bkdopi.age = bkcom.age AND bkdopi.paysdp = t_paysdp.cacc AND t_paysdp.ctab='040' "+
			"AND bkdopi.dev = t_devise.cacc AND t_devise.ctab = '005'";
	
	
	@Override
	public Map<String, Object> getInfoTransfertComex(String reftrans) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		// jj/MM/yyyy
		
		ResultSet rs = null;
		
		data.put("eta", "KO");
		Object[] parameters = new Object[] {reftrans};

		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_INFOTRF);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) {
			data.put("beneficiaire", rs.getString("beneficiaire").trim());
			data.put("pays", rs.getString("paysdp").trim());
			data.put("montantXaf", new DecimalFormat("#,##0").format(rs.getDouble("montantXaf")) );
			data.put("montantXaf2", rs.getDouble("montantXaf") );
			data.put("montantDev", rs.getDouble("montantDev") );
			data.put("devise", rs.getString("libelleDevise").trim());
			data.put("codeDevise", rs.getString("devise").trim());
			data.put("tauxDevise", rs.getDouble("tauxDevise") );
			data.put("dateOpe", rs.getDate("dateOperation"));
			data.put("refTrans", rs.getString("reftrans").trim());
			data.put("eta", "VA".equals(rs.getString("eta").trim()) || "FO".equals(rs.getString("eta").trim()) ? "OK" : rs.getString("eta").trim());
			data.put("NumCptDo", rs.getString("numCompte").trim());
			data.put("dateApure", rs.getString("dateApure") ); // Convert
			data.put("codeAgeSaisie", rs.getString("codeAgeSaisie") );
			data.put("nomDordre", rs.getString("nomDordre"));
			data.put("cuti", rs.getString("cuti").trim());
			data.put("dateDemand", rs.getDate("dateDemand"));
		}
		Utils.closeResulSet(con, st);
//		Object[] parameters = new Object[] {refDos};
//		rs = runSqlQuery.selectData(IConnectionFactory.CBS_DB, REQ_CBS_REFDOS, parameters);
//		if(rs != null && rs.next()) {
//			data.put("beneficiaire", rs.getString("beneficiaire").trim());
//			data.put("pays", rs.getString("paysdp").trim());
//			data.put("montantXaf", new DecimalFormat("#,##0").format(rs.getDouble("montantXaf")) );
//			data.put("montantXaf2", rs.getDouble("montantXaf") );
//			data.put("dateOpe", rs.getDate("dateOperation"));
//			data.put("refTrans", rs.getString("reftrans").trim());
//			data.put("eta", "VA".equals(rs.getString("eta").trim()) || "FO".equals(rs.getString("eta").trim()) ? "OK" : rs.getString("eta").trim());
//			Utils.closeResulSet(rs);
//		}
//		else {
//			Utils.closeResulSet(rs);
//			
//			parameters = new Object[] {reftrans};
//			rs = runSqlQuery.selectData(IConnectionFactory.CBS_DB, REQ_CBS_REFTRANS, parameters);
//			if(rs != null && rs.next()) {
//				data.put("beneficiaire", rs.getString("beneficiaire").trim());
//				data.put("pays", rs.getString("paysdp").trim());
//				data.put("montantXaf", new DecimalFormat("#,##0").format(rs.getDouble("montantXaf")));
//				data.put("montantXaf2", rs.getDouble("montantXaf") );
//				data.put("dateOpe", rs.getDate("dateOperation"));
//				data.put("refTrans", rs.getString("reftrans").trim());
//				data.put("eta", "VA".equals(rs.getString("eta").trim()) || "FO".equals(rs.getString("eta").trim()) ? "OK" : rs.getString("eta").trim());
//			}
//			Utils.closeResulSet(rs);
//		};
		
		
		return data;
		
	}

	
}
