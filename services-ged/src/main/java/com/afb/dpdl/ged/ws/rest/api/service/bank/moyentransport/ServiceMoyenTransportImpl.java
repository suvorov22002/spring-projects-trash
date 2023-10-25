package com.afb.dpdl.ged.ws.rest.api.service.bank.moyentransport;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.moyentransport.IManageQueryMoyenTransport;

public class ServiceMoyenTransportImpl implements IServiceMoyenTransport{

	@Inject
	private IManageQueryMoyenTransport manageQueryMoyenTransport;
	
	
	@Override
	public JSONArray getMoyenTransportJSON() throws ClassNotFoundException, SQLException {
		List<String> list = manageQueryMoyenTransport.getMoyenTransport();
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
