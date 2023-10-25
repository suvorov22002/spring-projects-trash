package com.afriland.afbpaypartnerservices.externservices;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.afriland.afbpaypartnerservices.enums.PayPartnerLangue;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.jpa.Transactions;
import com.afriland.afbpaypartnerservices.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * AlerteServices
 * @author yves_labo
 *  
 */
@Slf4j
public class AlerteServices {

	private static Logger log = LogManager.getLogger("AlerteServices.class");

	//*** private static String host = "";
	//*** private static String keysecurity = null;
	private static HttpHeaders headers = new HttpHeaders();


	public static void setHeader(String keysecurity) {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		if(!StringUtils.isBlank(keysecurity)){
			headers.set("Authorization", keysecurity);
		}
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json"); 
	}


	public static String liensig(String keysecurity, String linkcustomerinfo, String age, String ncp, String suf, String cli, String datecstring, String heurec, String utic) {
		RestTemplate client = new RestTemplate();
		String uri = linkcustomerinfo+"/liensig?age="+age+"&ncp="+ncp+"&suf="+suf+"&cli="+cli+"&datecstring="+datecstring+"&heurec="+heurec+"&utic="+utic;

		setHeader(keysecurity);
		log.info("=======================liensig======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
		return response.getBody();
	}


	public static String sendSMS(String keysecurity, String urlSMS, String phone, String message) {
		RestTemplate client = new RestTemplate();
		String uri = urlSMS+"?message="+message+"&phone="+phone;

		setHeader(keysecurity);
		log.info("=======================uri======================== : "+uri);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
		return response.getBody();

	}

	
	public static String sendMessageOtp(String keysecurity, String urlSMS, Transactions trans, Operators operator, Partners partner){
		RestTemplate client = new RestTemplate();

		log.info("SENDING SMS OTP");
		String otp = trans.getSecureOTP();
		String amount = StringUtil.amountFormatter(trans.getAmount());
		String phone = StringUtils.isNotBlank(operator.getPhones()) ? (operator.getPhones().split("/")[0]).replace(" ", "") : "" ;
		String message = "";

		if(phone.length()==9) phone = "237"+phone;
		log.info("OTP : "+otp+" / ID : "+trans.getPartnerTrxID()+" / MT : "+amount+" / PHONE :  "+phone);//phone xyz
		if(trans.getOtp() && StringUtils.isNotBlank(otp) && phone.length()==12){
			// Send Message

			if(operator.getLangue()==PayPartnerLangue.FR) {
				if(partner.getPartname().length()>25) message =  "Votre OTP est :"+otp+". Confirmer la transaction #"+trans.getPartnerTrxID()+" de "+amount+" XAF (Hors frais) effectuee chez le partenaire "+partner.getPartname().toUpperCase().substring(0, 25) ;
				if(partner.getPartname().length()<=25) message =  "Votre OTP est :"+otp+". Confirmer la transaction #"+trans.getPartnerTrxID()+" de "+amount+" XAF (Hors frais) effectuee chez le partenaire "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}
			else if(operator.getLangue()==PayPartnerLangue.EN) {
				if(partner.getPartname().length()>25) message = "Your OTP is :"+otp+". Confirm the transaction #"+trans.getPartnerTrxID()+" of XAF "+amount+" (Excluding fee) made at the partner's "+partner.getPartname().toUpperCase().substring(0, 25) ;
				if(partner.getPartname().length()<=25) message = "Your OTP is :"+otp+". Confirm the transaction #"+trans.getPartnerTrxID()+" of XAF "+amount+" (Excluding fee) made at the partner's "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}

			if(StringUtils.isNotBlank(message) && StringUtils.isNotBlank(phone)) {
				String uri = urlSMS+"?message="+message+"&phone="+phone;
				setHeader(keysecurity);
				log.info("=======================uri======================== : "+uri);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
				log.info("SENDING SMS OK ");
				return response.getBody();				
			}			

		}
		return null;
	}


	public static String sendMessageTrans(String keysecurity, String urlSMS, Transactions trans, Operators operator, Partners partner){
		RestTemplate client = new RestTemplate();

		log.info("SENDING SMS SUCCESS Transaction");
		String amount = StringUtil.amountFormatter(trans.getAmount());
		String phone = StringUtils.isNotBlank(operator.getPhones()) ? (operator.getPhones().split("/")[0]).replace(" ", "") : "" ;
		String message = "";

		if(phone.length()==9) phone = "237"+phone;
		log.info("ID : "+trans.getPartnerTrxID()+" / MT : "+amount+" / PHONE :  "+phone);//phone xyz
		if(phone.length()==12){
			// Send Message

			if(operator.getLangue()==PayPartnerLangue.FR) {
				if(partner.getPartname().length()>25) message =  "Votre transaction #"+trans.getPartnerTrxID()+" de "+amount+" XAF (Hors frais) effectuee avec succès chez le partenaire "+partner.getPartname().toUpperCase().substring(0, 25) ;
				if(partner.getPartname().length()<=25) message =  "Votre transaction #"+trans.getPartnerTrxID()+" de "+amount+" XAF (Hors frais) effectuee avec succès chez le partenaire "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}
			else if(operator.getLangue()==PayPartnerLangue.EN) {
				if(partner.getPartname().length()>25) message = "Your transaction #"+trans.getPartnerTrxID()+" of XAF "+amount+" (Excluding fee) successfully carried out at the partner "+partner.getPartname().toUpperCase().substring(0, 25) ;
				if(partner.getPartname().length()<=25) message = "Your transaction #"+trans.getPartnerTrxID()+" of XAF "+amount+" (Excluding fee) successfully carried out at the partner "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}

			if(StringUtils.isNotBlank(message) && StringUtils.isNotBlank(phone)) {
				String uri = urlSMS+"?message="+message+"&phone="+phone;
				setHeader(keysecurity);
				log.info("=======================uri======================== : "+uri);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
				log.info("SENDING SMS OK ");
				return response.getBody();				
			}			

		}
		return null;
	}



	public static String sendKeyOperator(String keysecurity, String urlSMS, String keyOperator, Operators operator, Partners partner){
		RestTemplate client = new RestTemplate();


		log.info("SENDING SMS");
		String phone = StringUtils.isNotBlank(operator.getPhones()) ? (operator.getPhones().split("/")[0]).replace(" ", "") : "" ;
		String message = "";

		if(phone.length()==9) phone = "237"+phone;
		log.info("keyOperator : "+keyOperator+" / PHONE :  "+phone);//phone xyz
		if(StringUtils.isNotBlank(keyOperator) && phone.length()==12){
			// Send Message

			if(operator.getLangue()==PayPartnerLangue.FR) {
				if(partner.getPartname().length()>25) message =  "Votre PIN est :"+keyOperator+", pour les transactions effectuees chez le partenaire "+partner.getPartname().toUpperCase().substring(0, 25) + ", est : " + keyOperator ;
				if(partner.getPartname().length()<=25) message =  "Votre OTP est :"+keyOperator+", pour les transactions effectuees chez le partenaire "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}
			else if(operator.getLangue()==PayPartnerLangue.EN) {
				if(partner.getPartname().length()>25) message = "Your OTP is :"+keyOperator+". for transactions carried out at the partner "+partner.getPartname().toUpperCase().substring(0, 25) ;
				if(partner.getPartname().length()<=25) message = "Your OTP is :"+keyOperator+". for transactions carried out at the partner "+partner.getPartname().toUpperCase() ; //partner.toUpperCase()
			}
			log.info("======================= message ======================== : "+message);

			if(StringUtils.isNotBlank(message) && StringUtils.isNotBlank(phone)) {
				String uri = urlSMS+"?message="+message+"&phone="+phone;
				setHeader(keysecurity);
				log.info("=======================uri======================== : "+uri);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<String> response = client.exchange(uri, HttpMethod.GET, entity, String.class);
				log.info("SENDING SMS OK ");
				return response.getBody();				
			}			

		}
		return null;
	}

}
