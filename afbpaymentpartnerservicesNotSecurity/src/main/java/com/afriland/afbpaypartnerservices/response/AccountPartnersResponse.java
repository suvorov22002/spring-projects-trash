package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.AccountPartnersDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des AccountPartners
 * @author yves_labo
 * @version 1.0
 */
public class AccountPartnersResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<AccountPartnersDto> datas = new ArrayList<AccountPartnersDto>();
	
	

	/**
	 * 
	 */
	public AccountPartnersResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public AccountPartnersResponse(String codeResponse, String message, String error, List<AccountPartnersDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}

	

	/**
	 * @return the datas
	 */
	public List<AccountPartnersDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<AccountPartnersDto> datas) {
		this.datas = datas;
	}

	
}
