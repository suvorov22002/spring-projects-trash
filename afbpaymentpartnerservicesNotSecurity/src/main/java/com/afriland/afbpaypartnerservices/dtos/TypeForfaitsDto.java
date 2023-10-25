package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

import com.afriland.afbpaypartnerservices.enums.Segment;

/**
 * 
 * @author yves_labo
 *
 */
public class TypeForfaitsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String code;
	
	private String libelle;
	
	private Segment segment;
	
	private String partnerCode;
	
	private Boolean actif;
	
	
	/**
	 * 
	 */
	public TypeForfaitsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param segment
	 * @param partnerCode
	 * @param actif
	 */
	public TypeForfaitsDto(String id, String code, String libelle, Segment segment, String partnerCode, Boolean actif) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.segment = segment;
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
	 * @return the segment
	 */
	public Segment getSegment() {
		return segment;
	}


	/**
	 * @param segment the segment to set
	 */
	public void setSegment(Segment segment) {
		this.segment = segment;
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

	@Override
	public String toString() {
		return "TypeForfaitsDto [code=" + code + ", libelle=" + libelle + ", segment=" + segment + ", partnerCode="
				+ partnerCode + "]";
	}

	
}
