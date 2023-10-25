package com.afb.dpdl.ged.ws.rest.api.dao.listecomptes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryListeComptesImpl implements IManageQueryListeComptes{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_ALTGRH_LIST_COMPTES = "select cpt.matricule as matricule, emp.AGENCE_VIRE, cpt.numerocompte, cpt.cle from compteemploye cpt, employe emp, agence ag where emp.AGENCE_VIRE = ag.CODEAGENCE " + 
					"and cpt.MATRICULE = emp.MATRICULE and emp.radie=0 and cpt.matricule = ?";
	
		
	@Override
	public List<String>  getListeComptes(String matricule)  throws ClassNotFoundException, SQLException{
		List<String> data = new ArrayList<>();
		
		Object[] parameters = {matricule};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
		PreparedStatement st = con.prepareStatement(REQ_ALTGRH_LIST_COMPTES);
		ResultSet rs = runSqlQuery.selectData(st, parameters);
		while(rs != null && rs.next()) {
			if(rs.getString("numerocompte") != null) {
				data.add("000" + rs.getString("AGENCE_VIRE")+"-"+ rs.getString("numerocompte")+"-"+rs.getString("cle"));
			}
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
    
	
}
