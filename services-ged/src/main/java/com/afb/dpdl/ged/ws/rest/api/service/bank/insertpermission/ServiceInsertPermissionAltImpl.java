package com.afb.dpdl.ged.ws.rest.api.service.bank.insertpermission;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.insertpermission.IManageQueryInsertPermissionAlt;

public class ServiceInsertPermissionAltImpl implements IServiceInsertPermissionAlt{

	@Inject
	private IManageQueryInsertPermissionAlt manageQueryInsertPermissionAlt;
	
	
	@Override
	public JSONObject insertPermissionAltJSON(String matricule, String dateDepart, String dateRetour, String motif,
			String loginUtil, String dateModif, int priseCpte, int nbjrs, String codeConge)
			throws ClassNotFoundException, SQLException, ParseException  {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQueryInsertPermissionAlt.insertPermissionAlt(matricule, dateDepart, dateRetour, motif, loginUtil, dateModif, priseCpte, nbjrs, codeConge);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
