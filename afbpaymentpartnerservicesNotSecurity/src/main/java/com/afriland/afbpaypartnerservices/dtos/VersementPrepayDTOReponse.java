package com.afriland.afbpaypartnerservices.dtos;

public class VersementPrepayDTOReponse  extends DefaultReponse {

	public VersementPrepayDTO data ;
	
	public VersementPrepayDTOReponse fixe(String code) {
		this.setReponseCode(code);
		return this;
	}
	
	public VersementPrepayDTOReponse ok() {
		this.setReponseCode(DefaultReponse.OK);
		return this;
	}

	public VersementPrepayDTOReponse ko() {
		this.setReponseCode(DefaultReponse.KO);
		return this;
	}


	public VersementPrepayDTOReponse() {
		super();
	}

	public VersementPrepayDTOReponse(VersementPrepayDTO data) {
		super();
		this.data = data;
		this.setErrorMessage(data.getErrorsMsg());
	}

	/**
	 * @return the data
	 */
	public VersementPrepayDTO getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(VersementPrepayDTO data) {
		this.data = data;
	}
	
	
	
}