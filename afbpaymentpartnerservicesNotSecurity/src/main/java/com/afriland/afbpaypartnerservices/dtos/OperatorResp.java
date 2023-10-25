package com.afriland.afbpaypartnerservices.dtos;

import com.afriland.afbpaypartnerservices.jpa.Operators;

public class OperatorResp extends DefaultReponse {

	public Operators data ;
	
	public OperatorResp ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public OperatorResp ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}


	public OperatorResp() {
		super();
	}

	public OperatorResp(Operators data) {
		super();
		this.data = data;
		this.setErrorMessage(data.getErrorsMsg());
	}

	/**
	 * @return the data
	 */
	public Operators getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Operators data) {
		this.data = data;
	}
	
	
	
}
