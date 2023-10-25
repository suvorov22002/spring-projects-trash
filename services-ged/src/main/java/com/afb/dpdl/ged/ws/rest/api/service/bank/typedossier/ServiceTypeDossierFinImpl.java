package com.afb.dpdl.ged.ws.rest.api.service.bank.typedossier;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.typedossier.IManageQueryTypeDossierFin;

public class ServiceTypeDossierFinImpl implements IServiceTypeDossierFin{

	@Inject
	private IManageQueryTypeDossierFin manageQueryTypeDossierFin;
	

	@Override
	public JSONArray getListeTypeDossierFinJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryTypeDossierFin.getListeTypeDossierFin();
		JSONArray ja = new JSONArray();
		for(String key : map.keySet()) {
			JSONObject jo = new JSONObject();
			jo.put(key, map.get(key));
			ja.put(jo);
		}
		return ja;
	}
	
}
