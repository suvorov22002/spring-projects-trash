package com.afb.dpdl.ged.ws.rest.api.service.client.cptclt;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.cptclt.IManageQueryCompteClient;

public class ServiceCompteClientImpl implements IServiceCompteClient{
	
	@Inject
	private IManageQueryCompteClient manageQueryCompteClient;
	
	
	@Override
	public JSONArray getComptesClientJSON(String matricule) throws ClassNotFoundException, SQLException {
		List<String> list = manageQueryCompteClient.getComptesClient(matricule);
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
