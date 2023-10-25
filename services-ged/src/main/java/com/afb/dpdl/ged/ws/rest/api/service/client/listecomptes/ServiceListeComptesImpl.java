package com.afb.dpdl.ged.ws.rest.api.service.client.listecomptes;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.listecomptes.IManageQueryListeComptes;

public class ServiceListeComptesImpl implements IServiceListeComptes{
	
	@Inject
	private IManageQueryListeComptes manageQueryListeComptes;
	
	
	@Override
	public JSONArray getListeComptesJSON( @QueryParam("matricule") String matricule) throws ClassNotFoundException, SQLException {
		List<String> list = manageQueryListeComptes.getListeComptes(matricule);
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
