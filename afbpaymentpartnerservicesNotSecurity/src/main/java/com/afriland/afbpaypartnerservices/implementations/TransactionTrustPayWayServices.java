package com.afriland.afbpaypartnerservices.implementations;



import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afriland.afbpaypartnerservices.dtos.PaymentDetails;
import com.afriland.afbpaypartnerservices.dtos.PaymentResponse;
import com.afriland.afbpaypartnerservices.dtos.trusayway.TrustPaymentDetails;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.services.ITransactionService;
import com.afriland.afbpaypartnerservices.services.ITransactionTrustPayWayService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;


/**
 * TransactionServices
 * @author yves_labo
 *  
 */
@Service("transactionservicetrustpayway")
@Transactional
public class TransactionTrustPayWayServices implements ITransactionTrustPayWayService {

	Logger logger = LoggerFactory.getLogger(TransactionTrustPayWayServices.class);


	@Autowired
	private PropertyConfigRepository propertyConfigRepository;
	
	@Autowired
	private ITransactionService transactionService;

	
	@Value("${partner.code.trustpayway}")
	private String partnerCodeTrustPayway;

	@Value("${application.partner.trustpayway.token_url}")
	private String urlTokenTrustPayway;

	@Value("${application.partner.trustpayway.access_url}")
	private String urlAccessTrustPayway;

	@Value("${application.partner.trustpayway.grant_type}")
	private String grantTypeTrustPayway;

	@Value("${application.partner.trustpayway.client_secret}")
	private String clientSecretTrustPayway;
	
	@Value("${application.partner.trustpayway.client_secret}")
	private String clientIdTrustPayway;

	@Value("${application.partner.trustpayway.scope}")
	private String scopeTrustPayway;
	
	@Value("${application.partner.trustpayway.owner_name}")
	private String ownerNameTrustPayway;

	@Value("${application.partner.trustpayway.owner_password}")
	private String ownerPasswordTrustPayway;

	
	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}


	public String getpartnerCodeTrustPayway(){
		return getValue(partnerCodeTrustPayway);
	}

	public String geturlTokenTrustPayway(){
		return getValue(urlTokenTrustPayway);
	}

	public String geturlAccessTrustPayway(){
		return getValue(urlAccessTrustPayway);
	}

	public String getgrantTypeTrustPayway(){
		return getValue(grantTypeTrustPayway);
	}

	public String getclientSecretTrustPayway(){
		return getValue(clientSecretTrustPayway);
	}

	public String getclientIdTrustPayway(){
		return getValue(clientIdTrustPayway);
	}

	public String getscopeTrustPayway(){
		return getValue(scopeTrustPayway);
	}

	public String getownerNameTrustPayway(){
		return getValue(ownerNameTrustPayway);
	}

	public String getownerPasswordTrustPayway(){
		return getValue(ownerPasswordTrustPayway);
	}

	@PostConstruct
	private void init(){
		partnerCodeTrustPayway = getpartnerCodeTrustPayway();
		urlTokenTrustPayway = geturlTokenTrustPayway();
		urlAccessTrustPayway = geturlAccessTrustPayway();
		grantTypeTrustPayway = getgrantTypeTrustPayway();
		clientSecretTrustPayway = getclientSecretTrustPayway();			
		clientIdTrustPayway = getclientIdTrustPayway();		
		scopeTrustPayway = getscopeTrustPayway();
		ownerNameTrustPayway = getownerNameTrustPayway();
		ownerPasswordTrustPayway = getownerPasswordTrustPayway();
	}


	/*
	private String host = null;
	private String port = null;
	private String protocole = null;
	private String path = null;
	*/

	static final TrustStrategy trustAllStrategy = new TrustStrategy(){
		public boolean isTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			return true;
		}
	};	


	public ResponseEntity<PaymentResponse> payments(TrustPaymentDetails trustPaymentDetails){
		//
		try{
			//*** init(); 
			logger.info("payments : trustPaymentDetails = " + trustPaymentDetails.toString());
			PaymentDetails paymentDetails = ObjectMapperUtils.mapTrustPaymentDetails(trustPaymentDetails);
			paymentDetails.setPartnerCode(partnerCodeTrustPayway);
			logger.info("paymentDetails = " + paymentDetails.toString());
			
			return transactionService.virementTransactions(paymentDetails);
		}catch(Exception e){
			return new ResponseEntity<PaymentResponse>(new PaymentResponse(null, Integer.toString(HttpStatus.PRECONDITION_FAILED.value())), HttpStatus.PRECONDITION_FAILED);
		}

	}

}
