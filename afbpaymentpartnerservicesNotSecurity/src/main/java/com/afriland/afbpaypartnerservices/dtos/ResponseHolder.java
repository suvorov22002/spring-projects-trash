package com.afriland.afbpaypartnerservices.dtos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;


public class ResponseHolder {
	
	public static String SUCESS = "200";
	public static String SUCESS2 = "503";
	public static String FAIL = "000";
		
	public static String NOT_FOUND = "404";
	public static String AUTHENTICATION_FAIL = "001";
	public static String PRINT_BILL_FAIL = "002";
	public static String SEND_TRANSFER_FAIL = "003";
	public static String TRANSACTION_WRONG_STATUS = "004";
	public static String POSTING_CBS_FAIL = "005";
	public static String VALIDATION_CBS_FAIL = "006";
	public static String AUCUNE_CAISSE_OUVERTE = "007";
	public static String DCO_NULL = "008";
	public static String Exception = "009";
	
	
	public static Map<String, String> mapMessage = new HashMap<String, String>();
	
	public ResponseHolder(){
		
	}
	
	public static  String message(String key){
		init();
		return mapMessage.get(key);
	}

	//@PostConstruct
	@Bean
	public static void init(){
		if(mapMessage.isEmpty()){
			mapMessage = new HashMap<String, String>();
			mapMessage.put(SUCESS,"Opération validée avec succès");	
			mapMessage.put(PRINT_BILL_FAIL,"Impression du recu a échoué");	
			mapMessage.put(AUTHENTICATION_FAIL,"Authentification dans la plateforme du partenaire a échoué");	
			mapMessage.put(NOT_FOUND,"La transaction n'existe pas");	
			mapMessage.put(FAIL,"La transaction a échouée");	
			mapMessage.put(SEND_TRANSFER_FAIL,"Enregistrement à échoué dans la plateforme du partenaire");
			mapMessage.put(VALIDATION_CBS_FAIL,"Opération en Attente de Validation dans Amplitude");	
			mapMessage.put(POSTING_CBS_FAIL,"Enregistrement à échoué dans Amplitude");	
			mapMessage.put(TRANSACTION_WRONG_STATUS,"Statut de la transaction inccorrecte pour continuer le traitement");
			mapMessage.put(AUCUNE_CAISSE_OUVERTE,"AUCUNE CAISSE OUVERTE");
			mapMessage.put(DCO_NULL,"Date Comptable Vide");
			mapMessage.put(Exception,"Exception Merci de contacter un IT");
		}
		
	}
	
}
