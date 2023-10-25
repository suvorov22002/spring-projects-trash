package com.afb.dpdl.ged.ws.rest.api.service.bank.recnat;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.recnat.IManageQueryNatureReclamation;

public class ServiceNatureReclamationImpl implements IServiceNatureReclamation{

	@Inject
	private IManageQueryNatureReclamation manageQueryNatureReclamation;
	
	
	@Override
	public JSONArray getListeNatureReclamationJSON(String domaine) throws ClassNotFoundException, SQLException {
		Map<String, Object> map = manageQueryNatureReclamation.getListeNatureReclamation(domaine);
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
