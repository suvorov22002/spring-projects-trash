package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;


public enum TransactionStatus {

	VALID,
	TO_CONFIRM,
	INITIATE,
	CANCEL,
	ERROR,
	PROCESSING,FAILED,REGUL,CLOSE,SUCCESS,REVERSED,UNDERINVESTIGATION,ENCAISSEE,EBANKING,CAISSE,INVALID,EXTOURNE,NONCONFORME;

	/**
	 * Valeur
	 */
	private String value;


	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<TransactionStatus> getValuesAccept() {
		
		// Initialisation de la collection a retourner
		List<TransactionStatus> ops = new ArrayList<TransactionStatus>();
		
		// Ajout des valeurs
		ops.add(TO_CONFIRM);
		ops.add(PROCESSING);
		ops.add(ENCAISSEE); 
		ops.add(SUCCESS); 
		
		// Retourne la collection
		return ops;
		
	}
	
	
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<TransactionStatus> getValuesAcceptPartner() {
		
		// Initialisation de la collection a retourner
		List<TransactionStatus> ops = new ArrayList<TransactionStatus>();
		
		// Ajout des valeurs
		ops.add(TO_CONFIRM);
		ops.add(ENCAISSEE); 
		ops.add(SUCCESS); 
		
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
