package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.PropertyConfigsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des propertyConfigs
 * @author yves_labo
 * @version 1.0
 */
public class PropertyConfigsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<PropertyConfigsDto> datas = new ArrayList<PropertyConfigsDto>();
	
	

	/**
	 * 
	 */
	public PropertyConfigsResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public PropertyConfigsResponse(String codeResponse, String message, String error, List<PropertyConfigsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<PropertyConfigsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<PropertyConfigsDto> datas) {
		this.datas = datas;
	}

	

	
}
