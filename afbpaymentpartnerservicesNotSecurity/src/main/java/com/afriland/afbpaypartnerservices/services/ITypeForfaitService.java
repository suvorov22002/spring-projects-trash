package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.TypeForfaitsDto;
import com.afriland.afbpaypartnerservices.enums.Segment;
import com.afriland.afbpaypartnerservices.response.TypeForfaitsResponse;


public interface ITypeForfaitService {
	
	public ResponseEntity<TypeForfaitsResponse> saveTypeForfait(TypeForfaitsDto typeForfaitsDto);
	
	public ResponseEntity<TypeForfaitsResponse> saveListTypeForfaits(List<TypeForfaitsDto> typeForfaitsDto);

	public ResponseEntity<TypeForfaitsResponse> updateTypeForfait(TypeForfaitsDto typeForfaitsDto);
	
	public ResponseEntity<TypeForfaitsResponse> deleteTypeForfait(String id);
	
	public ResponseEntity<TypeForfaitsResponse> findByCode(String code);	
	
	public ResponseEntity<TypeForfaitsResponse> findByLibelleLike(String libelle);
	
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCode(String partnerCode);

	public ResponseEntity<TypeForfaitsResponse> getAllTypeForfaits();
	
	public ResponseEntity<TypeForfaitsResponse> findByIdTypeForfait(String id);	
	
	public ResponseEntity<TypeForfaitsResponse> findBySegment(Segment segment);
	
	public ResponseEntity<TypeForfaitsResponse> findByPartnerCodeAndSegment(String partnerCode, Segment segment);
	
}
