package com.afriland.afbpaypartnerservices.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.afriland.afbpaypartnerservices.dtos.PropertyConfigsDto;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.response.PropertyConfigsResponse;
import com.afriland.afbpaypartnerservices.services.IPropertyConfigService;
import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("propertyconfigservice")
@Transactional
public class PropertyConfigServices implements IPropertyConfigService {

	@Autowired
	private PropertyConfigRepository propertyConfigRepository;
	
	
	@Value("${application.afriland.url.sms}")
	private String urlSms;

	@Value("${application.afriland.cbsservice.baseurl}")
	private String host;

	@Value("${application.afriland.cbsservice.keysecurity}")
	private String keysecurity;

	@Value("${application.partner.phonedefault.transaction}")
	private String phonedefault;

	@Value("${application.partner.emaildefault.transaction}")
	private String emaildefault;

	@Value("${application.afriland.cbsservice.utiebanking}")
	private String utiebanking ;
		
	@Value("${partner.code.trustpayway}")
	private String partnerCodeTrustPayway;

	@Value("${partner.code.cimencam}")
	private String partnerCodeCimencam;

	@Value("${partner.code.rtc}")
	private String partnerCodeRtc;

	@Value("${partner.code.douane}")
	private String partnerCodeDouane;

	@Value("${partner.code.sprintpay}")
	private String partnerCodeSprintpay;

	@Value("${partner.code.minitrans}") 
	private String partnerCodeMinitrans;

	@Value("${application.service.actif}")
	private String applicationServiceActif;

	@Value("${application.code.operation.virement}")
	private String applicationCodeOperationVirement;

	@Value("${application.service.transaction.opposition}")
	private String transactionopposition;

	@Value("${application.afriland.cbsservice.linkcustomerinfo}")
	private String linkcustomerinfo;

	@Value("${application.service.account.minitrans}")
	private String applicationAccountMinitrans;

	@Value("${application.service.transaction.ope.nivellement}")
	private String applicationServiceOpeNivellement;

	@Value("${application.service.account.liaison}")
	private String applicationServiceAccountLiaison;

	@Value("${application.service.code.user}")
	private String applicationServiceCodeUser;

	@Value("${application.service.niveau.check.transaction}")
	private String applicationServiceNiveauCheckTrans;
	
	
	@Value("${application.service.validite.otp}")
	private String applicationServiceValiditeOtp;
	
	@Value("${application.service.size.otp}")
	private String applicationServiceSizeOtp;
	
	@Value("${application.service.type.otp}")
	private String applicationServiceTypeOtp;
	
	@Value("${application.service.default.pin}")
	private String applicationServiceDefaultPin;


	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(PropertyConfigServices.class);


