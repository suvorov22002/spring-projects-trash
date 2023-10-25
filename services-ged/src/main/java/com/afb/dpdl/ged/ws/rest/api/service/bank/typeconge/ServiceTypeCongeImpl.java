package com.afb.dpdl.ged.ws.rest.api.service.bank.typeconge;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.typeconge.IManageQueryTypeConge;

public class ServiceTypeCongeImpl implements IServiceTypeConge{

	@Inject
	private IManageQueryTypeConge manageQueryTypeConge;
	

	@Override
	public JSONArray getListeTypeCongeJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryTypeConge.getListeTypeConge();
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
