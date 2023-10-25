package com.afb.dpdl.ged.ws.rest.api.service.client.affauto;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.affauto.IManageQueryAffAuto;

public class ServiceAffAutoImpl implements IServiceAffAuto{

	@Inject
	private IManageQueryAffAuto manageQueryAffAuto;
	
	
	@Override
	public JSONObject getUsersAffAutoJSON(String matricule, String typeClient, String codeAgeSaisie, Double montant,
			String secteurActivite, String currentPhase) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQueryAffAuto.getUsersAffAuto(matricule, typeClient, codeAgeSaisie, montant, secteurActivite, currentPhase);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
}
