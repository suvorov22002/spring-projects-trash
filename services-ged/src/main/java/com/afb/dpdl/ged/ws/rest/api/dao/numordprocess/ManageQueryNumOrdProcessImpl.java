package com.afb.dpdl.ged.ws.rest.api.dao.numordprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryNumOrdProcessImpl implements IManageQueryNumOrdProcess{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_GED_SELECT_NUMORD = "select numord from afb_numordre_process where datejr = ? and age = ? and idprocess = ?";
	
	private final String REQ_GED_INSERT_NUMORD = "INSERT INTO afb_numordre_process (numord, datejr, age, idprocess) VALUES (?,?,?,?) ";
	
	private final String REQ_GED_UPDATE_NUMORD = "UPDATE afb_numordre_process set numord = ? where datejr = ? and age = ? and idprocess = ?";
	
	@Override
	public Map<String, Object> getNumOrdProcess(String codeAgeSaisie, String idProcess) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		
		ResultSet rs = null;
		int numord = 0;
		
		codeAgeSaisie = codeAgeSaisie != null ? codeAgeSaisie : "00001";
		
		Object[] parameters = new Object[] {new java.sql.Date(new Date().getTime()), codeAgeSaisie, idProcess};

		Connection con = runSqlQuery.getConnection(IConnectionFactory.GED_DB);
		PreparedStatement st = con.prepareStatement(REQ_GED_SELECT_NUMORD);
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null && rs.next()) {
			numord = rs.getInt("numord");
		}
		Utils.closeResulSet(null, st);
		
		if(numord == 0){
			numord = 1;
			parameters = new Object[] {numord, new java.sql.Date(new Date().getTime()), codeAgeSaisie, idProcess};
			st = con.prepareStatement(REQ_GED_INSERT_NUMORD);
			runSqlQuery.updateData(st, parameters);
		}
		else{
			numord++;
			parameters = new Object[] {numord, new java.sql.Date(new Date().getTime()), codeAgeSaisie, idProcess};
			st = con.prepareStatement(REQ_GED_UPDATE_NUMORD);
			runSqlQuery.updateData(st, parameters);
		}
		
		Utils.closeResulSet(con, st);
		if(numord<10)
			data.put("numord", "00"+numord);
		else if(numord>=10 && numord <100)
			data.put("numord", "0"+numord);
		else
			data.put("numord", ""+numord);
		
		return data;
		
	}

	
}
