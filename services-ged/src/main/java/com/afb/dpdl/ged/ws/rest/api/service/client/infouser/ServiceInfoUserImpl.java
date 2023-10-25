package com.afb.dpdl.ged.ws.rest.api.service.client.infouser;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infouser.IManageQueryInfoUser;

public class ServiceInfoUserImpl implements IServiceInfoUser{

	@Inject
	private IManageQueryInfoUser manageQueryInfoUser;
	
	@Override
	public JSONObject getInfoUserJSON(String login) throws ClassNotFoundException, SQLException {
		Map<String, Object> mapCli = manageQueryInfoUser.getInfoUser(login);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
