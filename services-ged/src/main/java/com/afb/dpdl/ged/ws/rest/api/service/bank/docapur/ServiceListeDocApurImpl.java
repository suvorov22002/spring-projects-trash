package com.afb.dpdl.ged.ws.rest.api.service.bank.docapur;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.cptclt.IManageQueryCompteClient;
import com.afb.dpdl.ged.ws.rest.api.dao.docapur.IManageQueryListeDocApur;

public class ServiceListeDocApurImpl implements IServiceListeDocApur{
	
	@Inject
	private IManageQueryListeDocApur manageQueryListeDocApur;
	
	
	@Override
	public JSONArray getListeDocApurJSON(String refDos) throws ClassNotFoundException, SQLException {
		Map<String, Object> map = manageQueryListeDocApur.getListeDocApur(refDos);
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
