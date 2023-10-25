package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.AccountPartnersDto;
import com.afriland.afbpaypartnerservices.response.AccountPartnersResponse;


public interface IAccountPartnerService {
	
	public ResponseEntity<AccountPartnersResponse> saveAccountPartner(AccountPartnersDto accountPartnersDto);
	
	public ResponseEntity<AccountPartnersResponse> saveListAccountPartners(List<AccountPartnersDto> accountPartnersDto);

	public ResponseEntity<AccountPartnersResponse> updateAccountPartner(AccountPartnersDto accountPartnersDto);
	
	public ResponseEntity<AccountPartnersResponse> deleteAccountPartner(String id);
	
	public ResponseEntity<AccountPartnersResponse> findByCodeAndActif(String code, Boolean actif);
	
	public ResponseEntity<AccountPartnersResponse> findByCode(String code);	
	
	public ResponseEntity<AccountPartnersResponse> findByNcp(String ncp);
	
	public ResponseEntity<AccountPartnersResponse> findByLibelleLike(String libelle);
	
	public ResponseEntity<AccountPartnersResponse> findByAccountLike(String account);
	
	public ResponseEntity<AccountPartnersResponse> findByPartnerCodeAndActif(String partnerCode, Boolean actif);

	public ResponseEntity<AccountPartnersResponse> getAllAccountPartners();
	
	public ResponseEntity<AccountPartnersResponse> findByIdAccountPartner(String id);	
	
	public ResponseEntity<AccountPartnersResponse> findByAccountPartner(AccountPartnersDto accountPartnersDto);
		
}
