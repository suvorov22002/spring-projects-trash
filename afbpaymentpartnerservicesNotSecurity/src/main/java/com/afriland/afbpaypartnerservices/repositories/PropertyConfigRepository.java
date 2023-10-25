package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.PropertyConfigs;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface PropertyConfigRepository extends JpaRepository<PropertyConfigs,String>{
	
	public PropertyConfigs findByCode(String code);
	
	public List<PropertyConfigs> findByDescriptionLike(String description);
	
}