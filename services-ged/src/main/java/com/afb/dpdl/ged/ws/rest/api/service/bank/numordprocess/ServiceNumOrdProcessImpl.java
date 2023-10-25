package com.afb.dpdl.ged.ws.rest.api.service.bank.numordprocess;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.numordprocess.IManageQueryNumOrdProcess;

public class ServiceNumOrdProcessImpl implements IServiceNumOrdProcess{

	@Inject
	private IManageQueryNumOrdProcess manageQueryNumOrdProcess;
	
	@Override
	public JSONObject getNumOrdProcessJSON(String codeAgeSaisie, String idProcess) throws ClassNotFoundException, SQLException {
		
		Map<String, Object> map = manageQueryNumOrdProcess.getNumOrdProcess(codeAgeSaisie, idProcess);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
}
