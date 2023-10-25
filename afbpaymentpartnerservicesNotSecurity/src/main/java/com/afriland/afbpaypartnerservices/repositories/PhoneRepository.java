package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.OperatorPhones;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface PhoneRepository extends JpaRepository<OperatorPhones,String>{
	
	@Query("SELECT a FROM OperatorPhones a WHERE a.operator =:operator")
	List<OperatorPhones> findByRessource(@Param("operator") Operators operators);

}