package com.afb.dpdl.ged.ws.rest.api.service.client.infotransfertcomex;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infotransfertcomex.IManageQueryInfoTransfertComex;

public class ServiceInfoTransfertComexImpl implements IServiceInfoTransfertComex{

	@Inject
	private IManageQueryInfoTransfertComex manageQueryInfoTransfertComex;
	
	
	@Override
	public JSONObject getInfoTransfertComexJSON(String refTrans) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> map = manageQueryInfoTransfertComex.getInfoTransfertComex( refTrans);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
	@Override
	public JSONArray getNatureOpeTrftComexJSON() {
		
		JSONArray ja  = new JSONArray();
		for(String elt : IManageQueryInfoTransfertComex.LISTE_NATURE_OPERATIION) {
			JSONObject jo = new JSONObject();
			jo.put("label", elt);
	    	jo.put("value", elt);
			ja.put(jo);
		}
		return ja;
	}
	
	
	@Override
	public JSONArray getListeDocTrftComexByNatureOpeJSON(String nataureOpe) {
		
		JSONArray ja  = new JSONArray();
		if(nataureOpe == null || nataureOpe.trim().isEmpty())
			return ja;
		int cpt = 0;
		for(String elt : IManageQueryInfoTransfertComex.LISTE_NATURE_OPERATIION) {
			if(nataureOpe.equals(elt)) {
				for(String doc : IManageQueryInfoTransfertComex.LISTE_DOC_NATURE_OPERATIION[cpt]) {
					JSONObject jo = new JSONObject();
					jo.put("label", doc);
			    	jo.put("value", doc);
					ja.put(jo);
				}
				break;
			}
			else
				cpt++;
		}
		return ja;
	}
	
}
