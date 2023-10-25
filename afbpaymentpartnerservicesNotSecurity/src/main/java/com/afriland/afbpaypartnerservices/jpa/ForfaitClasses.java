package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author yves_labo
 *
 */
@Entity
@Table(name = "PAYPART_CLASS_PAY")
@JsonIgnoreProperties({"id"})
public class ForfaitClasses implements Serializable{

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
	
	@Column
	private String typeForfait;
		
	@Column
	private String partnerCode;
	
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
