package com.afb.dpdl.ged.ws.rest.api.service.bank.listeunitesv2;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.listeunitesv2.IManageQueryListeUnitesV2;

public class ServiceListeUnitesV2Impl implements IServiceListeUnitesV2{

	@Inject
	private IManageQueryListeUnitesV2 manageQueryListeUnitesV2;
	
	
	@Override
	public JSONArray getListeUnitesV2JSON() throws ClassNotFoundException, SQLException {
		List<String> list = manageQueryListeUnitesV2.getListeUnitesV2();
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
