package com.afb.dpdl.ged.ws.rest.api.service.bank.townlist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.townlist.IManageQueryListeVille;

public class ServiceListeVilleImpl implements IServiceListeVille{

	@Inject
	private IManageQueryListeVille manageQueryListeVille;
	

	@Override
	public JSONArray getListeVilleJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<String> list = manageQueryListeVille.getListeVille();
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
