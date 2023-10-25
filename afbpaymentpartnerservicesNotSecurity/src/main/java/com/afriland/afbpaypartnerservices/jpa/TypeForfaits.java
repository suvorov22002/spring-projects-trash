package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.Segment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author yves_labo
 *
 */
@Entity
@Table(name = "PAYPART_TYPE_FORF")
@JsonIgnoreProperties({"id"})
public class TypeForfaits implements Serializable{

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
	@Enumerated(EnumType.STRING)
	private Segment segment;
	
	@Column
	private String partnerCode;
	
	@Column
	private boolean actif = Boolean.TRUE;

		
	/**
	 * 
	 */
	public TypeForfaits() {
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
	public TypeForfaits(String id, String code, String libelle, Segment segment, String partnerCode, boolean actif) {
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
