package com.afriland.afbpaypartnerservices.jpa;


import org.apache.commons.lang3.StringUtils;

import com.afriland.afbpaypartnerservices.dtos.ResponseBase;
import com.afriland.afbpaypartnerservices.dtos.ResponseHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class BkeveDTO extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private Bkeve eve;

	/**
	 * 
	 */
	public BkeveDTO() {
		super();
	}

	/**
	 * @param code
	 * @param error
	 * @param message
	 * @param eve
	 */
	public BkeveDTO(String code, String error, String message, Bkeve eve) {
		super();
		this.setCode(code); 
		this.setError(error); 
		this.setMessage(message);
		this.eve = eve;
	}

	public Boolean status() {
		if(StringUtils.equalsIgnoreCase(this.getCode(), ResponseHolder.FAIL)) return Boolean.FALSE;
		else return Boolean.TRUE;
	}
	
	public static BkeveDTO getIntance(){
		return new BkeveDTO();
	}

	public BkeveDTO event(Bkeve eve) {
		this.setEve(eve);
		return this;
	}
	
	public BkeveDTO error(String code) {
		this.setCode(code);
		return this;
	}
	
	public BkeveDTO sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		this.setError(msg);
		return this;
	}
	

	/**
	 * @return the eve
	 */
	public Bkeve getEve() {
		return eve;
	}

	/**
	 * @param eve the eve to set
	 */
	public void setEve(Bkeve eve) {
		this.eve = eve;
	}
	
	

}
