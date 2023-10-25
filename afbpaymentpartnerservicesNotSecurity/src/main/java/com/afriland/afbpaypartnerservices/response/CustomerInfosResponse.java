package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.CustomerDetailsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des operators
 * @author yves_labo
 * @version 1.0
 */
public class CustomerInfosResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<CustomerDetailsDto> datas = new ArrayList<CustomerDetailsDto>();
	
	

	/**
	 * 
	 */
	public CustomerInfosResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public CustomerInfosResponse(String codeResponse, String message, String error, List<CustomerDetailsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}

	

	/**
	 * @return the datas
	 */
	public List<CustomerDetailsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<CustomerDetailsDto> datas) {
		this.datas = datas;
	}

	
}
