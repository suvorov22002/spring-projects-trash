package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

/**
 * 
 * @author yves_labo
 *
 */
public class AccountPartnersDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String code;
	
	private String account;
	
	private String ncp;
		
	private String libelle;
	
	private String partnerCode;
	
	private Boolean actif = Boolean.TRUE;
		

	/**
	 * @param id
	 * @param code
	 * @param account
	 * @param ncp
	 * @param libelle
	 * @param partnerCode
	 * @param actif
	 */
	public AccountPartnersDto(String id, String code, String account, String ncp, String libelle, String partnerCode,
			Boolean actif) {
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
	public AccountPartnersDto() {
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
