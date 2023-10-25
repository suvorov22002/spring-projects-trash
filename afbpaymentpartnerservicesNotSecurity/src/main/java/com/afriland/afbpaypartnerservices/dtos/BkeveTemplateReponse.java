package com.afriland.afbpaypartnerservices.dtos;

import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;

public class BkeveTemplateReponse extends DefaultReponse {

	public BkeveTemplate data ;

	public BkeveTemplateReponse ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public BkeveTemplateReponse ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}


	public BkeveTemplateReponse() {
		super();
	}

	public BkeveTemplateReponse(BkeveTemplate data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public BkeveTemplate getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(BkeveTemplate data) {
		this.data = data;
	}

}