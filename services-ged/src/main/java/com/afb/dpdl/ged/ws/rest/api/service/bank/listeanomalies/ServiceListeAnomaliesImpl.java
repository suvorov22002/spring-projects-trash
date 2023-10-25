package com.afb.dpdl.ged.ws.rest.api.service.bank.listeanomalies;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.listeanomalies.IManageQueryListeAnomalies;

public class ServiceListeAnomaliesImpl implements IServiceListeAnomalies{

	@Inject
	private IManageQueryListeAnomalies manageQueryListeAnomalies;
	
	
	@Override
	public JSONArray getListeAnomaliesJSON() throws ClassNotFoundException, SQLException {
		Map<String, Object> map = manageQueryListeAnomalies.getListeAnomalies();
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
