package com.afriland.afbpaypartnerservices.dtos;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.jpa.Operators;

public class OperatorListResponse extends DefaultReponse {

	public List<Operators> data ;
	
	public OperatorListResponse ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public OperatorListResponse ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}

	public OperatorListResponse() {
		super();
	}

	public OperatorListResponse(List<Operators> data) {
		super();
		this.data = new ArrayList<Operators>();
		for(Operators m : data){
			if(m.getValidTo() == null) this.data.add(m);
		}
	}

	/**
	 * @return the data
	 */
	public List<Operators> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Operators> data) {
		this.data = data;
	}
	
	
	
}
