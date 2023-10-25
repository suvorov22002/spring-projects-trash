package com.afb.dpdl.ged.ws.rest.api.service.bank.testdispo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.testdispo.IManageQueryTestDispoPeriode;

public class ServiceTestDispoPeriodeImpl implements IServiceTestDispoPeriode{

	@Inject
	private IManageQueryTestDispoPeriode manageQueryTestDispoPeriode;
	

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getTestDispoPeriodeJSON(String matricule, Date dateDeb, Date dateFin)
			throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQueryTestDispoPeriode.getTestDispoPeriode(matricule, dateDeb, dateFin);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getTestDispoPeriodeMissionJSON(String matricule, Date dateDeb, Date dateFin)
			throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> mapCli = manageQueryTestDispoPeriode.getTestDispoPeriodeMission(matricule, dateDeb, dateFin);
		JSONObject jo = new JSONObject();
		for(String key : mapCli.keySet()) {
			jo.put(key, mapCli.get(key));
		}
		return jo;
	}
	
	
}
