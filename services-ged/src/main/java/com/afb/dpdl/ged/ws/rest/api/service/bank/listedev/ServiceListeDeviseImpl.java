package com.afb.dpdl.ged.ws.rest.api.service.bank.listedev;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.listedev.IManageQueryListeDevive;

public class ServiceListeDeviseImpl implements IServiceListeDevise{

	@Inject
	private IManageQueryListeDevive manageQueryListeDevise;
	

	@Override
	public JSONArray getListeDeviseJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<String> list = manageQueryListeDevise.getListeDevise();
		JSONArray ja = new JSONArray();
	    for(String elt : list) {
	    	JSONObject jo = new JSONObject();
	    	jo.put("label", elt);
	    	jo.put("value", elt);
	    	ja.put(jo);
	    }
	    return ja;
	}
	
	
}
