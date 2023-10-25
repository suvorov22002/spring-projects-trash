package com.afriland.afbpaypartnerservices.dtos;

import lombok.ToString;

@ToString
public class DefaultReponse {

	public final static String OK ="200";
	public final static String KO ="000";

	public String reponseCode = "";
	
	public String errorMessage = "";
	
	


	public DefaultReponse() {
		super();
	}

	/**
	 * @return the reponseCode
	 */
	public String getReponseCode() {
		return reponseCode;
	}

	/**
	 * @param reponseCode the reponseCode to set
	 */
	public void setReponseCode(String reponseCode) {
		this.reponseCode = reponseCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
