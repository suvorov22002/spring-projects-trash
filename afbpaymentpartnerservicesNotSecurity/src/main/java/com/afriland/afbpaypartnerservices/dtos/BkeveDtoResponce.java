package com.afriland.afbpaypartnerservices.dtos;


import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.afriland.afbpaypartnerservices.jpa.Bkeve;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BkeveDtoResponce implements Serializable{

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String code; 

	private String error;
	
	private String message;

	private Bkeve eve01;
	
	private Bkeve eve02;

	/**
	 * 
	 */
	public BkeveDtoResponce() {
		super();
	}

	/**
	 * @param code
	 * @param error
	 * @param message
	 * @param eve
	 */
	public BkeveDtoResponce(String code, String error, String message, Bkeve eve01, Bkeve eve02) {
		super();
		this.code = code;
		this.error = error;
		this.message = message;
		this.eve01 = eve01;
		this.eve02 = eve02;
	}

	public Boolean status() {
		if(StringUtils.equalsIgnoreCase(this.getCode(), ResponseHolder.FAIL)) return Boolean.FALSE;
		else return Boolean.TRUE;
	}
	
	public static BkeveDtoResponce getIntance(){
		return new BkeveDtoResponce();
	}

	public BkeveDtoResponce	 event(Bkeve eve01, Bkeve eve02) {
		this.setEve01(eve01);
		this.setEve02(eve02);
		return this;
	}
	
	public BkeveDtoResponce error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		this.setMessage("FAIL");
		this.setError(msg);
		return this;
	}
	
	public BkeveDtoResponce sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		this.setMessage("SUCESS");
		this.setError(msg);
		return this;
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
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the eve01
	 */
	public Bkeve getEve01() {
		return eve01;
	}

	/**
	 * @param eve01 the eve01 to set
	 */
	public void setEve01(Bkeve eve01) {
		this.eve01 = eve01;
	}

	/**
	 * @return the eve02
	 */
	public Bkeve getEve02() {
		return eve02;
	}

	/**
	 * @param eve02 the eve02 to set
	 */
	public void setEve02(Bkeve eve02) {
		this.eve02 = eve02;
	}
	
}
