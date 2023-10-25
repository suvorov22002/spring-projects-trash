package com.afb.dpdl.ged.ws.rest.api.dao.infoclt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.dao.engaclt.ManageQueryEngagementClientImpl;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInfoClientImpl implements IManageQueryInfoClient{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_CLI = "select c.cli, c.age, t001.lib1 as nomage, nvl(c.tcli, ' ') as tcli, c.tid, c.nid, c.did, c.catl, t071.lib1 as secteur, c.ges, t035.lib1 as nomges, c.nomrest, nvl(c.dna, c.datc) as dcrea, c.dou, c.viln from bkcli c left join bknom t071 on t071.ctab='071' and c.sec=t071.cacc left join bknom t035 on t035.ctab='035' and c.ges=t035.cacc left join bknom t001 on t001.cacc=c.age and t001.ctab='001' where cli=?";
	private final String REQ_CBS_ADR = "select adr1, adr2, adr3, ville from bkadcli where cli=? and typ='C'";
	private final String REQ_CBS_EMAIL = "select email from bkemacli where cli=?";
	private final String REQ_CBS_TEL = "select num from bktelcli where cli=?";
	private final String REQ_CBS_PRF = "select emp, nom, sig, conv from bkempl where emp in (select emp from bkprfcli where cli=?)";
	private final String REQ_CBS_DIR_CLI ="select nom, pre from bkdircli where cli=?";
	private final String REQ_CBS_FJU ="select lib1 from bknom where ctab='049' and cacc in (select fju from bkcli where cli=?)";
	private final String REQ_CBS_SIT_MAT ="select lib1 from bknom where ctab='047' and cacc in (select sit from bkcli where cli=?)";
	private final String REQ_CBS_CUTI_USER = "select cuti from evuti where cge= ? or gea02= ? and sus='N' order by dmo desc";
	private final String REQ_GED_RENEW_WRESCO = "SELECT 1 as existe FROM public.afb_cli_cresco_solv where matricule = ? and exercice= ? ";
	private final String REQ_FBP_INFOUSER = "SELECT b.code as codeAgeGFC, u.PHONE, (case when u.sex='M' then 'M.' else 'Mme' end) as civilite  From CORE_USER u left join core_branch b on u.branch_id=b.id where login = ?";
	
	Logger logger;
	
	public ManageQueryInfoClientImpl() {
		super();
		logger  = Logger.getLogger(ManageQueryEngagementClientImpl.class.getName());
	}
		
	@Override
	public Map<String, Object> getInfoClient(String matricule, String login, String codeAgeUsr) throws ClassNotFoundException, SQLException{
		Map<String, Object> data = new HashMap<>();
		ResultSet rs;
		
		Object[] parameters = {matricule};
		
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_CLI);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		
		// Parcours du resultat et chargement de la Map
		if(rs != null && rs.next()) {
			data.put("nomClt", rs.getString("nomrest").trim());
			data.put("codeGes", rs.getString("ges").trim());
			data.put("nomGes", rs.getString("nomges").trim());
			data.put("secteurClt", rs.getString("secteur") != null ? rs.getString("secteur").trim() : "");
			data.put("codeAgeClt", rs.getString("age") != null ? rs.getString("age").trim() : "");
			data.put("lna", rs.getString("viln") != null ? rs.getString("viln").trim() : "");
			data.put("libAgeClt", rs.getString("nomage").trim());
			data.put("dCrea", rs.getDate("dcrea"));
			data.put("dou", rs.getDate("dou"));//date d'entré en relation
			data.put("anciennete", Utils.getDiffYears(rs.getDate("dou"), new Date()));//Anciennetee en annee et mois
			data.put("typClt", rs.getString("tcli").trim().equals("1") ? "Particulier" : "Entreprise");
			data.put("pid", (rs.getString("tid") != null ? rs.getString("tid").trim() + " " : "") + (rs.getString("nid") != null ? rs.getString("nid").trim() + " " : "") + (rs.getDate("did") != null ?  "delivre le " + new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("did")) : "") );
		} else data.put("nomClt", "null");
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {data.get("codeGes"),data.get("codeGes")};

		st = con.prepareStatement(REQ_CBS_CUTI_USER);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) 
			data.put("codeUserGFC", rs.getString("cuti").trim()); 
		else data.put("codeUserGFC", login);
		Utils.closeResulSet(null, st);
		
		parameters = new Object[] {matricule};
		st = con.prepareStatement(REQ_CBS_ADR);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) data.put("adrClt", (rs.getString("adr1") != null ? rs.getString("adr1").trim() + " " : "") + (rs.getString("adr2") != null ? rs.getString("adr2").trim() + " " : "") + (rs.getString("adr3") != null ? rs.getString("adr3").trim() + " " : "") + (rs.getString("ville") != null ? rs.getString("ville").trim() : "")  ); else data.put("adrClt", "");
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_EMAIL);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("emailClt", rs.getString("email").trim()); else data.put("emailClt", "");
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_SIT_MAT);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("sitMat", rs.getString("lib1").trim()); else data.put("sitMat", "");
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_TEL);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) {
			data.put("telClt", rs.getString("num") != null ? rs.getString("num").trim() : "");
			while(rs.next()) data.put("telClt", data.get("telClt") + ", " + (rs.getString("num") != null ? rs.getString("num").trim() : ""));
		} else data.put("telClt", "");
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_PRF);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) data.put("employeur", rs.getString("nom").trim()); else data.put("employeur", "");
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_DIR_CLI);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) data.put("dirigeant", (rs.getString("nom") != null ? rs.getString("nom").trim() : "") + (rs.getString("pre") != null ? " " + rs.getString("pre").trim() : "") ); 
		Utils.closeResulSet(null, st);
		
		st = con.prepareStatement(REQ_CBS_FJU);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null && rs.next()) data.put("fju", rs.getString("lib1").trim());
		Utils.closeResulSet(con, st);
		
		parameters = new Object[] {data.get("codeUserGFC").toString().trim()};
		con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		st = con.prepareStatement(REQ_FBP_INFOUSER);
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()){
			
			if( !login.equals(data.get("codeUserGFC")) && !rs.getString("codeAgeGFC").equals(codeAgeUsr) ){
				data.put("codeUserGFC", login);
			}
			data.put("phoneGFC", rs.getString("phone"));
			data.put("civilGFC", rs.getString("civilite"));
			
		}
		Utils.closeResulSet(con ,st);
		
		parameters = new Object[] {matricule, Calendar.getInstance().get(Calendar.YEAR)};
		con = runSqlQuery.getConnection(IConnectionFactory.GED_DB);
		st = con.prepareStatement(REQ_GED_RENEW_WRESCO);
		
		//logger.info(" --> CURRENT SQL : "+generateActualSql(REQ_GED_RENEW_WRESCO.toUpperCase(), parameters));
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()){
			data.put("existe", rs.getString("existe"));
		}
		else
			data.put("existe", 0);
		Utils.closeResulSet(con ,st);
				
		return data;
	}
		


	
	private String generateActualSql(String sqlQuery, Object... parameters) {
		if(parameters == null)
			return sqlQuery;
	    String[] parts = sqlQuery.split("\\?");
	    StringBuilder sb = new StringBuilder();

	    // This might be wrong if some '?' are used as litteral '?'
	    for (int i = 0; i < parts.length; i++) {
	        String part = parts[i];
	        sb.append(part);
	        if (i < parameters.length) {
	            sb.append(formatParameter(parameters[i]));
	        }
	    }

	    return sb.toString();
	}

	private String formatParameter(Object parameter) {
	    if (parameter == null) {
	        return "NULL";
	    } else {
	        if (parameter instanceof String) {
	            return "'" + ((String) parameter).replace("'", "''") + "'";
	        } else if (parameter instanceof Timestamp) {
	            return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").
	                    format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss.ff3')";
	        } else if (parameter instanceof Date) {
	            return "to_date('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
	                    format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss')";
	        } else if (parameter instanceof Boolean) {
	            return ((Boolean) parameter).booleanValue() ? "1" : "0";
	        } else {
	            return parameter.toString();
	        }
	    }
	}
}
