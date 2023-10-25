package com.afriland.afbpaypartnerservices.dtos;


import java.util.List;

public class ResponseBkcom extends ResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Bkcom> data;

	/**
	 * 
	 */
	public ResponseBkcom() {
		super();
	}


	public static ResponseBkcom getIntance(){
		return new ResponseBkcom();
	}

	public ResponseBkcom event(List<Bkcom> data) {
		this.setData(data);
		return this;
	}
	
	public ResponseBkcom error(String msg) {
		this.setCode(ResponseHolder.FAIL);
		return this;
	}
	
	public ResponseBkcom sucess(String msg) {
		this.setCode(ResponseHolder.SUCESS);
		return this;
	}


	/**
	 * @return the data
	 */
	public List<Bkcom> getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(List<Bkcom> data) {
		this.data = data;
	}
	
	
}

