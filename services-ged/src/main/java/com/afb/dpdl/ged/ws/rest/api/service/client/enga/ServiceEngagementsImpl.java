package com.afb.dpdl.ged.ws.rest.api.service.client.enga;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.enga.IManageQueryEngagements;

public class ServiceEngagementsImpl implements IServiceEngagements{

	@Inject
	private IManageQueryEngagements manageQueryEngagements;
	
	@Override
	public JSONObject getEngagementsJSON(String matricule) throws ClassNotFoundException, SQLException {
//		System.out.println("NCP2 : "+ncp);
		Map<String, Object> mapEnga = manageQueryEngagements.getEngagements(matricule);
    	JSONObject jo = new JSONObject();
	    for(String key : mapEnga.keySet()) {
	    	jo.put(key, mapEnga.get(key));
	    }
	    
	    return jo;
		
	}
	
	
}
