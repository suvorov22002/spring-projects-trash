package com.afriland.afbpaypartnerservices.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;

/**
 * 
 * @author yves_labo
 *
 */
@Entity
@Table(name = "PAYPART_PROP_CONF")
public class PropertyConfigs {

	@Id
	private String id;
	
	@Column
	private String code;
	
	@Column
	private String value;
	
	@Column
	private String description;
	
	@PrePersist
	public void prepersit() {
		this.id = this.code;
	}
	
	@Transient
	public final static String secretKey = "xxxxxx";
	
	
	public PropertyConfigs() {
		super();
	}
	

	/**
	 * @param id
	 * @param code
	 * @param value
	 * @param description
	 */
	public PropertyConfigs(String id, String code, String value, String description) {
		super();
		this.id = id;
		this.code = code;
		this.value = value;
		this.description = description;
	}


	public PropertyConfigs(String value, String description) {
		super();
		this.value = value;
		this.description = description;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static String decrypPassword(PropertyConfigs parameter) {
		if(parameter.getCode().contains("PWD")) {
			
			return AfbEncryptionDecryption.decrypt(parameter.getValue(), secretKey);
		}
		else
			return parameter.getValue();
	}
	
	
}
