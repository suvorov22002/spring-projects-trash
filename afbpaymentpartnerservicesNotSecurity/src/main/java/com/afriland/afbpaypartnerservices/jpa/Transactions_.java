package com.afriland.afbpaypartnerservices.jpa;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.afriland.afbpaypartnerservices.enums.TransactionStatus;

@javax.annotation.processing.Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transactions.class)
public abstract class Transactions_  {

	public static volatile SingularAttribute<Transactions, String> reference;
	public static volatile SingularAttribute<Transactions, TransactionStatus> statusTrans;
	
	public static volatile SingularAttribute<Transactions, Double> amount;
	public static volatile SingularAttribute<Transactions, String> accountDebit;
	public static volatile SingularAttribute<Transactions, String> referenceOperator;
	public static volatile SingularAttribute<Transactions, String> nomOperator;

	public static volatile SingularAttribute<Transactions, String> userCaisse;
	public static volatile SingularAttribute<Transactions, String> ageCaisse;
	public static volatile SingularAttribute<Transactions, String> referencenumber;
	public static volatile SingularAttribute<Transactions, String> eveid;
	public static volatile SingularAttribute<Transactions, Date> validfrom;
	
	
	public static volatile SingularAttribute<Transactions, String> partieVersante;
	public static volatile SingularAttribute<Transactions, String> telephone;
	public static volatile SingularAttribute<Transactions, Double> montantPercu;
	public static volatile SingularAttribute<Transactions, Double> montantVerse;
	
	
}