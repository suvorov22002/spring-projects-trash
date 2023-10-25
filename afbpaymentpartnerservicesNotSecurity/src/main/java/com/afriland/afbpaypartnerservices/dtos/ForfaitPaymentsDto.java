package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @author yves_labo
 *
 */
public class ForfaitPaymentsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String makeId(){
		return RandomStringUtils.randomAlphanumeric(25).toUpperCase();
	}
	
	private String id;
	
	private String code;
	
	private String libelle; 
	
	private String typeForfait;
	
	private String detail;
	
	private String partnerCode;
	
	private Double amount = 0d;
	
	private Date validfrom;
	
	private Date validTo;
	
	private Boolean actif;
	
		
	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param typeForfait
	 * @param detail
	 * @param partnerCode
	 * @param amount
	 * @param validfrom
	 * @param validTo
	 * @param actif
	 */
	public ForfaitPaymentsDto(String id, String code, String libelle, String typeForfait, String detail,
			String partnerCode, Double amount, Date validfrom, Date validTo, Boolean actif) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.typeForfait = typeForfait;
		this.detail = detail;
		this.partnerCode = partnerCode;
		this.amount = amount;
		this.validfrom = validfrom;
		this.validTo = validTo;
		this.actif = actif;
	}
	

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
	public Boolean getActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}

}
