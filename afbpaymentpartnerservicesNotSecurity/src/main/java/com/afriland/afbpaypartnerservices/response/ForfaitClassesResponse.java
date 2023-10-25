package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.ForfaitClassesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des categoryPayments
 * @author yves_labo
 * @version 1.0
 */
public class ForfaitClassesResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<ForfaitClassesDto> datas = new ArrayList<ForfaitClassesDto>();
	
	

	/**
	 * 
	 */
	public ForfaitClassesResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public ForfaitClassesResponse(String codeResponse, String message, String error, List<ForfaitClassesDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<ForfaitClassesDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<ForfaitClassesDto> datas) {
		this.datas = datas;
	}

	

	
}
