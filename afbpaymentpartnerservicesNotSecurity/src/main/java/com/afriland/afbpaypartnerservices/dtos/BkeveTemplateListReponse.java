package com.afriland.afbpaypartnerservices.dtos;

import java.util.List;

import com.afriland.afbpaypartnerservices.jpa.BkeveTemplate;

public class BkeveTemplateListReponse extends DefaultReponse {

	public List<BkeveTemplate> data ;

	public BkeveTemplateListReponse ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public BkeveTemplateListReponse ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}

	public BkeveTemplateListReponse() {
		super();
	}

	public BkeveTemplateListReponse(List<BkeveTemplate> data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public List<BkeveTemplate> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<BkeveTemplate> data) {
		this.data = data;
	}


}