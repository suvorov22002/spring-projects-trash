/**
 * 
 */
package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories d'exceptions
 * @author JAZA/LABO
 * @version 1.0
 */
public enum PayPartnerExceptionCategory {

	/**
	 * Erreur de saisie de la part du client
	 */
	SUBSCRIBER("Subscriber.Edit.Error"),
	
	/**
	 * Exception niveau transaction bancaire
	 */
	METIER("Bank.Transaction.Error"),
	
	/**
	 * Erreur de communication reseau
	 */
	NETWORK("Network.Communication.Error"),
	
	/**
	 * Erreur liee a la MAJ de la transaction  par le systeme
	 */
	SYSTEM("Bank.System.Error");

	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private PayPartnerExceptionCategory(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeurs
	 * @return liste des categories d'exception
	 */
	public static List<PayPartnerExceptionCategory> getValues() {
		
		// Initialisation de la collection a retourner
		List<PayPartnerExceptionCategory> ops = new ArrayList<PayPartnerExceptionCategory>();
		
		// Ajout des valeurs
		ops.add(SUBSCRIBER);
		ops.add(METIER);
		ops.add(NETWORK);
		ops.add(SYSTEM);
		
		// Retourne la collection
		return ops;
		
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
