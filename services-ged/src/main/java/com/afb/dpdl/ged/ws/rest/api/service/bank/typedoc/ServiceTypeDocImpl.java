package com.afb.dpdl.ged.ws.rest.api.service.bank.typedoc;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.typedoc.IManageQueryTypeDoc;

public class ServiceTypeDocImpl implements IServiceTypeDoc{

	@Inject
	private IManageQueryTypeDoc manageQueryTypeDoc;
	

	@Override
	public JSONArray getListeTypeDocJSON(String typedossier, String typeclient)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryTypeDoc.getListeTypeDoc(typedossier, typeclient);
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
