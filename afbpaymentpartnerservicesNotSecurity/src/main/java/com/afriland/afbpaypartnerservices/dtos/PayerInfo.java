package com.afriland.afbpaypartnerservices.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PayerInfo {
	
    private String type;
    private String value;
	
    
    /**
	 * 
	 */
	public PayerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param partner
	 * @param type
	 * @param value
	 */
	public PayerInfo(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	
	
	
}
