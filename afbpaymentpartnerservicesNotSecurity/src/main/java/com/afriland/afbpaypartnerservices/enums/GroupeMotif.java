package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;


public enum GroupeMotif {
		
	AUTORISATION("AUTORISATION"),	
	LICENSE_ORDINAIRE("LICENSE_ORDINAIRE"),	
	LICENSE_SPECIALE("LICENSE_SPECIALE"),	
	PERMIS_DE_CONDUIRE("PERMIS_DE_CONDUIRE");
	
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private GroupeMotif(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<GroupeMotif> getValues() {
		
		// Initialisation de la collection a retourner
		List<GroupeMotif> ops = new ArrayList<GroupeMotif>();
		
		// Ajout des valeurs
		ops.add(AUTORISATION);
		ops.add(LICENSE_ORDINAIRE);
		ops.add(LICENSE_SPECIALE); 
		ops.add(PERMIS_DE_CONDUIRE); 
		
		// Retourne la collection
		return ops;
		
	}

	public static GroupeMotif getValue(String shearch) {
		for(GroupeMotif t : GroupeMotif.getValues()){
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
