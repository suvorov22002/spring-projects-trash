package com.afriland.afbpaypartnerservices.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.afriland.afbpaypartnerservices.jpa.Transactions;

// FindJpaRepository
@Service
public class TransactionsFullRepository  {


	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Transactions> findDataByCriteria(Transactions cr,Date validfrom,Date validTo) {

		// Do something with the entity manager  trans.setAccountDebit
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT t FROM Transactions t WHERE ");

		if (!StringUtils.isBlank(cr.getReferenceOperator())) {
			builder.append(" t.referenceOperator =:referenceOperator "); builder.append(" AND ");
		}
		if (!StringUtils.isBlank(cr.getNomOperator())) {
			builder.append(" t.nomOperator like :nomOperator "); builder.append(" AND ");
		}
		if (!StringUtils.isBlank(cr.getAgeCaisse())) {
			builder.append(" t.ageCaisse =:ageCaisse "); builder.append(" AND ");
		}
		if (!StringUtils.isBlank(cr.getUserCaisse())) {
			builder.append(" t.userCaisse =:userCaisse "); builder.append(" AND ");
		}
		if (!StringUtils.isBlank(cr.getEveid())) {
			builder.append(" t.eveid =:eveid "); builder.append(" AND ");
		}
		if (cr.getStatusTrans() != null) {
			builder.append(" t.statusTrans =:statusTrans "); builder.append(" AND ");
		}
		if (!StringUtils.isBlank(cr.getAccountDebit())) {
			builder.append(" t.accountDebit like :accountDebit "); builder.append(" AND ");
		}
		if (cr.getAmount() != null) {
			if(cr.getAmount().compareTo(0d) > 0 ) {
				builder.append(" t.amount =:amount "); builder.append(" AND ");
			}
		}

		builder.append(" t.validfrom between :debut AND :fin " );

		// Like: 
		//System.out.println(builder.toString());
		Query query = em.createQuery(builder.toString());

		if (!StringUtils.isBlank(cr.getReferenceOperator())) {
			query.setParameter("referenceOperator",cr.getReferenceOperator());
		}
		if (!StringUtils.isBlank(cr.getNomOperator())) {
			query.setParameter("nomOperator","%"+cr.getNomOperator()+"%");
		}
		if (!StringUtils.isBlank(cr.getAgeCaisse())) {
			query.setParameter("ageCaisse",cr.getAgeCaisse());
		}
		if (!StringUtils.isBlank(cr.getUserCaisse())) {
			query.setParameter("userCaisse",cr.getUserCaisse());
		}
		if (!StringUtils.isBlank(cr.getEveid())) {
			query.setParameter("eveid",cr.getEveid());
		}
		if (cr.getStatusTrans() != null) {
			query.setParameter("statusTrans",cr.getStatusTrans());
		}
		if (!StringUtils.isBlank(cr.getAccountDebit())) {
			query.setParameter("accountDebit","%"+cr.getAccountDebit()+"%");
		}
		if (cr.getAmount() != null) {
			if(cr.getAmount().compareTo(0d) > 0 ) query.setParameter("amount",cr.getAmount());
		}
		query.setParameter("debut",validfrom);
		query.setParameter("fin",validTo);
		// ...
		List<Transactions> listes =  query.getResultList();
		
		return listes;
	}
}