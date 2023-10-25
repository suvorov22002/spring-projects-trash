package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.InfosCustomerVersementDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la reponse des Infos Customer Versement
 * @author yves_labo
 * @version 1.0
 */
public class InfosCustomerVersementResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<InfosCustomerVersementDto> datas = new ArrayList<InfosCustomerVersementDto>();
	
	

	/**
	 * 
	 */
	public InfosCustomerVersementResponse() {
		super();
	}


	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public InfosCustomerVersementResponse(String codeResponse, String message, String error, List<InfosCustomerVersementDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}

	

	/**
	 * @return the datas
	 */
	public List<InfosCustomerVersementDto> getDatas() {
		return datas;
	}


	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<InfosCustomerVersementDto> datas) {
		this.datas = datas;
	}

	
}
