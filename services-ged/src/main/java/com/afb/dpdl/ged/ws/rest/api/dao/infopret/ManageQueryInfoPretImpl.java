package com.afb.dpdl.ged.ws.rest.api.dao.infopret;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInfoPretImpl implements IManageQueryInfoPret{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private static DecimalFormat decimalFormat;
	
	private final String REQ_CBS_INFO_PRET = "select * from bkdosprt where age=? and eve=? and ave=? order by dou desc";
	private final String REQ_CBS_GARANTIE = "select ngar from bkeng where neng=? and ncp=? and eta in ('VA', 'VF', 'FO') and sit in ('O', 'M')";
		
	
	@Override
	public Map<String, Object> getInfoPret(String numPret, String ncp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		if((numPret == null || ncp == null) || (numPret.isEmpty() || ncp.isEmpty())) return data;
		
		// Execution de la requete
		ResultSet rs = null;
		DecimalFormat df = getAmountFormat();
		

		Object[] parameters = new Object[] {ncp.split("-")[0], numPret.split("-0")[0], numPret.split("-0")[1]};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_INFO_PRET);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) {
			data.put("montant", df.format(rs.getDouble("mon")));
			data.put("taux", rs.getDouble("tau_int"));
			data.put("duree", rs.getInt("tech"));
			data.put("mensualite", df.format(rs.getDouble("map")));
			data.put("frais", df.format(rs.getDouble("mon_fra")));
			data.put("misenplace", rs.getString("eta").equals("VA") || rs.getString("eta").equals("VF") || rs.getString("eta").equals("FO") ? "1" : "0" );
		}
		else {
			data.put("montant", "0");
			data.put("taux", "0");
			data.put("duree", "0");
			data.put("mensualite", "0");
			data.put("frais", "0");
			data.put("misenplace", "1" );
		}
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {numPret.replaceFirst("-", ""), ncp.split("-")[1]};
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		st = con.prepareStatement(REQ_CBS_GARANTIE);
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("ngar", rs.getString("ngar")); 
		Utils.closeResulSet(con, st);
				
		return data;
		
	}
	
	private DecimalFormat getAmountFormat(){
		if(decimalFormat == null)
			return new DecimalFormat("#,##0");
		return decimalFormat;
	}
	
	/*private DecimalFormat getAmountFormat(){

		Locale locale = null;
		Locale[] locs = DecimalFormat.getAvailableLocales();
		for(int i=0; i<locs.length; i++){
			if(locs[i].getLanguage().toUpperCase().equals("FR")) {
				locale = locs[i]; break;
			}
		}
		DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
		return df;
	}*/
	
}
