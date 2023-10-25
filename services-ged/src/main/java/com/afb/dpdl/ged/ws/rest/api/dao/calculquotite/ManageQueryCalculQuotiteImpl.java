package com.afb.dpdl.ged.ws.rest.api.dao.calculquotite;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class ManageQueryCalculQuotiteImpl implements IManageQueryCalculQuotite{

	@Override
	public Map<String, Object> calculQuotite(String mensualite, String totMimp, String salm1, String salm2, String salm3, String autRev,
			String retenues, String montant) throws ParseException {
		// TODO Auto-generated method stub
		// Initialisation de la Map a retourner
		Map<String, Object> data = new HashMap<String, Object>();
		Double salMoy = 0d, salCess = 0d, autRevCess = 0d, quoCess = 0d, quoDispo = 0d, ajustement = 0d;
		Double sal1 = 0d, sal2 = 0d, sal3 = 0d, ret = 0d, totalMensImpayees = 0d, autR = 0d, mens = 0d;
		double montantDecouvert = 0;
		
		DecimalFormat df = new DecimalFormat("###0");
		
		if(mensualite!=null){
			try{ 
				mens = Double.valueOf( mensualite.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				mens = df.parse(mensualite).doubleValue();
			}
		}
		if(totMimp!=null && !totMimp.trim().isEmpty()){
			try{ 
				totalMensImpayees = Double.valueOf( totMimp.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				totalMensImpayees = df.parse(totMimp).doubleValue();
			}
		}
		if(salm1!=null){
			try{ 
				sal1 = Double.valueOf( salm1.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				sal1 = df.parse(salm1).doubleValue();
			}
		}
		if(salm2!=null){
			try{ 
				sal2 = Double.valueOf( salm2.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				sal2 = df.parse(salm2).doubleValue();
			}
		}
		if(salm3!=null){
			try{ 
				sal3 = Double.valueOf( salm3.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				sal3 = df.parse(salm3).doubleValue();
			}
		}
		if(autRev!=null){
			try{ 
				autR = Double.valueOf( autRev.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				autR = df.parse(autRev).doubleValue();
			}
		}
		if(retenues!=null){
			try{ 
				ret = Double.valueOf( retenues.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				ret = df.parse(retenues).doubleValue();
			}
		}
		if(montant!=null){
			try{ 
				montantDecouvert = Double.valueOf( montant.replaceAll(" ", "").replaceAll(",", "") ); 
			}catch(Exception e1){
				montantDecouvert = df.parse(montant).doubleValue();
			}
		}
		
		salMoy = (sal1 + sal2 + sal3) / 3;
		Long totalConsom = null;
		
		if(totalMensImpayees > 0){
			totalConsom = Math.round((montantDecouvert + totalMensImpayees)*100/ salMoy);
			data.put("totalConsom", totalConsom);
		}
		else{
			totalConsom = Math.round((montantDecouvert*100/salMoy));
			data.put("totalConsom", totalConsom);
		}
		
		Double salRef = salMoy + ret, totalCess = 0d, maxSalCess = 0d; //, revMoy = (revm + revm1 + revm2) / 3;
		Double[] limits = new Double[]{18750d, 37500d, 75000d, 112500d, 142500d, 142501d};
		Double[] fractionsRet = new Double[]{0d, 0d, 0d, 0d, 0d, 0d};
		Double[] taux = new Double[]{10d, 20d, 25d, 33.3333333333333d, 50d, 100d};
		Double[] fractionsCess = new Double[]{0d, 0d, 0d, 0d, 0d, 0d};
		int i=0; boolean trouve = false;
		while(i<limits.length && !trouve){
			
			if(salRef > limits[i]) {
				
				switch(i){
					case 0 : fractionsRet[i] = Math.min(salRef, limits[i]); break;
					case 5 : fractionsRet[i] = Math.max(salRef - limits[i-1], 0); break;
					default : fractionsRet[i] = Math.max(Math.min(limits[i]-limits[i-1], salRef - limits[i-1]), 0); break;
				}
				
				fractionsCess[i] = fractionsRet[i] * taux[i] / 100;
				totalCess += fractionsCess[i];
			} else { trouve = true; i--; }
			i++;
		}
		if(i<6) {
			switch(i){
				case 0 : fractionsRet[i] = Math.min(salRef, limits[i]); break;
				case 5 : fractionsRet[i] = Math.max(salRef - limits[i-1], 0); break;
				default : fractionsRet[i] = Math.max(Math.min(limits[i]-limits[i-1], salRef - limits[i-1]), 0); break;
			}
			fractionsCess[i] = fractionsRet[i] * taux[i] / 100;
			totalCess += fractionsCess[i];
		}
		maxSalCess = Math.min(Math.max(salRef - 500000d, 0) + Math.min(salRef, 500000d)*0.5, totalCess);
		
		salCess = maxSalCess - ret;
		autRevCess = autR * 0.5;
		quoCess = salCess + autRevCess;
		quoDispo = quoCess - totalMensImpayees;
		ajustement = Math.max( mens - quoDispo, 0);
				
		data.put("salMoy", df.format(salMoy));
		data.put("salCess", df.format(salCess));
		data.put("autRevCess", df.format(autRevCess));
		data.put("quoCess", df.format(quoCess));
		data.put("quoDispo", df.format(quoDispo));
		data.put("ajustement", df.format(ajustement));
		
		return data;
	}
	
	
	
}
