package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

public class ObjectFile implements Serializable {


	private static final long serialVersionUID = 1L;

	private String code;

	private String value;


	
	/**
	 * Constructeur par d�faut
	 */
	public ObjectFile() {
		super();
	}


	/**
	 * @param code
	 * @param value
	 */
	public ObjectFile(String code, String value) {
		super();
		this.code = code;
		this.value = value;
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



}
