package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.TypeForfaitsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des typeForfaits
 * @author yves_labo
 * @version 1.0
 */
public class TypeForfaitsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<TypeForfaitsDto> datas = new ArrayList<TypeForfaitsDto>();
	
	

	/**
	 * 
	 */
	public TypeForfaitsResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public TypeForfaitsResponse(String codeResponse, String message, String error, List<TypeForfaitsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<TypeForfaitsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<TypeForfaitsDto> datas) {
		this.datas = datas;
	}

	

	
}
