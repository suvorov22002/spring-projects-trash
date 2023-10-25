package com.afb.dpdl.ged.ws.rest.api.dao.insertpermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInsertPermissionAltImpl implements IManageQueryInsertPermissionAlt{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_ALTGRH_INSERT_MISSION = "insert into OBTENIRCONGE(MATRICULE,DATEDEPARTCONGE,DATERETOUR,MOTIFCONGE,LOGINUTI,DATEMODIF,PRISECPTE,NBJRS,CODECONGE) "
	+"values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	

	@Override
	public Map<String, Object> insertPermissionAlt(String matricule, String dateDepart, String dateRetour, String motif,
			String loginUtil, String dateModif, int priseCpte, int nbjrs, String codeConge)
			throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Object[] parameters = new Object[] {matricule, df.parse(dateDepart), df.parse(dateRetour), motif, loginUtil, df.parse(dateModif), priseCpte, nbjrs, codeConge};
			Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
			PreparedStatement st = con.prepareStatement(REQ_ALTGRH_INSERT_MISSION);
			runSqlQuery.updateData(st, parameters);
			Utils.closeResulSet(con, st);
			//Si absence d'exception
			data.put("insertALTOK", true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			data.put("insertALTOK", false);
			e.printStackTrace();
		}
		
		return data;
	}
	
    
	
}
