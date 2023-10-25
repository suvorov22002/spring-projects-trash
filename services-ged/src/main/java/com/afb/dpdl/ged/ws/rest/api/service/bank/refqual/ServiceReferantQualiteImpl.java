package com.afb.dpdl.ged.ws.rest.api.service.bank.refqual;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.refqual.IManageQueryReferantQualite;

public class ServiceReferantQualiteImpl implements IServiceReferantQualite{

	@Inject
	private IManageQueryReferantQualite manageQueryReferantQualite;
	
	@Override
	public JSONObject getReferantQualiteJSON(String codeAgeUser) throws ClassNotFoundException, SQLException {
		Map<String, Object> mapCli = manageQueryReferantQualite.getReferantQualite(codeAgeUser);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
