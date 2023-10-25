package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.PropertyConfigsDto;
import com.afriland.afbpaypartnerservices.response.PropertyConfigsResponse;


public interface IPropertyConfigService {
	
	public ResponseEntity<PropertyConfigsResponse> savePropertyConfig(PropertyConfigsDto propertyConfigsDto);
	
	public ResponseEntity<PropertyConfigsResponse> saveListPropertyConfigs(List<PropertyConfigsDto> propertyConfigsDto);

	public ResponseEntity<PropertyConfigsResponse> updatePropertyConfig(PropertyConfigsDto propertyConfigsDto);
	
	public ResponseEntity<PropertyConfigsResponse> deletePropertyConfig(String id);
	
	public ResponseEntity<PropertyConfigsResponse> findByCode(String code);	
	
	public ResponseEntity<PropertyConfigsResponse> findByDescriptionLike(String libelle);

	public ResponseEntity<PropertyConfigsResponse> getAllPropertyConfigs();
	
	public ResponseEntity<PropertyConfigsResponse> findByIdPropertyConfig(String id);	
	
	public String geturlSms();
	
	public String gethost();
	
	public String getkeysecurity();
	
	public String getemaildefault();
	
	public String getphonedefault();
	
	public String getutiebanking();
	
	public String getlinkcustomerinfo();
	
	public String getpartnerCodeTrustPayway();
	
	public String getpartnerCodeMinitrans();
	
	public String getpartnerCodeCimencam();
	
	public String getpartnerCodeRtc();
	
	public String getpartnerCodeDouane();
	
	public String getpartnerCodeSprintpay();
	
	public String getapplicationServiceActif();
	
	public String gettransactionopposition();
	
	public String getapplicationCodeOperationVirement();
	
	public String getapplicationaccountminitrans();
	
	public String getapplicationServiceOpeNivellement();
	
	public String getapplicationServiceAccountLiaison();
	
	public String getapplicationServiceCodeUser();
	
	public String getapplicationServiceNiveauCheckTrans();	
		
	public String getapplicationServiceValiditeOtp();
	
	public String getapplicationServiceSizeOtp();
	
	public String getapplicationServiceTypeOtp();
	
	public String getapplicationServiceDefaultPin();

}
