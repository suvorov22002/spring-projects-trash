package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

public enum PayPartnerStatutSubscriber {

	ACTIF("Validée"),

	WAITING("En Attente"),

	SUSPENDU("Suspendu"),

	CANCEL("Annulée");

	/**
	 * Valeur
	 */
	private String value;

	/**
	 * Constructeur
	 * @param value
	 */
	private PayPartnerStatutSubscriber(String value){
		this.setValue(value);
	}

	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<PayPartnerStatutSubscriber> getValues() {

		// Initialisation de la collection a retourner
		List<PayPartnerStatutSubscriber> ops = new ArrayList<PayPartnerStatutSubscriber>();

		// Ajout des valeurs
		ops.add(ACTIF);
		ops.add(CANCEL);
		ops.add(SUSPENDU);
		ops.add(CANCEL);

		// Retourne la collection
		return ops;		
	}


	/**
	 * Retourne la liste des valeus
	 * @return
	 */
	public static List<PayPartnerStatutSubscriber> getValuesValid() {

		// Initialisation de la collection a retourner
		List<PayPartnerStatutSubscriber> ops = new ArrayList<PayPartnerStatutSubscriber>();

		// Ajout des valeurs
		ops.add(ACTIF);
		ops.add(SUSPENDU);
		ops.add(CANCEL);

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
