package com.afriland.afbpaypartnerservices.implementations;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.afriland.afbpaypartnerservices.dtos.ObjectFile;
import com.afriland.afbpaypartnerservices.dtos.OperatorsDto;
import com.afriland.afbpaypartnerservices.dtos.ParameterJasper;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.externservices.AlerteServices;
import com.afriland.afbpaypartnerservices.jpa.ForfaitPayments;
import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.Partners;
import com.afriland.afbpaypartnerservices.repositories.ForfaitPaymentRepository;
import com.afriland.afbpaypartnerservices.repositories.OperatorRepository;
import com.afriland.afbpaypartnerservices.repositories.PartnerRepository;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.OperatorsResponse;
import com.afriland.afbpaypartnerservices.services.IOperatorService;
import com.afriland.afbpaypartnerservices.services.IPropertyConfigService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;
import com.afriland.afbpaypartnerservices.utils.ToolsUtils;



@Service("operatorservice")
@Transactional
public class OperatorServices implements IOperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private ForfaitPaymentRepository forfaitPaymentRepository;

	@Autowired
	private IPropertyConfigService propertyConfigService;

	@PersistenceContext
	private EntityManager entityManager;


	Logger logger = LoggerFactory.getLogger(OperatorServices.class);


	/*
	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}

	public String getpartnerCodeMinitrans(){
		return getValue(partnerCodeMinitrans);
	}
	 */


	@Override
	public ResponseEntity<OperatorsResponse> saveOperator(OperatorsDto operatorDtoRequest) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (operatorDtoRequest == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(operatorDtoRequest.getCodeClient())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code client)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getClientName())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (client Name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getCustomerReferencePartner())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer Partner Reference)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getCustomerNamePartner())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer Partner Name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getPartnerCode())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getUtiunitiate())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Initiate user)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getAgenceSous())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Agence souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if(propertyConfigService.getpartnerCodeMinitrans().equals(operatorDtoRequest.getPartnerCode())) {
				if (StringUtils.isBlank(operatorDtoRequest.getActivity())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Activité du client)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				if (StringUtils.isBlank(operatorDtoRequest.getDivisionAdministratif())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Délégation départementale de paiement)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				if (StringUtils.isBlank(operatorDtoRequest.getAdresseOperator())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Adresse du client)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
			}

			//Controle des infos de partenaire
			Partners partner = partnerRepository.findByPartcode(operatorDtoRequest.getPartnerCode());
			if (partner == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, " Partner code", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Operators existingOperator = operatorRepository.findByCode(operatorDtoRequest.getCode()); 
			if (existingOperator != null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			Operators existingOperator2 = operatorRepository.findByPartnerCodeAndCodeClient(operatorDtoRequest.getPartnerCode(), operatorDtoRequest.getCodeClient()); 
			if (existingOperator2 != null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, " Code client et partner code", datas), HttpStatus.CONFLICT);
			}

			Operators operatorResponse = ObjectMapperUtils.mapOperatorDtoToOperator(operatorDtoRequest);
			String phones = "";		
			for(String val : operatorDtoRequest.getTxtPhones() ) phones = StringUtils.isBlank(phones) ? val : phones + "/" + val;
			operatorDtoRequest.setPhones(phones);
			String accounts = "";
			for(String val : operatorDtoRequest.getTxtAccounts()) phones = StringUtils.isBlank(accounts) ? val : accounts + "/" + val;
			operatorDtoRequest.setAccounts(accounts);


			if(operatorDtoRequest.getValid() == null) operatorResponse.setValid(Boolean.TRUE);
			int nbreCharacters = partner.getSizeKey() != null ? partner.getSizeKey() : 10;
			operatorResponse.setKeyOperator(operatorDtoRequest.getPartnerCode()+RandomStringUtils.randomAlphanumeric(nbreCharacters).toUpperCase());
			operatorResponse.setValidfrom(new Date());
			operatorResponse.setOperatorStatus(PayPartnerStatutSubscriber.WAITING);
			//*** operatorResponse.setUtiunitiate(operatorDtoRequest.getUtiunitiate());
			operatorResponse.setUtimodif(operatorDtoRequest.getUtiunitiate());
			if(propertyConfigService.getpartnerCodeMinitrans().equals(operatorDtoRequest.getPartnerCode())) {
				operatorResponse.setCustomerNiuPartner(operatorDtoRequest.getCustomerReferencePartner());
			}
			if(propertyConfigService.getpartnerCodeTrustPayway().equals(operatorDtoRequest.getPartnerCode())) {
				operatorResponse.setCustomerReferencePartner(operatorDtoRequest.getCustomerNiuPartner());
			}

			operatorResponse = operatorRepository.save(operatorResponse);
			if (operatorResponse != null) {
				OperatorsDto response = ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse);

				//set bordereau de souscription
				try {
					response.setBordereauSouscription(getBordereauSouscription(response));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				datas.add(response);
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> saveListOperators(List<OperatorsDto> operatorDtoRequests) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			for(OperatorsDto operatorDtoRequest : operatorDtoRequests) {
				try {
					/*
					if (operatorDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(operatorDtoRequest.getCodeClient())) {
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getClientName())) {
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getCustomerReferencePartner())) { 
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getCustomerNamePartner())) {
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getPartnerCode())) {
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getUtiunitiate())) {
						break;
					}
					if (StringUtils.isBlank(operatorDtoRequest.getAgenceSous())) {
						break;
					}
					*/

					//Controle des infos de partenaire
					Partners partner = partnerRepository.findByPartcode(operatorDtoRequest.getPartnerCode());

					Operators existingOperator = operatorRepository.findByCode(operatorDtoRequest.getCode()); 
					if (existingOperator != null) {
						break;
					}

					Operators operatorResponse = ObjectMapperUtils.mapOperatorDtoToOperator(operatorDtoRequest);
					/* ***
					List<OperatorPhones> phones = new ArrayList<OperatorPhones>();			
					for(String val : operatorDtoRequest.getTxtPhones() )phones.add(new OperatorPhones(operatorResponse,val));
					operatorDtoRequest.setPhones(phones);
					List<OperatorAccounts> accounts = new ArrayList<OperatorAccounts>();
					for(String val : operatorDtoRequest.getTxtAccounts())accounts.add(new OperatorAccounts(operatorResponse,val));
					operatorDtoRequest.setAccounts(accounts);
					 */

					int nbreCharacters = partner.getSizeKey() != null ? partner.getSizeKey() : 10;
					operatorResponse.setKeyOperator(operatorDtoRequest.getPartnerCode()+RandomStringUtils.randomAlphanumeric(nbreCharacters).toUpperCase());
					if(operatorDtoRequest.getValid() == null) operatorResponse.setValid(Boolean.TRUE);	
					operatorResponse.setValidfrom(new Date());
					operatorResponse.setOperatorStatus(PayPartnerStatutSubscriber.WAITING);
					operatorResponse.setUtiunitiate(operatorDtoRequest.getUtiunitiate());
					operatorResponse = operatorRepository.save(operatorResponse);

					if (operatorResponse != null) {
						datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<OperatorsResponse> updateOperator(OperatorsDto operatorDtoRequest, String typeOperation) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {					

			if (operatorDtoRequest == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(operatorDtoRequest.getId())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getCodeClient())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code client)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getClientName())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (client Name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getCustomerReferencePartner())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer Partner Reference)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getCustomerNamePartner())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer Partner Name)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getPartnerCode())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getUtiunitiate())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Initiate user)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(operatorDtoRequest.getAgenceSous())) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Agence souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if(propertyConfigService.getpartnerCodeMinitrans().equals(operatorDtoRequest.getPartnerCode())) {
				if (StringUtils.isBlank(operatorDtoRequest.getActivity())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Activité du client)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				if (StringUtils.isBlank(operatorDtoRequest.getDivisionAdministratif())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Délégation départementale de paiement)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				if (StringUtils.isBlank(operatorDtoRequest.getAdresseOperator())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Adresse du client)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
			}

			Optional<Operators> optionalOperator = operatorRepository.findById(operatorDtoRequest.getId()); 
			if (optionalOperator == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			Operators operators = optionalOperator.get();
			if("CANCEL".equals(typeOperation)) {
				if (StringUtils.isBlank(operatorDtoRequest.getUticancel())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (User cancel)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				operators = cancelOperator(operators, operatorDtoRequest);
			}
			if("VALIDATE".equals(typeOperation)) {
				if (StringUtils.isBlank(operatorDtoRequest.getUtivalidate())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Validation user)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				if (operatorDtoRequest.getUtivalidate().equals(operators.getUtimodif())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION + " (Validation user is the same that last user)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				
				Partners partner = partnerRepository.findByPartcode(operators.getPartnerCode());
									
				String keyOperator = buildKeyOperator(operators.getPartnerCode());
				int cpt = 0;
				while(StringUtils.isBlank(keyOperator) && cpt<3) {
					keyOperator = buildKeyOperator(operators.getPartnerCode());
					cpt++;
				}
				if(StringUtils.isBlank(keyOperator)) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION + " (Clé d'abonnement non générée)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				
				int nbreCharacters = partner.getSizeKey() != null ? partner.getSizeKey() : 10;
				String defaultKeyOperator = operatorDtoRequest.getPartnerCode()+RandomStringUtils.randomAlphanumeric(nbreCharacters).toUpperCase();
				
				if(propertyConfigService.getapplicationServiceDefaultPin().equals(operators.getKeyOperator()) || defaultKeyOperator.equals(operators.getKeyOperator())) {
					operators.setKeyOperator(keyOperator);
				}
				
				if(propertyConfigService.getpartnerCodeTrustPayway().equals(operators.getPartnerCode())) {
					// Envoyer la clé
					try {
						String sms = AlerteServices.sendKeyOperator(propertyConfigService.getkeysecurity(), propertyConfigService.geturlSms(), keyOperator, operators, partner); 
						if(StringUtils.isBlank(sms)) {
							return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION + " (SMS sur le PIN non envoyé)", "", datas), HttpStatus.PRECONDITION_FAILED);
						}
						operators = validateOperator(operators, operatorDtoRequest);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					operators = validateOperator(operators, operatorDtoRequest);
				}				
			}
			if("UPDATE".equals(typeOperation)) {
				if (StringUtils.isBlank(operatorDtoRequest.getUtimodif())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (User modification)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				operators = majOperator(operators, operatorDtoRequest);
			}
			if("RESETPIN".equals(typeOperation)) {
				if (StringUtils.isBlank(operatorDtoRequest.getUtimodif())) {
					return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (User modification)", "", datas), HttpStatus.PRECONDITION_FAILED);
				}
				operators = resetPinOperator(operators, operatorDtoRequest);
			}

			/* ***
			List<OperatorPhones> phones = new ArrayList<OperatorPhones>();	
			for(String val : operatorDtoRequest.getTxtPhones() )phones.add(new OperatorPhones(operators,val));
			operatorDtoRequest.setPhones(phones);
			List<OperatorAccounts> accounts = new ArrayList<OperatorAccounts>();
			for(String val : operatorDtoRequest.getTxtAccounts())accounts.add(new OperatorAccounts(operators,val));
			operatorDtoRequest.setAccounts(accounts);
			 */

			operators = operatorRepository.save(operators);
			if (operators != null) {
				List<OperatorsDto> parts = new ArrayList<OperatorsDto>();
				parts.add(ObjectMapperUtils.mapOperatorToOperatorDto(operators));
				datas = parts;
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public Operators validateOperator(Operators operators, OperatorsDto operatorDtoRequest){
		operators.setValid(Boolean.TRUE);
		operators.setUtivalidate(operatorDtoRequest.getUtivalidate());
		operators.setNameUtivalidate(operatorDtoRequest.getNameUtivalidate());
		operators.setDatevalidate(new Date());
		operators.setOperatorStatus(PayPartnerStatutSubscriber.ACTIF);
		operators.setValidTo(new Date());
		
		return operators;
	}

	

	public Operators cancelOperator(Operators operators, OperatorsDto operatorDtoRequest){
		operators.setValid(Boolean.FALSE);
		operators.setUticancel(operatorDtoRequest.getUticancel());
		operators.setNameUticancel(operatorDtoRequest.getNameUticancel());
		operators.setDatecancel(new Date());
		operators.setOperatorStatus(PayPartnerStatutSubscriber.CANCEL);
		operators.setValidTo(new Date());		
		
		return operators;
	}
	
	
	public Operators resetPinOperator(Operators operators, OperatorsDto operatorDtoRequest){
		operators.setValid(Boolean.FALSE);
		operators.setKeyOperator("PINPAYPARTNER");
		operators.setOperatorStatus(PayPartnerStatutSubscriber.WAITING);
		operators.setValidTo(new Date());
		operators.setUtimodif(operatorDtoRequest.getUtimodif());
		operators.setNameUtimodif(operatorDtoRequest.getNameUtimodif());
		
		return operators;
	}


	public Operators majOperator(Operators operators, OperatorsDto operatorDtoRequest){
		operators.setValid(operatorDtoRequest.getValid() != null ? operatorDtoRequest.getValid() : Boolean.TRUE);	
		if(StringUtils.isNotBlank(operatorDtoRequest.getAccounts())) operators.setAccounts(operatorDtoRequest.getAccounts());
		if(StringUtils.isNotBlank(operatorDtoRequest.getActivity())) operators.setActivity(operatorDtoRequest.getActivity());
		if(StringUtils.isNotBlank(operatorDtoRequest.getAdresseOperator())) operators.setAdresseOperator(operatorDtoRequest.getAdresseOperator());
		if(StringUtils.isNotBlank(operatorDtoRequest.getAgenceSous())) operators.setAgenceSous(operatorDtoRequest.getAgenceSous());
		if(StringUtils.isNotBlank(operatorDtoRequest.getLibelleAgenceSous())) operators.setLibelleAgenceSous(operatorDtoRequest.getLibelleAgenceSous());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCni())) operators.setCni(operatorDtoRequest.getCni());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerCniPartner())) operators.setCustomerCniPartner(operatorDtoRequest.getCustomerCniPartner());	
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerNamePartner())) operators.setCustomerNamePartner(operatorDtoRequest.getCustomerNamePartner());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerNiuPartner())) operators.setCustomerNiuPartner(operatorDtoRequest.getCustomerNiuPartner());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerAddress())) operators.setCustomerAddress(operatorDtoRequest.getCustomerAddress());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerRegion())) operators.setCustomerRegion(operatorDtoRequest.getCustomerRegion());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerCountry())) operators.setCustomerCountry(operatorDtoRequest.getCustomerCountry());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerPhonePartner())) operators.setCustomerPhonePartner(operatorDtoRequest.getCustomerPhonePartner());
		if(StringUtils.isNotBlank(operatorDtoRequest.getCustomerReferencePartner())) operators.setCustomerReferencePartner(operatorDtoRequest.getCustomerReferencePartner());
		if(StringUtils.isNotBlank(operatorDtoRequest.getDivisionAdministratif())) operators.setDivisionAdministratif(operatorDtoRequest.getDivisionAdministratif());
		if(StringUtils.isNotBlank(operatorDtoRequest.getEmails())) operators.setEmails(operatorDtoRequest.getEmails());
		if(StringUtils.isNotBlank(operatorDtoRequest.getPartnerCode())) operators.setPartnerCode(operatorDtoRequest.getPartnerCode());
		if(StringUtils.isNotBlank(operatorDtoRequest.getPartnerName())) operators.setPartnerName(operatorDtoRequest.getPartnerName());
		if(StringUtils.isNotBlank(operatorDtoRequest.getPhones())) operators.setPhones(operatorDtoRequest.getPhones());
		
		operators.setOperatorStatus(PayPartnerStatutSubscriber.WAITING);
		operators.setValidTo(new Date());
		operators.setUtimodif(operatorDtoRequest.getUtimodif());
		operators.setNameUtimodif(operatorDtoRequest.getNameUtimodif());
		return operators;
	}

	
	private String buildKeyOperator(String partnerCode){
		logger.info("BUILDING OTP ");
		
		// Generation OTP
		String otp = "";
		try {
			Partners partner = partnerRepository.findByPartcode(partnerCode);
			otp = ToolsUtils.generateToken(partner.getSizeKey(), partner.getTypekey());
		} catch (Exception e) {
			otp="";
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		logger.info("BUILDING OTP OK : " + otp);
		return otp;
	}
	


	@Override
	public ResponseEntity<OperatorsResponse> deleteOperator(String id) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			operatorRepository.deleteById(id);
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByIdOperator(String id) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<Operators> optionalOperator = operatorRepository.findById(id);
			if (optionalOperator != null) {
				Operators operatorResponse = optionalOperator.get();
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> getAllOperators() {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			List<Operators> operators = operatorRepository.findAll();
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<OperatorsResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Operators operatorResponse = operatorRepository.findByCode(code);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByCodeAndValid(String code, Boolean valid) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (partcode)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (valid == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (actif)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Operators operatorResponse = operatorRepository.findByCodeAndValid(code, valid);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByCodeClient(String codeClient) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			List<Operators> operators = operatorRepository.findByCodeClient(codeClient);
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByClientNameLike(String clientName) {

		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			List<Operators> operators = operatorRepository.findByClientNameLike((new StringBuilder()).append("%").append(clientName).append("%").toString());
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<OperatorsResponse> findByCustomerNiuPartner(String customerNiuPartner) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(customerNiuPartner)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer NIU)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			List<Operators> operators = operatorRepository.findByCustomerNiuPartner(customerNiuPartner);
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByCustomerReferencePartnerAndPartnerCode(String customerReferencePartner, String partnerCode) {
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(customerReferencePartner)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (customer Partner Reference)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}			

			Operators operatorResponse = operatorRepository.findByCustomerReferencePartnerAndPartnerCode(customerReferencePartner, partnerCode);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByPartnerCode(String partnerCode){
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}		
			List<Operators> operators = operatorRepository.findByPartnerCodeOrderByValidfromDesc(partnerCode);
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByPartnerCodeAndCodeClient(String partnerCode, String codeClient){
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner Code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(codeClient)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Matricule client)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			Operators operatorResponse = operatorRepository.findByPartnerCodeAndCodeClient(partnerCode, codeClient);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<OperatorsResponse> findByOperatorStatusAndAgenceSous(PayPartnerStatutSubscriber operatorStatus, String agenceSous){
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (operatorStatus == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Statut souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(agenceSous)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Agence souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			List<Operators> operators = operatorRepository.findByOperatorStatusAndAgenceSous(operatorStatus, agenceSous);
			if (operators != null && !CollectionUtils.isEmpty(operators)) {
				datas = operators.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByKeyOperatorAndPartnerCode(String keyOperator, String partnerCode){
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(keyOperator)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (clé souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			Operators operatorResponse = operatorRepository.findByKeyOperatorAndPartnerCode(keyOperator, partnerCode);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}



	@Override
	public ResponseEntity<OperatorsResponse> findByKeyOperator(String keyOperator){
		// TODO Auto-generated method stub
		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {
			if (StringUtils.isBlank(keyOperator)) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (clé souscription)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			Operators operatorResponse = operatorRepository.findByKeyOperator(keyOperator);
			if (operatorResponse != null) {
				datas.add(ObjectMapperUtils.mapOperatorToOperatorDto(operatorResponse));
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseEntity<OperatorsResponse> findOperator(OperatorsDto operatorDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Operators> cq = cb.createQuery(Operators.class);

		Root<Operators> operators = cq.from(Operators.class);
		List<Predicate> predicates = new ArrayList<>();

		List<OperatorsDto> datas = new ArrayList<OperatorsDto>();

		try {			
			if (operatorDtoRequest == null) {
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			if (StringUtils.isNotBlank(operatorDtoRequest.getCode())) {
				predicates.add(cb.equal(operators.get("code"), operatorDtoRequest.getCode()));
			}			
			if (StringUtils.isNotBlank(operatorDtoRequest.getClientName())) {
				predicates.add(cb.like(operators.get("clientName"), "%" + operatorDtoRequest.getClientName() + "%"));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerReferencePartner())) {
				predicates.add(cb.equal(operators.get("customerReferencePartner"), operatorDtoRequest.getCustomerReferencePartner()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerNamePartner())) {
				predicates.add(cb.like(operators.get("customerNamePartner"), "%" + operatorDtoRequest.getCustomerNamePartner() + "%"));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerNiuPartner())) {
				predicates.add(cb.equal(operators.get("customerNiuPartner"), operatorDtoRequest.getCustomerNiuPartner()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerCniPartner())) {
				predicates.add(cb.equal(operators.get("customerCniPartner"), operatorDtoRequest.getCustomerCniPartner()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getPartnerCode())) {
				predicates.add(cb.equal(operators.get("partnerCode"), operatorDtoRequest.getPartnerCode()));
			}			
			if (StringUtils.isNotBlank(operatorDtoRequest.getPartnerName())) {
				predicates.add(cb.like(operators.get("partnerName"), "%" + operatorDtoRequest.getPartnerName() + "%"));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getAgenceSous())) {
				predicates.add(cb.equal(operators.get("agenceSous"), operatorDtoRequest.getAgenceSous()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getUtiunitiate())) {
				predicates.add(cb.equal(operators.get("utiunitiate"), operatorDtoRequest.getUtiunitiate()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getUtivalidate())) {
				predicates.add(cb.equal(operators.get("utivalidate"), operatorDtoRequest.getUtivalidate()));
			}
			if (StringUtils.isNotBlank(operatorDtoRequest.getUticancel())) {
				predicates.add(cb.equal(operators.get("uticancel"), operatorDtoRequest.getUticancel()));
			}
			if (operatorDtoRequest.getOperatorStatus() != null) {
				predicates.add(cb.equal(operators.get("operatorStatus"), operatorDtoRequest.getOperatorStatus()));
			}
			if (operatorDtoRequest.getValid() != null) {
				predicates.add(cb.equal(operators.get("valid"), operatorDtoRequest.getValid()));
			}


			try {
				if(operatorDtoRequest.getValidFrom() != null && operatorDtoRequest.getValidTo() != null){
					Date dateDebParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(operatorDtoRequest.getValidFrom()) + " 00:00:00", "dd-MM-yyyy hh:mm:ss");
					Date dateFinParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(operatorDtoRequest.getValidTo()) + " 23:59:59", "dd-MM-yyyy hh:mm:ss");
					predicates.add(cb.between(operators.get("validfrom"), dateDebParse, dateFinParse));
				}
			} catch (Exception e) {
			}

			cq.where(predicates.toArray(new Predicate[0]));

			List<Order> orderList = new ArrayList();			
			orderList.add(cb.desc(operators.get("validfrom")));
			orderList.add(cb.desc(operators.get("clientName")));
			cq.orderBy(orderList);

			List<Operators> operatorsResponse = entityManager.createQuery(cq).getResultList();
			if (operatorsResponse != null && !CollectionUtils.isEmpty(operatorsResponse)) {
				datas = operatorsResponse.stream().map(response -> {
					return ObjectMapperUtils.mapOperatorToOperatorDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OperatorsResponse>(new OperatorsResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<DataResponseDTO> editBordereauSouscription(OperatorsDto operator){
		String response = "";
		try {
			if (operator == null) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.PRECONDITION_FAILED);
			}
			response = getBordereauSouscription(operator);
			if (StringUtils.isNotBlank(response)) {
				return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", response), HttpStatus.OK);
			}
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", response), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<DataResponseDTO>(new DataResponseDTO(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), response), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	public String getBordereauSouscription(OperatorsDto operatorDto) {		
		String result = "";

		try {
			if(StringUtils.isBlank(operatorDto.getId())) return result;

			Operators operator = null;
			Optional<Operators> operatorOpt = operatorRepository.findById(operatorDto.getId());	
			if(operatorOpt.isPresent()) {
				operator = operatorOpt.get();
			}
			if(operator == null) return result;

			List<Operators> operators = new ArrayList<Operators>();
			Partners partner = partnerRepository.findByPartcode(operator.getPartnerCode());
			operators.add(operator);

			//Recherche des redevances
			DecimalFormat df = new DecimalFormat("#,###");
			String forfaits = "";
			String Newligne=System.getProperty("line.separator");
			List<ForfaitPayments> forfaitPayments = forfaitPaymentRepository.findByPartnerCodeAndActifOrderByLibelleAsc(operator.getPartnerCode(), Boolean.TRUE);
			if(!forfaitPayments.isEmpty()) {
				for(ForfaitPayments f : forfaitPayments) {
					forfaits = StringUtils.isBlank(forfaits) ? " >>>  " + f.getCode().toUpperCase() + "  ----- " + f.getLibelle().toUpperCase() + "  ----- " + df.format(f.getAmount()) + " XAF" : forfaits + Newligne + Newligne + " >>>  " + f.getCode().toUpperCase() + "  ----- " + f.getLibelle().toUpperCase() + "  ----- " + df.format(f.getAmount()) + " XAF";
				}
			}

			//Generation de l'etat PDF
			ParameterJasper parameterJasper = new ParameterJasper(); 
			if(propertyConfigService.getpartnerCodeMinitrans().equals(operatorDto.getPartnerCode())) {
				parameterJasper.setReportName("recuSouscriptionPayPartner.jasper");
			}
			else {
				parameterJasper.setReportName("recuSouscriptionPayPartnerFirst.jasper");
			}
			
			parameterJasper.setMaCollection(operators);
			
			List<ObjectFile> objects = new ArrayList<ObjectFile>();
			objects.add(new ObjectFile("IMAGE_AFB", "AFRILAND.jpg"));
			objects.add(new ObjectFile("IMAGE_PARTNER", operator.getPartnerCode()+".png"));
			objects.add(new ObjectFile("IMAGE_INTRA", "AFB_INTRA.png"));
			objects.add(new ObjectFile("codeUser", operator.getUtiunitiate()));
			objects.add(new ObjectFile("accountPartner", partner.getAccount()));
			objects.add(new ObjectFile("forfaits", forfaits));
			objects.add(new ObjectFile("validfromString", operator.getValidfrom() != null ? new SimpleDateFormat("dd/MM/yyyy").format(operator.getValidfrom()) : new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			objects.add(new ObjectFile("validToString", operator.getValidTo() != null ? new SimpleDateFormat("dd/MM/yyyy").format(operator.getValidTo()) : new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
			parameterJasper.setObjects(objects);

			// TODO Auto-generated method stub
			//Construction de l'etat PDF	
			try {

				RestTemplate restTemplate = new RestTemplate();
				//*** final String uri = servicesystemUrl() + "/rest/api/generictool/editdocumentbyte";
				final String uri = "http://192.168.11.75:9001/system-service/rest/api/generictool/editdocumentbyte";
				byte[] bytes = restTemplate.postForObject(uri, parameterJasper, byte[].class); 
				if(bytes != null) return Base64.getEncoder().encodeToString(bytes);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result;

		} catch (Exception e) {
			return "";
		}

	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Operators> findOperatorGeneric(OperatorsDto operatorDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Operators> cq = cb.createQuery(Operators.class);

		Root<Operators> operators = cq.from(Operators.class);
		List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.isNotBlank(operatorDtoRequest.getCode())) {
			predicates.add(cb.equal(operators.get("code"), operatorDtoRequest.getCode()));
		}			
		if (StringUtils.isNotBlank(operatorDtoRequest.getClientName())) {
			predicates.add(cb.like(operators.get("clientName"), "%" + operatorDtoRequest.getClientName() + "%"));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerReferencePartner())) {
			predicates.add(cb.equal(operators.get("customerReferencePartner"), operatorDtoRequest.getCustomerReferencePartner()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerNamePartner())) {
			predicates.add(cb.like(operators.get("customerNamePartner"), "%" + operatorDtoRequest.getCustomerNamePartner() + "%"));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerNiuPartner())) {
			predicates.add(cb.equal(operators.get("customerNiuPartner"), operatorDtoRequest.getCustomerNiuPartner()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getCustomerCniPartner())) {
			predicates.add(cb.equal(operators.get("customerCniPartner"), operatorDtoRequest.getCustomerCniPartner()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getPartnerCode())) {
			predicates.add(cb.equal(operators.get("partnerCode"), operatorDtoRequest.getPartnerCode()));
		}			
		if (StringUtils.isNotBlank(operatorDtoRequest.getPartnerName())) {
			predicates.add(cb.like(operators.get("partnerName"), "%" + operatorDtoRequest.getPartnerName() + "%"));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getAgenceSous())) {
			predicates.add(cb.equal(operators.get("agenceSous"), operatorDtoRequest.getAgenceSous()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getUtiunitiate())) {
			predicates.add(cb.equal(operators.get("utiunitiate"), operatorDtoRequest.getUtiunitiate()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getUtivalidate())) {
			predicates.add(cb.equal(operators.get("utivalidate"), operatorDtoRequest.getUtivalidate()));
		}
		if (StringUtils.isNotBlank(operatorDtoRequest.getUticancel())) {
			predicates.add(cb.equal(operators.get("uticancel"), operatorDtoRequest.getUticancel()));
		}
		if (operatorDtoRequest.getOperatorStatus() != null) {
			predicates.add(cb.equal(operators.get("operatorStatus"), operatorDtoRequest.getOperatorStatus()));
		}
		if (operatorDtoRequest.getValid() != null) {
			predicates.add(cb.equal(operators.get("valid"), operatorDtoRequest.getValid()));
		}


		try {
			if(operatorDtoRequest.getValidFrom() != null && operatorDtoRequest.getValidTo() != null){
				Date dateDebParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(operatorDtoRequest.getValidFrom()) + " 00:00:00", "dd-MM-yyyy hh:mm:ss");
				Date dateFinParse = DateUtils.parseDate(new SimpleDateFormat("dd-MM-yyyy").format(operatorDtoRequest.getValidTo()) + " 23:59:59", "dd-MM-yyyy hh:mm:ss");
				predicates.add(cb.between(operators.get("validFrom"), dateDebParse, dateFinParse));
			}
		} catch (Exception e) {
		}

		cq.where(predicates.toArray(new Predicate[0]));

		List<Order> orderList = new ArrayList();			
		orderList.add(cb.desc(operators.get("validFrom")));
		orderList.add(cb.desc(operators.get("clientName")));
		cq.orderBy(orderList);

		return entityManager.createQuery(cq).getResultList();

	}



	/*
	public URI servicesystemUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("servicesystem-service");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri();
		}
		return null;
	}


	public URI serviceDataSystemUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("datasystem-service");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri();
		}
		return null;
	}
	 */


}
