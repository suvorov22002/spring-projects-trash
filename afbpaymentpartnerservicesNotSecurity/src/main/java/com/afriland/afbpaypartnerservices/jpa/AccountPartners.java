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
@Table(name = "PAYPART_ACCOUNT_PART")
@JsonIgnoreProperties({"id"})
public class AccountPartners implements Serializable{

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
	private String account;
	
	@Column
	private String ncp;
		
	@Column
	private String libelle;
	
	@Column
	private String partnerCode;
	
	@Column
	private boolean actif = Boolean.TRUE;
	
	
	/**
	 * @param id
	 * @param code
	 * @param account
	 * @param ncp
	 * @param libelle
	 * @param partnerCode
	 * @param actif
	 */
	public AccountPartners(String id, String code, String account, String ncp, String libelle, String partnerCode,
			boolean actif) {
		super();
		this.id = id;
		this.code = code;
		this.account = account;
		this.ncp = ncp;
		this.libelle = libelle;
		this.partnerCode = partnerCode;
		this.actif = actif;
	}
	

	/**
	 * 
	 */
	public AccountPartners() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the ncp
	 */
	public String getNcp() {
		return ncp;
	}

	/**
	 * @param ncp the ncp to set
	 */
	public void setNcp(String ncp) {
		this.ncp = ncp;
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
