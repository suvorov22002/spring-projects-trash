package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

public enum FpstatutFileTransaction {
	
	/**
	 * Actif
	 */
	ACTIF("Validé"),
	
	/**
	 * CONTROLE
	 */
	CONTROLE("Controlé"),
	
	/**
	 * DECHIFFRE
	 */
	DECHIFFRE("Dechiffré"),
	
	/**
	 * DEPOSIT
	 */
	DEPOSIT("DEPOSIT"),
	
	/**
	 * En cours
	 */
	ENCOURS("En cours"),
	
	/**
	 * Uploaded
	 */
	UPLOADED("Uploaded"),
	
	/**
	 * Valide
	 */
	VALIDE("Valide"),

	/**
	* Echec
	*/
	ECHEC("Echec"),

	/**
	 * Annulee
	 */
	CANCEL("Annulé");
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private FpstatutFileTransaction(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<FpstatutFileTransaction> getValues() {
		
		// Initialisation de la collection a retourner
		List<FpstatutFileTransaction> status = new ArrayList<FpstatutFileTransaction>();
		
		// Ajout des valeurs
		status.add(ACTIF); 
		status.add(CANCEL);
		status.add(CONTROLE); 
		status.add(DECHIFFRE); 
		status.add(DEPOSIT);
		status.add(ECHEC);  
		status.add(ENCOURS);   
		status.add(UPLOADED);
		status.add(VALIDE);
		
		// Retourne la collection
		return status;
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
