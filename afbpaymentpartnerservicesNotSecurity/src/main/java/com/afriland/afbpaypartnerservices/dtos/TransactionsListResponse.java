package com.afriland.afbpaypartnerservices.dtos;

import java.util.List;

import com.afriland.afbpaypartnerservices.jpa.Transactions;

public class TransactionsListResponse extends DefaultReponse {

	public List<Transactions> data ;
	
	public TransactionsListResponse ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public TransactionsListResponse ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}

	public TransactionsListResponse() {
		super();
	}

	public TransactionsListResponse(List<Transactions> data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public List<Transactions> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Transactions> data) {
		this.data = data;
	}
	
	
	
}
