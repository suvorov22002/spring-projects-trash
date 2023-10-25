package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.ForfaitClassesDto;
import com.afriland.afbpaypartnerservices.response.ForfaitClassesResponse;


public interface IForfaitClasseService {
	
	public ResponseEntity<ForfaitClassesResponse> saveForfaitClasse(ForfaitClassesDto forfaitClassesDto);
	
	public ResponseEntity<ForfaitClassesResponse> saveListForfaitClasses(List<ForfaitClassesDto> forfaitClassesDto);

	public ResponseEntity<ForfaitClassesResponse> updateForfaitClasse(ForfaitClassesDto forfaitClassesDto);
	
	public ResponseEntity<ForfaitClassesResponse> deleteForfaitClasse(String id);
	
	public ResponseEntity<ForfaitClassesResponse> findByCode(String code);	
	
	public ResponseEntity<ForfaitClassesResponse> findByLibelleLike(String libelle);
	
	public ResponseEntity<ForfaitClassesResponse> findByPartnerCode(String partnerCode);

	public ResponseEntity<ForfaitClassesResponse> getAllForfaitClasses();
	
	public ResponseEntity<ForfaitClassesResponse> findByIdForfaitClasse(String id);	
	
	public ResponseEntity<ForfaitClassesResponse> findByTypeForfait(String typeForfait);
	
	public ResponseEntity<ForfaitClassesResponse> findByPartnerCodeAndTypeForfait(String partnerCode, String typeForfait);
	
}
