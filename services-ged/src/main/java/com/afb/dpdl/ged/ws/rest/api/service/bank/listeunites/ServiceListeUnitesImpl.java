package com.afb.dpdl.ged.ws.rest.api.service.bank.listeunites;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.listeunites.IManageQueryListeUnites;

public class ServiceListeUnitesImpl implements IServiceListeUnites{

	@Inject
	private IManageQueryListeUnites manageQueryListeUnites;
	
	
	@Override
	public JSONArray getListeUnitesJSON() throws ClassNotFoundException, SQLException {
		Map<String, Object> map = manageQueryListeUnites.getListeUnites();
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
