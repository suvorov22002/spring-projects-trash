package com.afriland.afbpaypartnerservices.response;


import org.apache.commons.lang3.StringUtils;

import com.afriland.afbpaypartnerservices.dtos.ResponseBase;
import com.afriland.afbpaypartnerservices.dtos.ResponseHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataResponseDTO extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String data;

	/**
	 * 
	 */
	public DataResponseDTO() {
		super();
	}

	/**
	 * @param code
	 * @param error
	 * @param message
	 * @param eta
	 */
	public DataResponseDTO(String code, String error, String message, String data) {
		super();
		this.setCode(code); 
		this.setError(error); 
		this.setMessage(message);
		this.data = data;
	}

	public Boolean status() {
		if(StringUtils.equalsIgnoreCase(this.getCode(), ResponseHolder.FAIL)) return Boolean.FALSE;
		else return Boolean.TRUE;
	}
	
	public static DataResponseDTO getIntance(){
		return new DataResponseDTO();
	}

	public DataResponseDTO event(String data) {
		this.setData(data);
		return this;
	}
	
	public DataResponseDTO error(String code) {
		this.setCode(code);
		return this;
	}
	
	public DataResponseDTO sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		this.setError(msg);
		return this;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	


}

