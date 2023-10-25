package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.PartnersDto;
import com.afriland.afbpaypartnerservices.response.PartnersResponse;


public interface IPartnerService {

	public ResponseEntity<PartnersResponse> savePartner(PartnersDto partnerDTORequest);
	
	public ResponseEntity<PartnersResponse> saveListPartners(List<PartnersDto> partnerDTORequests);

	public ResponseEntity<PartnersResponse> updatePartner(PartnersDto partnerDTORequest);

	public ResponseEntity<PartnersResponse> deletePartner(String id);
	
	public ResponseEntity<PartnersResponse> findByIdPartner(String id);
	
	public ResponseEntity<PartnersResponse> getAllPartners();
	
	public ResponseEntity<PartnersResponse> findByPartcode(String partcode);
	
	public ResponseEntity<PartnersResponse> findByPartcodeAndActif(String partcode, Boolean actif);
	
	public ResponseEntity<PartnersResponse> findByPartnameLike(String partname);
	
	public ResponseEntity<PartnersResponse> findByPartcodeAndPwd(String partcode, String pwd);
	
	public ResponseEntity<PartnersResponse> findByPartcodeAndPwdAndActif(String partcode, String pwd, Boolean actif);
	
	public ResponseEntity<PartnersResponse> findPartner(PartnersDto partnerDTORequest);
		
}
