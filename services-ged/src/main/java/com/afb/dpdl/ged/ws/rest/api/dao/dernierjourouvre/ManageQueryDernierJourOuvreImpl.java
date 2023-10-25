package com.afb.dpdl.ged.ws.rest.api.dao.dernierjourouvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryDernierJourOuvreImpl implements IManageQueryDernierJourOuvre{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	private final String REQ_CBS_JRS_FERIES = "select jourfer from bkfer where TO_CHAR(jourfer,'YYYY')= ?";
	
	
	@Override
	public Map<String, Object> getDernierJourOuvre(Date dateDeb, Double nbjrPerm) throws ClassNotFoundException, SQLException, ParseException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		String years = null;
		List<Date> listeJourFerier = new ArrayList<>();
		ResultSet rs = null;
		
		dateDeb = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US).parse(dateDeb.toString());
    	years = new SimpleDateFormat("yyyy").format(dateDeb);
		
    	Object[] parameters = new Object[] {years};
    	

		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_JRS_FERIES);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
    	while (rs.next()) {
			listeJourFerier.add(rs.getDate(1));
        }
    	Utils.closeResulSet(con,st);
    	
		Calendar calendar = Calendar.getInstance(Locale.US);
		calendar.setTime(dateDeb);
		
		while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || listeJourFerier.contains(calendar.getTime()) ){
			calendar.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		for(int i = 0;i<nbjrPerm-1;i++){
			calendar.add(Calendar.DAY_OF_WEEK, 1);
			while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || listeJourFerier.contains(calendar.getTime()) ){
				calendar.add(Calendar.DAY_OF_WEEK, 1);
			}
		}
		
		Date dateFin = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat formatALT = new SimpleDateFormat("dd/MM/yyyy");	
		data.put("dateFin", format.format(dateFin));
		data.put("dateFinALT", formatALT.format(dateFin));
    	
		return data;
	}

	
}
