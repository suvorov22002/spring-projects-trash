package com.afriland.afbpaypartnerservices.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Type d'operations
 * @author JAZA/LABO
 * @version 1.0
 */
public enum PayPartnerExceptionCode {
	
	/**
	 * Pull from account
	 */
	SubscriberInvalidOTP("Invalid Bank OTP"),
	SubscriberExpiredOTP("Expired Bank OTP"),
	SubscriberInvalidKey("Invalid Subscriber Key"),
	SubscriberInvalidAccount("Invalid Account Number"),
	SubscriberInvalidAmount("Invalid Amount"),
	SubscriberNotFound("Subscriber Not Found"),
	SubscriberAmountExceedMaxAllowed("Amount Exceed Max Allowed"),
	SubscriberWaitingValidation("Subscriber Waiting for Validation"),
	SubscriberSuspended("Suspended Contract"),
	SubscriberPartnerWaitingValidation("Partner Contract Waiting for Validation"),
	SubscriberPartnerSuspended("Suspended Partner Contract"),
	SubscriberNotIdentified("Suspended Not Identified"),
	SubscriberInvalid("Souscription invalide"),
		
	BankClosingAccount("Close Or Closing Account Error"),
	BankBlockingDebitAccount("Debit Blocking Account Error"),
	BankBlockingCreditAccount("Credit Blocking Account Error"),
	BankInsufficientBalance("Insufficient Balance"),
	PartnerExceededAmount("Partner Ceiling Exceeded"),
	BankExceededAmount("Bank Ceiling Exceeded"),
	BankException("Bank Exception Error"),
	
	PartnerServiceSuspended("Partner Service Suspended"),
	PartnerNotIdentified("Partner Not Identified"),
	PartnerServiceOnMaintenance("Partner Service On Maintenance"),
	PartnerTrxSuspended("Partner Transactions Suspended"),
	DigitalFirstServiceSuspended("DigitalFirst Service Suspended"),
	DigitalFirstServiceOnMaintenance("DigitalFirst Service On Maintenance"),
	DigitalFirstTrxSuspended("DigitalFirst Transactions Suspended"),
	
	NetWorkSendingPartnerRequest("Sending Partner Resquest Error"),
	NetWorkReceivingPartnerRequestResponse("Receiving Partner Request Response Error"),
	NetWorkReceivingPartnerRequestConfirmation("Receiving Partner Request Confirmation Error"),
	NetWorkSendingPartnerRequestConfirmation("Sending Partner Request Confirmation Error"),
	
	SystemCoreBankingSystemAcces("Bank Core Banking System Error"),
	SystemCommittingTransaction("Bank Commit Transaction Error"),
	SystemNotValidatedTransaction("Bank Transaction Not Validated in Core Banking System"),
	
	// Plafond
	SubscriberLimitPaymentDayReached("Limit Payment Day Reached"),
	SubscriberLimitPaymentWeekReached("Limit Payment Week Reached"),
	SubscriberLimitPaymentMonthReached("Limit Payment Month Reached"),
		
	
	// Unicite TRX ID
	TransactionIDAlreadyExist("Transaction Already Exist With This Partner Transaction ID"),
	
	// Validité des détails de la TRX
	TransactionDetailsNotReach("Unable to check transaction details on the partner system"),
	TransactionDetailsNotValid("Transaction details not valid"),
	TransactionInvalidAccount("Invalid Account Number for Transaction"),
	TransactionInvalidAmount("Invalid Amount  for Transaction"),
	
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
	
	CUSTOMER_NOT_IDENTIFIED("Customer not identified"), 
	
	InvalidAccount("Invalid Account"),
	
	Exception("Exception, Merci de contacter un technicien"),
	
	FORFAIT_NOT_IDENTIFIED("Forfait not identified"), 
	
	EXCEPTION_PARTNER("Exception coté partenaire");
	
	
	/**
	 * Valeur
	 */
	private String value;
	
	/**
	 * Constructeur
	 * @param value
	 */
	private PayPartnerExceptionCode(String value){
		this.setValue(value);
	}
	
	/**
	 * Retourne la liste des valeus
	 * @return liste des codes des execeptions
	 */
	public static List<PayPartnerExceptionCode> getValues() {
		
		// Initialisation de la collection a retourner
		List<PayPartnerExceptionCode> ops = new ArrayList<PayPartnerExceptionCode>();
		
		// Ajout des valeurs
		ops.add(SubscriberInvalidOTP);
		ops.add(SubscriberExpiredOTP);
		ops.add(SubscriberInvalidKey);
		ops.add(SubscriberInvalidAccount);
		ops.add(SubscriberInvalidAmount);
		ops.add(SubscriberNotFound);
		ops.add(SubscriberAmountExceedMaxAllowed);
		ops.add(SubscriberWaitingValidation);
		ops.add(SubscriberSuspended);
		ops.add(SubscriberPartnerWaitingValidation);
		ops.add(SubscriberPartnerSuspended);
		
		ops.add(BankClosingAccount);
		ops.add(BankBlockingDebitAccount);
		ops.add(BankBlockingCreditAccount);
		ops.add(BankInsufficientBalance);
		ops.add(PartnerExceededAmount);
		ops.add(BankExceededAmount);
		
		ops.add(PartnerServiceSuspended);
		ops.add(PartnerServiceOnMaintenance);
		ops.add(PartnerTrxSuspended);
		ops.add(DigitalFirstServiceSuspended);
		ops.add(DigitalFirstServiceOnMaintenance);
		ops.add(DigitalFirstTrxSuspended);
		
		ops.add(NetWorkSendingPartnerRequest);
		ops.add(NetWorkReceivingPartnerRequestResponse);
		ops.add(NetWorkReceivingPartnerRequestConfirmation);
		ops.add(NetWorkSendingPartnerRequestConfirmation);
		
		ops.add(SystemCoreBankingSystemAcces);
		ops.add(SystemCommittingTransaction);
		ops.add(SystemNotValidatedTransaction);
		
		ops.add(SubscriberLimitPaymentDayReached);
		ops.add(SubscriberLimitPaymentWeekReached);
		ops.add(SubscriberLimitPaymentMonthReached);
		
		ops.add(TransactionIDAlreadyExist);
		ops.add(TransactionDetailsNotValid);
		ops.add(TransactionDetailsNotReach);
		
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
