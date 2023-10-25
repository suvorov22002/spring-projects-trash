package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.enums.Segment;
import com.afriland.afbpaypartnerservices.jpa.TypeForfaits;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface TypeForfaitRepository extends JpaRepository<TypeForfaits,String>{
	
	public TypeForfaits findByCode(String code);
	
	public List<TypeForfaits> findByLibelleLikeOrderByLibelleAsc(String libelle);

	public List<TypeForfaits> findByPartnerCodeAndActifOrderByLibelleAsc(String partnerCode, boolean actif);
	
	public List<TypeForfaits> findBySegmentAndActifOrderByLibelleAsc(Segment segment, boolean actif);
	
	public List<TypeForfaits> findByPartnerCodeAndSegmentOrderByLibelleAsc(String partnerCode, Segment segment);
	
}