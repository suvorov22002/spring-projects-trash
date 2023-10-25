package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.AccountPartners;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface AccountPartnerRepository extends JpaRepository<AccountPartners,String>{
	
	public AccountPartners findByCode(String code);
	
	public AccountPartners findByCodeAndActif(String code, boolean actif);
	
	public List<AccountPartners> findByNcp(String ncp);
	
	public List<AccountPartners> findByAccountLike(String account);
	
	public List<AccountPartners> findByLibelleLikeOrderByLibelleAsc(String libelle);

	public List<AccountPartners> findByPartnerCodeAndActifOrderByLibelleAsc(String partnerCode, boolean actif);
		
}