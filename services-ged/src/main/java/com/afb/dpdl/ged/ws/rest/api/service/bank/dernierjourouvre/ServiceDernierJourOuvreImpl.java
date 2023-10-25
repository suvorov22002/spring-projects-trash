package com.afb.dpdl.ged.ws.rest.api.service.bank.dernierjourouvre;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.dernierjourouvre.IManageQueryDernierJourOuvre;

public class ServiceDernierJourOuvreImpl implements IServiceDernierJourOuvre{

	@Inject
	private IManageQueryDernierJourOuvre manageQueryDernierJourOuvre;
	
	
	@Override
	public JSONObject getDernierJourOuvreJSON(Date dateDeb, Double nbjrPerm)
			throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub

		Map<String, Object> map = manageQueryDernierJourOuvre.getDernierJourOuvre(dateDeb, nbjrPerm);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
}
