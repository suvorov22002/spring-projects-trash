package com.afriland.afbpaypartnerservices.dtos;

/**
 * 
 * @author yves_labo
 *
 */
public class PropertyConfigsDto {

	private String id;
	
	private String code;
	
	private String value;
	
	private String description;
	
	
	/**
	 * 
	 */
	public PropertyConfigsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyConfigsDto(String value, String description) {
		super();
		this.value = value;
		this.description = description;
	}

	/**
	 * @param code
	 * @param value
	 * @param description
	 */
	public PropertyConfigsDto(String code, String value, String description) {
		super();
		this.code = code;
		this.value = value;
		this.description = description;
	}

	/**
	 * @param id
	 * @param code
	 * @param value
	 * @param description
	 */
	public PropertyConfigsDto(String id, String code, String value, String description) {
		super();
		this.id = id;
		this.code = code;
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
	
	
}
