package com.afb.dpdl.ged.ws.rest.api.service.client.calculquotite;

import java.text.ParseException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.calculquotite.IManageQueryCalculQuotite;

public class ServiceCalculQuotiteImpl implements IServiceCalculQuotite{

	@Inject
	private IManageQueryCalculQuotite manageQueryCalculQuotite;
	

	@Override
	public JSONObject calculQuotiteJSON(String mensualite, String totMimp, String salm1, String salm2, String salm3,
			String autRev, String retenues, String montant) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQueryCalculQuotite.calculQuotite(mensualite, totMimp, salm1, salm2, salm3, autRev, retenues, montant);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
