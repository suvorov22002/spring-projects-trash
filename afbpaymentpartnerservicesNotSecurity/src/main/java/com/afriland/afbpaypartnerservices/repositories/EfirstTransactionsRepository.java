package com.afriland.afbpaypartnerservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.jpa.EfirstTransactions;

/**
 * 
 * @author yves_labo
 *
 */
@Repository
public interface EfirstTransactionsRepository extends JpaRepository<EfirstTransactions,String>{
	
	@Query("SELECT a FROM EfirstTransactions a WHERE a.nord =:nord and a.deva =:deva and a.cpro =:cpro and a.ncp2 =:ncp2 and a.ncp1 =:ncp1 and a.mnt1 =:mnt1 and a.dreq =:dreq and a.dope =:dope")
	List<EfirstTransactions> findByRessourceValue(@Param("nord") Double nord,@Param("deva") String deva,@Param("cpro") String cpro,@Param("ncp2") String ncp2
			,@Param("ncp1") String ncp1,@Param("mnt1") Double mnt1,@Param("dreq") String dreq,@Param("dope") String dope);

}
