package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Type String
 * @author yves_labo
 * @version 1.0
 */
public enum PayPartnerTypeString {
	
	/**
	 * Alphanumerique
	 */
	AL("Alphabetique"),
	
	
	/**
	 * Alphanumerique
	 */
	AN("Alphanumerique"),
	
	
	/**
	 * Numerique
	 */
	NU("Numerique");
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private PayPartnerTypeString(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<PayPartnerTypeString> getValues() {
		
		// Initialisation de la collection a retourner
		List<PayPartnerTypeString> ops = new ArrayList<PayPartnerTypeString>();
		
		// Ajout des valeurs
		ops.add(AL);
		ops.add(AN);
		ops.add(NU);
		
		// Retourne la collection
		return ops;
		
	}

	public static PayPartnerTypeString getValue(String shearch) {
		for(PayPartnerTypeString t : PayPartnerTypeString.getValues()){
			if(t.name().equalsIgnoreCase(shearch)) return t;
		}
		// Retourne la collection
		return null;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
