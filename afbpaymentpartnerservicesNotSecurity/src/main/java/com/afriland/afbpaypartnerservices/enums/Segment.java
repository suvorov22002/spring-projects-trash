package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;


public enum Segment {

	TRANSPORT_AERIEN("TRANSPORT_AERIEN"),
	TRANSPORT_MARITIME("TRANSPORT_MARITIME"),
	TRANSPORT_ROUTIER("TRANSPORT_ROUTIER");
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private Segment(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<Segment> getValues() {
		
		// Initialisation de la collection a retourner
		List<Segment> ops = new ArrayList<Segment>();
		
		// Ajout des valeurs
		ops.add(TRANSPORT_AERIEN);
		ops.add(TRANSPORT_MARITIME);
		ops.add(TRANSPORT_ROUTIER);
		
		// Retourne la collection
		return ops;
		
	}

	public static Segment getValue(String shearch) {
		for(Segment t : Segment.getValues()){
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
