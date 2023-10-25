package com.afriland.afbpaypartnerservices.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

import com.afriland.afbpaypartnerservices.dtos.AccountPartnersDto;
import com.afriland.afbpaypartnerservices.dtos.Bkcom;
import com.afriland.afbpaypartnerservices.dtos.ResponseBkcom;
import com.afriland.afbpaypartnerservices.enums.MessageResponse;
import com.afriland.afbpaypartnerservices.externservices.CoreBankingServices;
import com.afriland.afbpaypartnerservices.jpa.AccountPartners;
import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;
import com.afriland.afbpaypartnerservices.repositories.AccountPartnerRepository;
import com.afriland.afbpaypartnerservices.repositories.PropertyConfigRepository;
import com.afriland.afbpaypartnerservices.response.AccountPartnersResponse;
import com.afriland.afbpaypartnerservices.services.IAccountPartnerService;
import com.afriland.afbpaypartnerservices.utils.ObjectMapperUtils;



@Service("accountpartnerservice")
@Transactional
public class AccountPartnerServices implements IAccountPartnerService {

	@Autowired
	private AccountPartnerRepository accountPartnerRepository;
	
	@Autowired
	private PropertyConfigRepository propertyConfigRepository;


	@PersistenceContext
	private EntityManager entityManager;
	
	@Value("${application.afriland.cbsservice.baseurl}")
	private String host;
	
	@Value("${application.afriland.cbsservice.keysecurity}")
	private String keysecurity;


	Logger logger = LoggerFactory.getLogger(AccountPartnerServices.class);
	
	
	public String getValue(String code){
		Optional<PropertyConfigs> mers = propertyConfigRepository.findById(code);
		PropertyConfigs mer = null ; 
		if(mers.isPresent()){
			mer = mers.get();
			return mer.getValue();
		}
		return null;
	}


	public String gethost(){
		return getValue(host);
	}

	public String getkeysecurity(){
		return getValue(keysecurity);
	}

	
	
	@PostConstruct
	private void init(){
		host = gethost();
		keysecurity = getkeysecurity();					
	}
	
	

