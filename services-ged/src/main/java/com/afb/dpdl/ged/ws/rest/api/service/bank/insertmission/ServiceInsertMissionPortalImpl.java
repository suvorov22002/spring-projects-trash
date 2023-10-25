package com.afb.dpdl.ged.ws.rest.api.service.bank.insertmission;

import java.sql.SQLException;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.insertmission.IManageQueryInsertMissionPortal;

public class ServiceInsertMissionPortalImpl implements IServiceInsertMissionPortal{

	@Inject
	private IManageQueryInsertMissionPortal manageQueryInsertMissionPortal;
	
	
	@Override
	public JSONObject insertMissionPortalJSON(String nom, String ref, String fonction, String grade, String cellule, String ncp, int nbrJours, String moyenTrans, 
			String unitebudg, String villeDepart, String villeDestinataire, String paysAfricain, String paysEtranger, String codeOpe, double transportReel, 
			double missionReel, String ncpTransport, String ncpMission, String cutiAnalComp, String cutiDcompt) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> map = manageQueryInsertMissionPortal.insertMissionPortal(nom, ref, fonction, grade, cellule, ncp, nbrJours, moyenTrans, unitebudg, 
				villeDepart, villeDestinataire, paysAfricain, paysEtranger, codeOpe, transportReel, missionReel, ncpTransport, ncpMission, cutiAnalComp, cutiDcompt);
		JSONObject jo = new JSONObject();
		for(String key : map.keySet()) {
			jo.put(key, map.get(key));
		}
		return jo;
	}
	
	
}
