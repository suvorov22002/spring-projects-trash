package com.afb.dpdl.ged.ws.rest.api.service.client.infopret;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infopret.IManageQueryInfoPret;

public class ServiceInfoPretImpl implements IServiceInfoPret{

	@Inject
	private IManageQueryInfoPret manageQueryInfoPret;
	
	@Override
	public JSONObject getInfoPretJSON(String numPret, String ncp) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> mapCli = manageQueryInfoPret.getInfoPret(numPret, ncp);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