	@Override
	public ResponseEntity<AccountPartnersResponse> saveAccountPartner(AccountPartnersDto accountPartnerDtoRequest) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (accountPartnerDtoRequest == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(accountPartnerDtoRequest.getCode())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getLibelle())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getAccount())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Account partner)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getPartnerCode())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
						
			ResponseBkcom res = CoreBankingServices.getaccountinfo(keysecurity, host, accountPartnerDtoRequest.getAccount().split("-")[0], accountPartnerDtoRequest.getAccount().split("-")[1]);
			if(res == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET + " (Account partner)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			Bkcom bkcom =  res.getData().isEmpty() ? null : res.getData().get(0);
			if(bkcom == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.InvalidAccount + " (Account partner)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			
			AccountPartners existingAccountPartner = accountPartnerRepository.findByCode(accountPartnerDtoRequest.getCode()); 
			if (existingAccountPartner != null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT.getReasonPhrase() + " : " + MessageResponse.ELEMENT_ALREADY_EXIST, "", datas), HttpStatus.CONFLICT);
			}

			AccountPartners accountPartnerResponse = ObjectMapperUtils.mapAccountPartnersDtoToAccountPartners(accountPartnerDtoRequest);
			accountPartnerResponse.setNcp(accountPartnerDtoRequest.getAccount().split("-")[1]);
			if(accountPartnerDtoRequest.getActif() == null) accountPartnerResponse.setActif(Boolean.TRUE);
			accountPartnerResponse = accountPartnerRepository.save(accountPartnerResponse);
			if (accountPartnerResponse != null) {
				datas.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartnerResponse));
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> saveListAccountPartners(List<AccountPartnersDto> accountPartnerDtoRequests) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			for(AccountPartnersDto accountPartnerDtoRequest : accountPartnerDtoRequests) {
				try {
					if (accountPartnerDtoRequest == null) {
						break;
					}			
					if (StringUtils.isBlank(accountPartnerDtoRequest.getCode())) {
						break;
					}
					if (StringUtils.isBlank(accountPartnerDtoRequest.getLibelle())) {
						break;
					}
					if (StringUtils.isBlank(accountPartnerDtoRequest.getAccount())) {
						break;
					}
					if (StringUtils.isBlank(accountPartnerDtoRequest.getPartnerCode())) {
						break;
					}

					AccountPartners existingAccountPartner = accountPartnerRepository.findByCode(accountPartnerDtoRequest.getCode()); 
					if (existingAccountPartner != null) {
						break;
					}

					AccountPartners accountPartnerResponse = ObjectMapperUtils.mapAccountPartnersDtoToAccountPartners(accountPartnerDtoRequest);
					accountPartnerResponse.setNcp(accountPartnerDtoRequest.getAccount().split("-")[1]);
					if(accountPartnerDtoRequest.getActif() == null) accountPartnerResponse.setActif(Boolean.TRUE);
					accountPartnerResponse = accountPartnerRepository.save(accountPartnerResponse);

					if (accountPartnerResponse != null) {
						datas.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartnerResponse));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					break;
				}
			}

			if(!datas.isEmpty()) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	@Override
	public ResponseEntity<AccountPartnersResponse> updateAccountPartner(AccountPartnersDto accountPartnerDtoRequest) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {					
			logger.info("************** updateAccountPartner ***************");
			
			if (accountPartnerDtoRequest == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.PRECONDITION_FAILED);
			}			
			if (StringUtils.isBlank(accountPartnerDtoRequest.getId())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + "(Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getCode())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getLibelle())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Libellé)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getAccount())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Account partner)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			if (StringUtils.isBlank(accountPartnerDtoRequest.getPartnerCode())) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			Optional<AccountPartners> optionalAccountPartner = accountPartnerRepository.findById(accountPartnerDtoRequest.getId()); 
			if (optionalAccountPartner == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase() + " : " + MessageResponse.ELEMENT_DOESNT_EXIST, "", datas), HttpStatus.NOT_FOUND);
			}
			AccountPartners accountPartners = optionalAccountPartner.get();
			if(StringUtils.isNotBlank(accountPartnerDtoRequest.getLibelle())) accountPartners.setLibelle(accountPartnerDtoRequest.getLibelle());
			if(StringUtils.isNotBlank(accountPartnerDtoRequest.getAccount())) {
				accountPartners.setAccount(accountPartnerDtoRequest.getAccount());
				accountPartners.setNcp(accountPartnerDtoRequest.getAccount().split("-")[1]);
			}
			if(accountPartnerDtoRequest.getActif() != null) accountPartners.setActif(accountPartnerDtoRequest.getActif());
			
			accountPartners = accountPartnerRepository.save(accountPartners);
			if (accountPartners != null) {
				List<AccountPartnersDto> parts = new ArrayList<AccountPartnersDto>();
				parts.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartners));
				datas = parts;
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}

			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.FAILED_OPERATION, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> deleteAccountPartner(String id) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			accountPartnerRepository.deleteById(id);
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> findByIdAccountPartner(String id) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(id)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Id)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			Optional<AccountPartners> optionalAccountPartner = accountPartnerRepository.findById(id);
			if (optionalAccountPartner != null) {
				AccountPartners accountPartnerResponse = optionalAccountPartner.get();
				datas.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartnerResponse));
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> getAllAccountPartners() {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			List<AccountPartners> accountPartners = accountPartnerRepository.findAll();
			if (accountPartners != null && !CollectionUtils.isEmpty(accountPartners)) {
				datas = accountPartners.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.EMPTY_LIST, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> findByCode(String code) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			AccountPartners accountPartnerResponse = accountPartnerRepository.findByCode(code);
			if (accountPartnerResponse != null) {
				datas.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartnerResponse));
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@Override
	public ResponseEntity<AccountPartnersResponse> findByCodeAndActif(String code, Boolean actif) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(code)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}				
			if(actif == null) actif = Boolean.TRUE;

			AccountPartners accountPartnerResponse = accountPartnerRepository.findByCodeAndActif(code, actif);
			if (accountPartnerResponse != null) {
				datas.add(ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(accountPartnerResponse));
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@Override
	public ResponseEntity<AccountPartnersResponse> findByNcp(String ncp) {
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(ncp)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (ncp)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}	

			List<AccountPartners> accountPartners = accountPartnerRepository.findByNcp(ncp);
			if (accountPartners != null && !CollectionUtils.isEmpty(accountPartners)) {
				datas = accountPartners.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}			
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	


	@Override
	public ResponseEntity<AccountPartnersResponse> findByLibelleLike(String libelle){
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			List<AccountPartners> accountPartners = accountPartnerRepository.findByLibelleLikeOrderByLibelleAsc((new StringBuilder()).append("%").append(libelle).append("%").toString());
			if (accountPartners != null && !CollectionUtils.isEmpty(accountPartners)) {
				datas = accountPartners.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@Override
	public ResponseEntity<AccountPartnersResponse> findByAccountLike(String account){
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			List<AccountPartners> accountPartners = accountPartnerRepository.findByAccountLike((new StringBuilder()).append("%").append(account).append("%").toString());
			if (accountPartners != null && !CollectionUtils.isEmpty(accountPartners)) {
				datas = accountPartners.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> findByPartnerCodeAndActif(String partnerCode, Boolean actif){
		// TODO Auto-generated method stub
		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {
			if (StringUtils.isBlank(partnerCode)) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}
			
			if(actif == null) actif = Boolean.TRUE;
			List<AccountPartners> accountPartners = accountPartnerRepository.findByPartnerCodeAndActifOrderByLibelleAsc(partnerCode, actif);
			
			if (accountPartners != null && !CollectionUtils.isEmpty(accountPartners)) {
				datas = accountPartners.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);

		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	@Override
	public ResponseEntity<AccountPartnersResponse> findByAccountPartner(AccountPartnersDto accountPartnerDtoRequest) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccountPartners> cq = cb.createQuery(AccountPartners.class);

		Root<AccountPartners> accountPartners = cq.from(AccountPartners.class);
		List<Predicate> predicates = new ArrayList<>();

		List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();

		try {			
			if (accountPartnerDtoRequest == null) {
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.PRECONDITION_FAILED.value()), HttpStatus.PRECONDITION_FAILED.getReasonPhrase() + " : " + MessageResponse.NULL_PROPERTY + " (Partner code)", "", datas), HttpStatus.PRECONDITION_FAILED);
			}

			if (StringUtils.isNotBlank(accountPartnerDtoRequest.getCode())) {
				predicates.add(cb.equal(accountPartners.get("code"), accountPartnerDtoRequest.getCode()));
			}			
			if (StringUtils.isNotBlank(accountPartnerDtoRequest.getPartnerCode())) {
				predicates.add(cb.equal(accountPartners.get("partnerCode"), accountPartnerDtoRequest.getPartnerCode()));
			}			
			if (StringUtils.isNotBlank(accountPartnerDtoRequest.getLibelle())) {
				predicates.add(cb.like(accountPartners.get("libelle"), "%" + accountPartnerDtoRequest.getLibelle() + "%"));
			}
			if (StringUtils.isNotBlank(accountPartnerDtoRequest.getAccount())) {
				predicates.add(cb.equal(accountPartners.get("account"), accountPartnerDtoRequest.getAccount()));
			}			
			if (StringUtils.isNotBlank(accountPartnerDtoRequest.getNcp())) {
				predicates.add(cb.equal(accountPartners.get("ncp"), accountPartnerDtoRequest.getNcp()));
			}	
			if (accountPartnerDtoRequest.getActif() != null) {
				predicates.add(cb.equal(accountPartners.get("actif"), accountPartnerDtoRequest.getActif()));
			}

			cq.where(predicates.toArray(new Predicate[0]));

			List<AccountPartners> accountPartnersResponse = entityManager.createQuery(cq).getResultList();
			if (accountPartnersResponse != null && !CollectionUtils.isEmpty(accountPartnersResponse)) {
				datas = accountPartnersResponse.stream().map(response -> {
					return ObjectMapperUtils.mapAccountPartnersToAccountPartnersDto(response);
				}).collect(Collectors.toList());
				return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase() + " : " + MessageResponse.SUCCESSFULL_OPERATION, "", datas), HttpStatus.OK);
			}	
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.NOT_IMPLEMENTED.value()), HttpStatus.NOT_IMPLEMENTED.getReasonPhrase() + " : " + MessageResponse.NULL_OBJET, "", datas), HttpStatus.NOT_IMPLEMENTED);
		} catch (Exception e) {
			return new ResponseEntity<AccountPartnersResponse>(new AccountPartnersResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), datas), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
