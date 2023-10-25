package com.afb.dpdl.ged.ws.rest.api.service.bank.typeforcage;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.typeforcage.IManageQueryTypeForcage;

public class ServiceTypeForcageImpl implements IServiceTypeForcage{

	@Inject
	private IManageQueryTypeForcage manageQueryTypeForcage;
	

	@Override
	public JSONArray getListeTypeForcageJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryTypeForcage.getListeTypeForcage();
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
