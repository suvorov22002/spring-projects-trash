package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.VentilationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class VentilationResponse extends ResponseBase {
	
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	private List<VentilationDto> datas = new ArrayList<VentilationDto>();


	public VentilationResponse() {
		super();
	}

	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public VentilationResponse(String codeResponse, String message, String error, List<VentilationDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}

	public List<VentilationDto> getDatas() {
		return datas;
	}

	public void setDatas(List<VentilationDto> datas) {
		this.datas = datas;
	}
	
}
