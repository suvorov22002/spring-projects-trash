package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.ForfaitPayments;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface ForfaitPaymentRepository extends JpaRepository<ForfaitPayments,String>{
	
	public ForfaitPayments findByCode(String code);
	
	public List<ForfaitPayments> findByLibelleLikeOrderByLibelleAsc(String libelle);

	public List<ForfaitPayments> findByPartnerCodeAndActifOrderByLibelleAsc(String partnerCode, boolean actif);
	
	public List<ForfaitPayments> findByPartnerCodeAndTypeForfaitAndActifOrderByLibelleAsc(String partnerCode, String typeForfait, boolean actif);
	
	public List<ForfaitPayments> findByTypeForfaitAndActifOrderByLibelleAsc(String typeForfait, boolean actif);
		
}