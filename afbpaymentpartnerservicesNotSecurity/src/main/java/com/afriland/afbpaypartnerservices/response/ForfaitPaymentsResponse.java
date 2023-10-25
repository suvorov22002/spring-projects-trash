package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.ForfaitPaymentsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des categoryPayments
 * @author yves_labo
 * @version 1.0
 */
public class ForfaitPaymentsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<ForfaitPaymentsDto> datas = new ArrayList<ForfaitPaymentsDto>();
	
	

	/**
	 * 
	 */
	public ForfaitPaymentsResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public ForfaitPaymentsResponse(String codeResponse, String message, String error, List<ForfaitPaymentsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}


	/**
	 * @return the datas
	 */
	public List<ForfaitPaymentsDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<ForfaitPaymentsDto> datas) {
		this.datas = datas;
	}

	

	
}
