package com.afriland.afbpaypartnerservices.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.afriland.afbpaypartnerservices.dtos.OperatorsDto;
import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.response.DataResponseDTO;
import com.afriland.afbpaypartnerservices.response.OperatorsResponse;


public interface IOperatorService {

	public ResponseEntity<OperatorsResponse> saveOperator(OperatorsDto operatorDTORequest);
	
	public ResponseEntity<OperatorsResponse> saveListOperators(List<OperatorsDto> operatorDTORequests);

	public ResponseEntity<OperatorsResponse> updateOperator(OperatorsDto operatorDTORequest, String typeOperation);

	public ResponseEntity<OperatorsResponse> deleteOperator(String id);
	
	public ResponseEntity<OperatorsResponse> findByIdOperator(String id);
	
	public ResponseEntity<OperatorsResponse> getAllOperators();
	
	public ResponseEntity<OperatorsResponse> findByCode(String code);
	
	public ResponseEntity<OperatorsResponse> findByCodeAndValid(String code, Boolean valid);
	
	public ResponseEntity<OperatorsResponse> findByCodeClient(String codeClient);
	
	public ResponseEntity<OperatorsResponse> findByClientNameLike(String clientName);
	
	public ResponseEntity<OperatorsResponse> findByCustomerNiuPartner(String customerNiuPartner);
	
	public ResponseEntity<OperatorsResponse> findByCustomerReferencePartnerAndPartnerCode(String customerReferencePartner, String partnerCode);
	
	public ResponseEntity<OperatorsResponse> findByOperatorStatusAndAgenceSous(PayPartnerStatutSubscriber operatorStatus, String agenceSous);
	
	public ResponseEntity<OperatorsResponse> findByPartnerCode(String partnerCode);
	
	public ResponseEntity<OperatorsResponse> findByPartnerCodeAndCodeClient(String partnerCode, String codeClient);
	
	public ResponseEntity<OperatorsResponse> findByKeyOperatorAndPartnerCode(String keyOperator, String partnerCode);
	
	public ResponseEntity<OperatorsResponse> findByKeyOperator(String keyOperator);
	
	public ResponseEntity<OperatorsResponse> findOperator(OperatorsDto operatorDTORequest);		
	
	public ResponseEntity<DataResponseDTO> editBordereauSouscription(OperatorsDto operator);	
		
}
