package com.afb.dpdl.ged.ws.rest.api.service.bank.infounitebudg;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.infounitebudg.IManageQueryInfoUniteBudgetaire;

public class ServiceInfoUniteBudgetaireImpl implements IServiceInfoUniteBudgetaire{

	@Inject
	private IManageQueryInfoUniteBudgetaire manageQueryInfoUniteBudgetaire;
	
	@Override
	public JSONObject getInfoUniteBudgetaireJSON(String unitebudg, String lieu) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> map = manageQueryInfoUniteBudgetaire.getInfoUniteBudgetaire(unitebudg, lieu);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
}
