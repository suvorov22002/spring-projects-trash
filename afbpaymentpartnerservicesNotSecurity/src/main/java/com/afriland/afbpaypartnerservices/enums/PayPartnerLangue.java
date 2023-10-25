package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Type Langue
 * @author FOKAM/TOUKAM
 * @version 1.0
 */
public enum PayPartnerLangue {
	
	/**
	 * English
	 */
	EN("English"),
	
	
	/**
	 * Français
	 */
	FR("Français");
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private PayPartnerLangue(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<PayPartnerLangue> getValues() {
		
		// Initialisation de la collection a retourner
		List<PayPartnerLangue> ops = new ArrayList<PayPartnerLangue>();
		
		// Ajout des valeurs
		ops.add(EN);
		ops.add(FR);
		
		// Retourne la collection
		return ops;
		
	}

	public static PayPartnerLangue getValue(String shearch) {
		for(PayPartnerLangue t : PayPartnerLangue.getValues()){
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
