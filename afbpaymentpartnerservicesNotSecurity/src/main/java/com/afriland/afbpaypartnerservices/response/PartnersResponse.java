package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.PartnersDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des partners
 * @author yves_labo
 * @version 1.0
 */
public class PartnersResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<PartnersDto> datas = new ArrayList<PartnersDto>();
	
	

	/**
	 * 
	 */
	public PartnersResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public PartnersResponse(String codeResponse, String message, String error, List<PartnersDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<PartnersDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<PartnersDto> datas) {
		this.datas = datas;
	}

	

	
}