	@Override
	public ResponseEntity<PropertyConfigsResponse> savePropertyConfig(PropertyConfigsDto propertyConfigDtoRequest) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			if (propertyConfigDtoRequest == null) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(propertyConfigDtoRequest.getCode())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(propertyConfigDtoRequest.getValue())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (value)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(propertyConfigDtoRequest.getDescription())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (description)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			PropertyConfigs existingPropertyConfig = propertyConfigRepository.findByCode(propertyConfigDtoRequest.getCode()); 
			if (existingPropertyConfig != null) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}
			
			if (propertyConfigDtoRequest.getCode().trim().contains("PWD")) {
				propertyConfigDtoRequest.setValue(AfbEncryptionDecryption.encrypt(propertyConfigDtoRequest.getValue(), PropertyConfigs.secretKey));
			}
			
			PropertyConfigs propertyConfigResponse = ObjectMapperUtils.mapPropertyConfigsDtoToPropertyConfigs(propertyConfigDtoRequest);
			propertyConfigResponse = propertyConfigRepository.save(propertyConfigResponse);
			if (propertyConfigResponse != null) {
				datas.add(ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(propertyConfigResponse));
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> saveListPropertyConfigs(List<PropertyConfigsDto> propertyConfigDtoRequests) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			for(PropertyConfigsDto propertyConfigDtoRequest : propertyConfigDtoRequests) {
				try {
					if (propertyConfigDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(propertyConfigDtoRequest.getCode())) {
						break;
					}
					if (StringUtils.isBlank(propertyConfigDtoRequest.getValue())) {
						break;
					}
					if (StringUtils.isBlank(propertyConfigDtoRequest.getDescription())) {
						break;
					}

					PropertyConfigs existingPropertyConfig = propertyConfigRepository.findByCode(propertyConfigDtoRequest.getCode()); 
					if (existingPropertyConfig != null) {
						break;
					}

					PropertyConfigs propertyConfigResponse = ObjectMapperUtils.mapPropertyConfigsDtoToPropertyConfigs(propertyConfigDtoRequest);
					propertyConfigResponse = propertyConfigRepository.save(propertyConfigResponse);

					if (propertyConfigResponse != null) {
						datas.add(ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(propertyConfigResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> updatePropertyConfig(PropertyConfigsDto propertyConfigDtoRequest) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {					

			if (propertyConfigDtoRequest == null) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(propertyConfigDtoRequest.getId())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(propertyConfigDtoRequest.getCode())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(propertyConfigDtoRequest.getValue())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (value)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(propertyConfigDtoRequest.getDescription())) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (description)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<PropertyConfigs> optionalPropertyConfig = propertyConfigRepository.findById(propertyConfigDtoRequest.getId()); 
			if (optionalPropertyConfig == null) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			PropertyConfigs propertyConfigs = optionalPropertyConfig.get();
			if(StringUtils.isNotBlank(propertyConfigDtoRequest.getValue())) propertyConfigs.setValue(propertyConfigDtoRequest.getValue());
			if(StringUtils.isNotBlank(propertyConfigDtoRequest.getDescription())) propertyConfigs.setDescription(propertyConfigDtoRequest.getDescription());

			propertyConfigs = propertyConfigRepository.save(propertyConfigs);
			if (propertyConfigs != null) {
				List<PropertyConfigsDto> parts = new ArrayList<PropertyConfigsDto>();
				parts.add(ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(propertyConfigs));
				datas = parts;
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> deletePropertyConfig(String id) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			propertyConfigRepository.deleteById(id);
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> findByIdPropertyConfig(String id) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<PropertyConfigs> optionalPropertyConfig = propertyConfigRepository.findById(id);
			if (optionalPropertyConfig != null) {
				PropertyConfigs propertyConfigResponse = optionalPropertyConfig.get();
				datas.add(ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(propertyConfigResponse));
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> getAllPropertyConfigs() {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			List<PropertyConfigs> propertyConfigs = propertyConfigRepository.findAll();
			if (propertyConfigs != null && !CollectionUtils.isEmpty(propertyConfigs)) {
				datas = propertyConfigs.stream().map(response -> {
					return ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			PropertyConfigs propertyConfigResponse = propertyConfigRepository.findByCode(code);
			if (propertyConfigResponse != null) {
				datas.add(ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(propertyConfigResponse));
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<PropertyConfigsResponse> findByDescriptionLike(String libelle){
		// TODO Auto-generated method stub
		List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();

		try {
			List<PropertyConfigs> propertyConfigs = propertyConfigRepository.findByDescriptionLike((new StringBuilder()).append("%").append(libelle).append("%").toString());
			if (propertyConfigs != null && !CollectionUtils.isEmpty(propertyConfigs)) {
				datas = propertyConfigs.stream().map(response -> {
					return ObjectMapperUtils.mapPropertyConfigsToPropertyConfigsDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<PropertyConfigsResponse>(new PropertyConfigsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public String getValue(String code){
		PropertyConfigs prop = propertyConfigRepository.findByCode(code);
		if(prop != null){
			return prop.getValue();
		}
		return null;
	}
	
	
	@Override
	public String geturlSms(){
		return getValue(urlSms);
	}

	@Override
	public String gethost(){
		return getValue(host);
	}

	@Override
	public String getkeysecurity(){
		return getValue(keysecurity);
	}

	@Override
	public String getemaildefault(){
		return getValue(emaildefault);
	}

	@Override
	public String getphonedefault(){
		return getValue(phonedefault);
	}

	@Override
	public String getutiebanking(){
		return getValue(utiebanking);
	}
	
	@Override
	public String getlinkcustomerinfo(){
		return getValue(linkcustomerinfo);
	}

	@Override
	public String getpartnerCodeTrustPayway(){
		return getValue(partnerCodeTrustPayway);
	}

	@Override
	public String getpartnerCodeCimencam(){
		return getValue(partnerCodeCimencam);
	}

	@Override
	public String getpartnerCodeRtc(){
		return getValue(partnerCodeRtc);
	}

	@Override
	public String getpartnerCodeDouane(){
		return getValue(partnerCodeDouane);
	}

	@Override
	public String getpartnerCodeSprintpay(){
		return getValue(partnerCodeSprintpay);
	}

	@Override
	public String getpartnerCodeMinitrans(){
		return getValue(partnerCodeMinitrans);
	}

	@Override
	public String getapplicationServiceActif(){
		return getValue(applicationServiceActif);
	}

	@Override
	public String gettransactionopposition(){
		return getValue(transactionopposition);
	}

	@Override
	public String getapplicationCodeOperationVirement(){
		return getValue(applicationCodeOperationVirement);
	}

	@Override
	public String getapplicationaccountminitrans(){
		return getValue(applicationAccountMinitrans);
	} 

	@Override
	public String getapplicationServiceOpeNivellement(){
		return getValue(applicationServiceOpeNivellement);
	} 

	@Override
	public String getapplicationServiceAccountLiaison(){
		return getValue(applicationServiceAccountLiaison);
	} 

	@Override
	public String getapplicationServiceCodeUser(){
		return getValue(applicationServiceCodeUser);
	} 

	@Override
	public String getapplicationServiceNiveauCheckTrans(){
		return getValue(applicationServiceNiveauCheckTrans);
	}
	
	
	@Override
	public String getapplicationServiceValiditeOtp(){
		return getValue(applicationServiceValiditeOtp);
	}
	
	@Override
	public String getapplicationServiceSizeOtp(){
		return getValue(applicationServiceSizeOtp);
	}
	
	@Override
	public String getapplicationServiceTypeOtp(){
		return getValue(applicationServiceTypeOtp);
	}

	@Override
	public String getapplicationServiceDefaultPin(){
		return getValue(applicationServiceDefaultPin);
	}
	
}
