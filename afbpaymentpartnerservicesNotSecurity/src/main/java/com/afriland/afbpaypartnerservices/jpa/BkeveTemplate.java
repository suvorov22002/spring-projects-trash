package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.utils.DateUtil;

/**
 * Classe representant la table des evenements de Amplitude
 * @author yves_labo
 * @version 1.0
 */
@Entity
@Table(name = "PAYPART_BKEVE_TEMP")
public class BkeveTemplate extends BkeveBase implements Serializable {

	/**a
	 * Default Serial UID
	 */
	private static final long serialVersionUID = 1L;

	public static final String SIMPLE = "SIMPLE";
	public static final String DEPLACE_PARTNER = "DEPLACE_PARTNER";
	public static final String DEPLACE_AGENT = "DEPLACE_AGENT";
	public static final String VIREMENT = "VIREMENT";

	@Column
	public String typeOpe; 


	/**
	 * Default Constructor
	 */
	public BkeveTemplate() {}

	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now()+RandomStringUtils.randomNumeric(6));
	}


	/**
	 * @return the typeOpe
	 */
	public String getTypeOpe() {
		return typeOpe;
	}


	/**
	 * @param typeOpe the typeOpe to set
	 */
	public void setTypeOpe(String typeOpe) {
		this.typeOpe = typeOpe;
	}



}