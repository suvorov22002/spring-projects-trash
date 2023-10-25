package com.afriland.afbpaypartnerservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.FactMonthDetails;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface FactMonthDetailRepository extends JpaRepository<FactMonthDetails,Long>{
	
}