package com.afriland.afbpaypartnerservices.dtos;


import java.util.List;

public class ResponseCustomer extends ResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Customer> data;

	/**
	 * 
	 */
	public ResponseCustomer() {
		super();
	}


	public static ResponseCustomer getIntance(){
		return new ResponseCustomer();
	}

	public ResponseCustomer event(List<Customer> data) {
		this.setData(data);
		return this;
	}
	
	public ResponseCustomer error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		return this;
	}
	
	public ResponseCustomer sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		return this;
	}


	/**
	 * @return the data
	 */
	public List<Customer> getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(List<Customer> data) {
		this.data = data;
	}
	
	
}
