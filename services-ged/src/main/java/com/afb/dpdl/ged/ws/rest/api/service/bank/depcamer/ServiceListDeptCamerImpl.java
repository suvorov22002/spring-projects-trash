package com.afb.dpdl.ged.ws.rest.api.service.bank.depcamer;

import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.depcamer.IManageQueryDeptCamer;

public class ServiceListDeptCamerImpl implements IServiceListDeptCamer{

	@Inject
	private IManageQueryDeptCamer manageQueryDeptCamer;
	
	@Override
	public JSONArray getListDeptCamerJSON(){
		
		List<String> list = manageQueryDeptCamer.getListDeptCamer();
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
