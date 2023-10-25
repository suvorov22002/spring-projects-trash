package com.afb.dpdl.ged.ws.rest.api.service.client.engaclt;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.engaclt.IManageQueryEngagementClient;

public class ServiceEngagementClientImpl implements IServiceEngagementClient{

	@Inject
	private IManageQueryEngagementClient manageQueryEngagementClient;
	
	@Override
	public JSONObject getEngagementCliJSON(String matricule, String ncp) throws ClassNotFoundException, SQLException {
		Map<String, Object> mapCli = manageQueryEngagementClient.getEngagementClient(matricule, ncp);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
