package com.afb.dpdl.ged.ws.rest.api.service.bank.recdom;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.recdom.IManageQueryDomaineReclamation;

public class ServiceDomaineReclamationImpl implements IServiceDomaineReclamation{

	@Inject
	private IManageQueryDomaineReclamation manageQueryDomaineReclamation;
	

	@Override
	public JSONArray getListeDomaineReclamationJSON() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<String> list = manageQueryDomaineReclamation.getListeDomaineReclamation();
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
