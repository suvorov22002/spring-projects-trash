/**
 * 
 */
package com.afriland.afbpaypartnerservices.enums;

/**
 * Messages for Responses
 * @authorYves FOKAM / Rodrigue TOUKAM
 * @version 1.0
 */
public enum MessageResponse {
	
	ELEMENT_ALREADY_EXIST("Element deja enregistre"), 
	
	ELEMENT_DOESNT_EXIST("Element non existant"), 
	
	NULL_OBJET("Element vide ou nulle"), 
	
	NULL_PROPERTY("Champ vide ou nulle"), 
	
	SUCCESSFULL_OPERATION("Opération effectuée avec succès"), 
	
	FAILED_OPERATION("Opération en échec"), 
	
	EMPTY_LIST("Aucune donnée retrouvée"), 
	
	PRINT_BILL_FAIL("Echec de l'impression du reçu"), 
	
	AUTHENTICATION_FAIL("Echec de l'authentification dans la plateforme du partenaire"), 
	
	SEND_TRANSFER_FAIL("Echec de l'enregistrement sur la plateforme du partenaire"), 
	
	VALIDATION_CBS_FAIL("Opération en Attente de Validation dans le Core Banking"), 
	
	POSTING_CBS_FAIL("Echec de l'enregistrement dans le Core Banking"), 
	
	TRANSACTION_WRONG_STATUS("Statut de la transaction incorrecte pour continuer le traitement"), 
	
	AUCUNE_CAISSE_OUVERTE("Aucune caisse ouverte"), 
	
	DCO_NULL("Date comptable vide"), 
	
	DigitalFirstServiceSuspended("DigitalFirst Service Suspended"),
	
	CUSTOMER_NOT_IDENTIFIED("Customer not identified"), 
	
	PartnerNotIdentified("Partner Not Identified"),
	
	Exception("Exception, Merci de contacter un technicien"), 
	
	BankInsufficientBalance("Insufficient Balance"),
	
	InvalidAccount("Invalid Account"),
	
	InvalidStatus("Invalid Status"),
	
	
	/** SUCCESS. */
	SUCCESS("SUCCESS"), 

	/** ERROR. */
	ERROR("ERROR");
	
	
	
	/**
	 * Valeur de la constante (Code)
	 */
	private final String value;
	
	
	/**
	 * Constructeur avec initialisation des parametres
	 * @param value	Valeur du type d'entr�es
	 */
	private MessageResponse(String value) {
		// Initialisation de la valeur
		this.value = value;
	}

	
	/**
	 * Obtention de la valeur 
	 * @return	Valeur
	 */
	public String value() {
		return value;
	}

	/**
	 * Obtention de la valeur 
	 * @return	Valeur
	 */
	public String getValue() {
		return value;
	}
	
}
