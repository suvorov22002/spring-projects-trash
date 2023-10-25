package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.OperatorsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des operators
 * @author yves_labo
 * @version 1.0
 */
public class OperatorsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<OperatorsDto> datas = new ArrayList<OperatorsDto>();
	
	

	/**
	 * 
	 */
	public OperatorsResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public OperatorsResponse(String codeResponse, String message, String error, List<OperatorsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}

	

	/**
	 * @return the datas
	 */
	public List<OperatorsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<OperatorsDto> datas) {
		this.datas = datas;
	}

	
}
