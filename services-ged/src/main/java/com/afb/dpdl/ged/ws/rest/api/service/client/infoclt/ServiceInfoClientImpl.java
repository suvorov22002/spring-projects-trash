package com.afb.dpdl.ged.ws.rest.api.service.client.infoclt;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infoclt.IManageQueryInfoClient;

public class ServiceInfoClientImpl implements IServiceInfoClient{

	@Inject
	private IManageQueryInfoClient manageQueryInfoClient;
	
	@Override
	public JSONObject getInfoCliJSON(String matricule, String login, String ageUser) throws ClassNotFoundException, SQLException {
		Map<String, Object> mapCli = manageQueryInfoClient.getInfoClient(matricule, login, ageUser);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
