package com.afriland.afbpaypartnerservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.FactMonth;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface FactMonthRepository extends JpaRepository<FactMonth,Long>{
	
}