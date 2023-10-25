package com.afb.dpdl.ged.ws.rest.api.service.bank.infosemploye;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infosemploye.IManageQueryInfosEmploye;

public class ServiceInfosEmployeImpl implements IServiceInfosEmploye{

	@Inject
	private IManageQueryInfosEmploye manageQueryInfosEmploye;
	
	@Override
	public JSONObject getInfosEmployeJSON(String matricule) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> map = manageQueryInfosEmploye.getInfosEmploye(matricule);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	

	@Override
	public JSONArray getInfosEmployeJSON_TEST(String matricule) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> map = manageQueryInfosEmploye.getInfosEmploye(matricule);
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put("label", map.get(key));
	    	jo.put("value", map.get(key));
	    	ja.put(jo);
		}
	    return ja;
	}
	
	@Override
	public JSONArray getMatVehiculeEmployeJSON(String matricule) throws ClassNotFoundException, SQLException {
		
		List<String> list = manageQueryInfosEmploye.getMatVehiculeEmploye(matricule);
		
		JSONArray ja = new JSONArray();
	    for(String elt : list) {
	    	JSONObject jo = new JSONObject();
	    	jo.put("label", elt);
	    	jo.put("value", elt);
	    	ja.put(jo);
	    }
	    return ja;
	}
}
