package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author yves_labo
 *
 */
@Entity
@Table(name = "PAYPART_FORF_PAY")
@JsonIgnoreProperties({"id"})
public class ForfaitPayments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrePersist
	public void prepersit() {
		if(this.getId() == null) this.setId(this.partnerCode + RandomStringUtils.randomAlphanumeric(15).toUpperCase());
	}
	
	
	@Id
	private String id;
	
	@Column
	private String code;
	
	@Column
	private String libelle;
	
	@Column(length = 500)
	private String detail;
	
	@Column
	private String classeForfait;
		
	@Column
	private String typeForfait;
		
	@Column
	private String partnerCode;
	
	@Column
	private Double amount = 0d;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validfrom;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;
	
	@Column
	private boolean actif = Boolean.TRUE;
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}


	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * @return the classeForfait
	 */
	public String getClasseForfait() {
		return classeForfait;
	}

	/**
	 * @param classeForfait the classeForfait to set
	 */
	public void setClasseForfait(String classeForfait) {
		this.classeForfait = classeForfait;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}


	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}


	/**
	 * @return the typeForfait
	 */
	public String getTypeForfait() {
		return typeForfait;
	}


	/**
	 * @param typeForfait the typeForfait to set
	 */
	public void setTypeForfait(String typeForfait) {
		this.typeForfait = typeForfait;
	}


	/**
	 * @return the partnerCode
	 */
	public String getPartnerCode() {
		return partnerCode;
	}


	/**
	 * @param partnerCode the partnerCode to set
	 */
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}


	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}


	/**
	 * @return the validfrom
	 */
	public Date getValidfrom() {
		return validfrom;
	}


	/**
	 * @param validfrom the validfrom to set
	 */
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}


	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}


	/**
	 * @param validTo the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

		
}
