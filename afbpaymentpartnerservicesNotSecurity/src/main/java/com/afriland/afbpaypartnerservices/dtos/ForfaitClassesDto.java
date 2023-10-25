package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
 * @author yves_labo
 *
 */
public class ForfaitClassesDto implements Serializable{

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
	
	private String partnerCode;
	
	private Boolean actif;
	
	
	
	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param typeForfait
	 * @param partnerCode
	 * @param actif
	 */
	public ForfaitClassesDto(String id, String code, String libelle, String typeForfait, String partnerCode,
			Boolean actif) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.typeForfait = typeForfait;
		this.partnerCode = partnerCode;
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
