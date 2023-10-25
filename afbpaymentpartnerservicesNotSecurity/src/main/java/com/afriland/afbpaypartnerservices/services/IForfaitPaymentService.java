package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.ForfaitPaymentsDto;
import com.afriland.afbpaypartnerservices.response.ForfaitPaymentsResponse;


public interface IForfaitPaymentService {
	
	public ResponseEntity<ForfaitPaymentsResponse> saveForfaitPayment(ForfaitPaymentsDto forfaitPaymentsDto);
	
	public ResponseEntity<ForfaitPaymentsResponse> saveListForfaitPayments(List<ForfaitPaymentsDto> forfaitPaymentsDto);

	public ResponseEntity<ForfaitPaymentsResponse> updateForfaitPayment(ForfaitPaymentsDto forfaitPaymentsDto);
	
	public ResponseEntity<ForfaitPaymentsResponse> deleteForfaitPayment(String id);
	
	public ResponseEntity<ForfaitPaymentsResponse> findByCode(String code);	
	
	public ResponseEntity<ForfaitPaymentsResponse> findByLibelleLike(String libelle);
	
	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCode(String partnerCode);

	public ResponseEntity<ForfaitPaymentsResponse> getAllForfaitPayments();
	
	public ResponseEntity<ForfaitPaymentsResponse> findByIdForfaitPayment(String id);	
	
	public ResponseEntity<ForfaitPaymentsResponse> findByTypeForfait(String typeForfait);
	
	public ResponseEntity<ForfaitPaymentsResponse> findByPartnerCodeAndTypeForfait(String partnerCode, String typeForfait);
	
}
