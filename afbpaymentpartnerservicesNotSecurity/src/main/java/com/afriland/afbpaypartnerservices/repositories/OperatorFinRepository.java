package com.afriland.afbpaypartnerservices.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.jpa.Operators;

/**
 * 
 * @author yves_labo
 *
 */
@Service
public class OperatorFinRepository {

	@PersistenceContext
	private EntityManager em;
	
	public List<Operators> Pendingvalidationoperator(String user,String agenceSous) {

		if(StringUtils.isBlank(user)){
			return new ArrayList<Operators>();
		}
		
		// Do something with the entity manager
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT m FROM Operator m ");
		builder.append(" WHERE "); 
		builder.append(" m.valid = :valid "); 
		builder.append(" AND ");
		builder.append(" m.merStatus != :merStatus "); 
		builder.append(" AND ");
		builder.append(" m.validTo is null "); 
		if (!StringUtils.isBlank(user)) {
			builder.append(" AND "); builder.append(" m.utiunitiate != :utiunitiate ");
		}
		if (!StringUtils.isBlank(agenceSous)) {
			builder.append(" agenceSous "); builder.append(" m.agenceSous != :agenceSous ");
		}
		
		// Like: 
		//System.out.println(builder.toString());
		Query query = em.createQuery(builder.toString());

		query.setParameter("valid",Boolean.FALSE);
		query.setParameter("merStatus",PayPartnerStatutSubscriber.WAITING);
		if (!StringUtils.isBlank(user)) {
			query.setParameter("utiunitiate",user);
		}
		if (!StringUtils.isBlank(agenceSous)) {
			query.setParameter("agenceSous",agenceSous);
		}

		@SuppressWarnings("unchecked")
		List<Operators> listes =  query.getResultList();
		return listes;

	}

	@SuppressWarnings("unchecked")
	public List<Operators> findDataByCriteria(Operators cr,Date validfrom,Date validTo) {

		if(cr == null){
			return new ArrayList<Operators>();
		}
		
		// Do something with the entity manager
		StringBuilder builder = new StringBuilder();

		builder.append("SELECT m FROM Operator m ");
		builder.append(" WHERE "); 
		builder.append(" m.validfrom between :debut AND :fin " );

		if (!StringUtils.isBlank(cr.getId())) {
			builder.append(" AND ");
			builder.append(" m.id like :id "); 
		}
		
		if (!StringUtils.isBlank(cr.getPartnerName())) {
			builder.append(" AND ");
			builder.append(" m.operatorName like :operatorName ");
		}
				
		
		// Like: 
		//System.out.println(builder.toString());
		Query query = em.createQuery(builder.toString());

		query.setParameter("debut",validfrom);
		query.setParameter("fin",validTo);
		
		if (!StringUtils.isBlank(cr.getId())) {
			query.setParameter("id","%"+cr.getId()+"%");
		}
		if (!StringUtils.isBlank(cr.getPartnerName())) {
			query.setParameter("operatorName","%"+cr.getPartnerName().toUpperCase()+"%");
		}
		
		// ...
		List<Operators> listes =  query.getResultList();

		return listes;

	}

}
