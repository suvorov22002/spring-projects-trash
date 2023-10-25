package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.ForfaitClasses;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface ForfaitClasseRepository extends JpaRepository<ForfaitClasses,String>{
	
	public ForfaitClasses findByCode(String code);
	
	public List<ForfaitClasses> findByLibelleLikeOrderByLibelleAsc(String libelle);

	public List<ForfaitClasses> findByPartnerCodeAndActifOrderByLibelleAsc(String partnerCode, boolean actif);
	
	public List<ForfaitClasses> findByPartnerCodeAndTypeForfaitAndActifOrderByLibelleAsc(String partnerCode, String typeForfait, boolean actif);
	
	public List<ForfaitClasses> findByTypeForfaitAndActifOrderByLibelleAsc(String typeForfait, boolean actif);
		
}