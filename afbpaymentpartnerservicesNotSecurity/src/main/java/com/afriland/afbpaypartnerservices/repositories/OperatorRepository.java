package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.jpa.Operators;

@Repository
public interface OperatorRepository extends JpaRepository<Operators,String>{

	public Operators findByCode(String code);

	public Operators findByCodeAndValid(String code, Boolean valid);

	public List<Operators> findByCodeClient(String codeClient);

	public List<Operators> findByClientNameLike(String clientName);

	public List<Operators> findByCustomerNiuPartner(String customerNiuPartner);

	public Operators findByCustomerReferencePartnerAndPartnerCode(String customerReferencePartner, String partnerCode);
	
	public Operators findByCustomerNiuPartnerAndKeyOperatorAndPartnerCode(String customerNiuPartner, String keyOperator, String partnerCode);
	
	public Operators findByCustomerReferencePartner(String customerReferencePartner);
	
	public List<Operators> findByPartnerCodeOrderByValidfromDesc(String partnerCode);
	
	public Operators findByPartnerCodeAndCodeClient(String partnerCode, String codeClient);
	
	public List<Operators> findByOperatorStatusAndAgenceSous(PayPartnerStatutSubscriber operatorStatus, String agenceSous);
	
	public Operators findByKeyOperatorAndPartnerCode(String keyOperator, String partnerCode);
	
	public Operators findByKeyOperator(String keyOperator);

}