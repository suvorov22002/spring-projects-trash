package com.afb.dpdl.ged.ws.rest.api.service.bank.fraismission;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.fraismission.IManageQueryFraisMission;

public class ServiceFraisMissionImpl implements IServiceFraisMission{

	@Inject
	private IManageQueryFraisMission manageQueryFraisMission;
	

	@Override
	public JSONObject getFraisMissionJSON(String lieu, String nbrJours, String grade, String fonction, String moyenTrans, String villedepart, 
			String villedestinataire) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryFraisMission.getFraisMission(lieu, nbrJours, grade, fonction, moyenTrans, villedepart, villedestinataire);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
}
