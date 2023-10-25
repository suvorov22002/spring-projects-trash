package com.afb.dpdl.ged.ws.rest.api.service.client.infocltrecla;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infocltrecla.IManageQueryInfoClientRecla;

public class ServiceInfoClientReclaImpl implements IServiceInfoClientRecla{

	@Inject
	private IManageQueryInfoClientRecla manageQueryInfoClientRecla;
	
	@Override
	public JSONObject getInfoCliReclaJSON(String matricule, String login, String ageUser) throws ClassNotFoundException, SQLException {
		Map<String, Object> mapCli = manageQueryInfoClientRecla.getInfoClientRecla(matricule, login, ageUser);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
