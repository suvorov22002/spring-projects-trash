package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.UserExternsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des users externs
 * @author yves_labo
 * @version 1.0
 */
public class UserExternsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<UserExternsDto> datas = new ArrayList<UserExternsDto>();
	
	

	/**
	 * 
	 */
	public UserExternsResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public UserExternsResponse(String codeResponse, String message, String error, List<UserExternsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<UserExternsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<UserExternsDto> datas) {
		this.datas = datas;
	}

	

	
}
