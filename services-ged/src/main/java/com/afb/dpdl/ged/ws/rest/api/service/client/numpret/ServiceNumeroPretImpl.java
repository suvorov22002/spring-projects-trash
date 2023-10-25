package com.afb.dpdl.ged.ws.rest.api.service.client.numpret;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.numpret.IManageQueryNumeroPret;

public class ServiceNumeroPretImpl implements IServiceNumeroPret{

	@Inject
	private IManageQueryNumeroPret manageQueryNumeroPret;
	
	
	@Override
	public JSONArray getNumpretClientJSON(String matricule) throws ClassNotFoundException, SQLException {
		List<String> list = manageQueryNumeroPret.getNumeroPret(matricule);
		JSONArray ja = new JSONArray();
	    for(String elt : list) {
	    	JSONObject jo = new JSONObject();
	    	jo.put("label", elt);
	    	jo.put("value", elt);
	    	ja.put(jo);
	    }
	    
	    JSONObject jo = new JSONObject();
    	jo.put("label", "DECOUVERT");
    	jo.put("value", "000000-00");
    	ja.put(jo);
	    return ja;
	}
}
