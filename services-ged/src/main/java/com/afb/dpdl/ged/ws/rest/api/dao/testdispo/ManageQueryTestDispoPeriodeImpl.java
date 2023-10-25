package com.afb.dpdl.ged.ws.rest.api.dao.testdispo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.RunSqlQueryImpl;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryTestDispoPeriodeImpl implements IManageQueryTestDispoPeriode{
	

	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_ALTGRH_TEST_CONGE = "select * from obtenirconge where codeconge in ('C002', 'C001') and matricule=? and ((datedepartconge <= ? and  ? <= dateretour) or (datedepartconge <= ? and  ? <= dateretour) or (? <= datedepartconge and  datedepartconge <= ?) or (? <= dateretour and  dateretour <= ?))";
	
	private final String REQ_GED_TEST_ABS = "select * from afb_absence where emp_mat=? and valide = '1' and ((dateDeb <= ? and  ? <= dateFin) or (dateDeb <= ? and  ? <= dateFin) or (? <= dateDeb and  dateDeb <= ?) or (? <= dateFin and  dateFin <= ?))";
	
	private final String REQ_GED_TEST_MIS = "select * from afb_mission mis, afb_absence ab where mis.refdos = ab.refabs and ab.emp_mat=? and mis.statut = 'VALIDE' and ((datemission <= ? and  ? <= mis.datefin) or (datemission <= ? and  ? <= mis.datefin) or (? <= datemission and  datemission < ?) or (? <= mis.datefin and  mis.datefin <= ?))";


	//private static final Logger logger  = Logger.getLogger(ManageQueryTestDispoPeriodeImpl.class.getName());;
	
	@Override
	public Map<String, Object> getTestDispoPeriode(String matricule, Date dateDeb, Date dateFin) throws ClassNotFoundException, SQLException, ParseException{
		Map<String, Object> data = new HashMap<>();
		
		ResultSet rs, rs2;
		
		Object[] parameters = {
				matricule,
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime())
		};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
		PreparedStatement st = con.prepareStatement(REQ_ALTGRH_TEST_CONGE);
		rs = runSqlQuery.selectData(st , parameters);
		
		Connection con2 = runSqlQuery.getConnection(IConnectionFactory.GED_DB);
		PreparedStatement st2 = con2.prepareStatement(REQ_GED_TEST_ABS);
		rs2 = runSqlQuery.selectData(st2 , parameters);
		
		boolean dispoPeriode = true;
		if((rs != null && rs.next()) || (rs2 != null && rs2.next())) {
			dispoPeriode = false;
		}
		
		data.put("dispoPeriode", dispoPeriode);
		Utils.closeResulSet(con, st);
		Utils.closeResulSet(con2, st2);
		
						
		return data;
	}
	
	public Map<String, Object> getTestDispoPeriodeMission(String matricule, Date dateDeb, Date dateFin)  throws ClassNotFoundException, SQLException, ParseException{
		Map<String, Object> data = new HashMap<>();
		
		ResultSet rs, rs2;
		
		Object[] parameters = {
				matricule,
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime()),
				new java.sql.Date(dateDeb.getTime()),
				new java.sql.Date(dateFin.getTime())
		};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
		PreparedStatement st = con.prepareStatement(REQ_ALTGRH_TEST_CONGE);
		rs = runSqlQuery.selectData(st , parameters);
		
		Connection con2 = runSqlQuery.getConnection(IConnectionFactory.GED_DB);
		PreparedStatement st2 = con2.prepareStatement(REQ_GED_TEST_MIS);
		//logger.info(" --> CURRENT SQL : "+generateActualSql(REQ_GED_TEST_MIS.toUpperCase(), parameters));
		rs2 = runSqlQuery.selectData(st2 , parameters);
		
		boolean dispoPeriode = true;
		if((rs != null && rs.next()) || (rs2 != null && rs2.next())) {
			dispoPeriode = false;
		}
		
		data.put("dispoPeriode", dispoPeriode);
		Utils.closeResulSet(con, st);
		Utils.closeResulSet(con2, st2);
		
						
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
