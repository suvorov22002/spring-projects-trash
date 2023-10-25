package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.Operators;
import com.afriland.afbpaypartnerservices.jpa.OperatorAccounts;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<OperatorAccounts,Long>{
	
	@Query("SELECT a FROM OperatorAccounts a WHERE a.operator =:operator")
	List<OperatorAccounts> findByRessource(@Param("operator") Operators operators);
	
	@Query("SELECT a FROM OperatorAccounts a WHERE a.datavalue like :datavalue")
	List<OperatorAccounts> findByRessourceValue(@Param("datavalue") String datavalue);

}