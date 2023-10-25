package com.afriland.afbpaypartnerservices.dtos;


import java.util.List;


public class ResponseCaisse extends ResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CashierDto> data;

	/**
	 * 
	 */
	public ResponseCaisse() {
		super();
	}


	public static ResponseCaisse getIntance(){
		return new ResponseCaisse();
	}

	public ResponseCaisse event(List<CashierDto> data) {
		this.setData(data);
		return this;
	}
	
	public ResponseCaisse error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		return this;
	}
	
	public ResponseCaisse sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		return this;
	}


	/**
	 * @return the data
	 */
	public List<CashierDto> getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(List<CashierDto> data) {
		this.data = data;
	}
	
	
}
