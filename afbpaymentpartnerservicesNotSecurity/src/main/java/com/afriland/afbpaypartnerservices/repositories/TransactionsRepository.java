package com.afriland.afbpaypartnerservices.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.jpa.Transactions;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,String>{
	
	@Query("SELECT a FROM Transactions a WHERE a.accountDebit =:accountDebit AND a.amount =:amount AND a.accountCredit =:accountCredit  AND a.eveid =:eveid AND a.statusTrans =:statusTrans ")
	List<Transactions> findByTransactionsTodayOnline(@Param("accountDebit") String accountDebit,@Param("amount") Double amount,@Param("accountCredit") String accountCredit,@Param("eveid") String eveid,@Param("statusTrans") TransactionStatus statusTrans);

	@Query("SELECT a FROM Transactions a WHERE a.userCaisse =:userCaisse AND a.statusTrans in (:statusTrans) AND a.validfrom between :validfrom AND :validTo ORDER BY a.validfrom ASC")
	List<Transactions> findByTransactionsTodayBbyuserCaisse(@Param("userCaisse") String userCaisse,@Param("statusTrans") List<TransactionStatus> statusTrans,@Param("validfrom") Date validfrom ,@Param("validTo") Date validTo);
	
	@Query("SELECT a FROM Transactions a WHERE a.statusTrans in (:statusTrans) ORDER BY a.validfrom DESC")
	List<Transactions> findByPendingTransactions(@Param("statusTrans") List<TransactionStatus> statusTrans);
	
	@Query("FROM Transactions")
    List<Transactions> findAllOrderByNameAsc(Sort sort);
		
	@Query("SELECT a FROM Transactions a WHERE a.userCaisse =:userCaisse AND a.statusTrans in (:statusTrans) AND a.validfrom between :validfrom AND :validTo ORDER BY a.validfrom ASC")
	List<Transactions> find(@Param("userCaisse") String userCaisse,@Param("statusTrans") List<TransactionStatus> statusTrans,@Param("validfrom") Date validfrom ,@Param("validTo") Date validTo);
	
	public List<Transactions> findByPartnerCodeOrderByValidfromDesc(String partnerCode);
	
	public List<Transactions> findByPartnerTrxIDAndPartnerCode(String partnerTrxID, String partnerCode);
	
	Page<Transactions> findByPartnerCodeAndStatusTransOrderByValidfromDesc(String partnerCode, TransactionStatus statusTrans, Pageable pageable);
	
	public List<Transactions> findByPartnerCodeAndPostCompleteAndStatusTrans(String partnerCode, Boolean postComplete, TransactionStatus statusTrans);
	
	@Query("SELECT a FROM Transactions a WHERE a.ope =:ope AND a.referenceOperator =:referenceOperator AND a.partnerCode =:partnerCode AND a.statusTrans in (:statusTrans) ORDER BY a.validfrom DESC")
	List<Transactions> findLastOperator(@Param("ope") String ope, @Param("referenceOperator") String referenceOperator, @Param("partnerCode") String partnerCode, @Param("statusTrans") List<TransactionStatus> statusTrans);
	
	@Query("SELECT a FROM Transactions a WHERE a.partnerCode =:partnerCode AND a.statusTrans in (:statusTrans) ORDER BY a.validfrom DESC")
	List<Transactions> findTransactionsPartner(@Param("partnerCode") String partnerCode, @Param("statusTrans") List<TransactionStatus> statusTrans);
	
}
