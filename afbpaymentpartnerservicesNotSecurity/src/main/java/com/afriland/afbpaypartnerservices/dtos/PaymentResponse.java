package com.afriland.afbpaypartnerservices.dtos;

import com.afriland.afbpaypartnerservices.jpa.Transactions;

public class PaymentResponse {
	
    private String status;
    private String partnerTrxID;
    private String transactionID;
    private String responseCode;
    private String message;
    private String libelleResponse; 

                
    /**
	 * 
	 */
	public PaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    public PaymentResponse(Transactions tr, String responseCode) {
		super();
		if(tr==null) new PaymentResponse();
		else{
			this.status = tr.getStatusTrans().toString();
			this.partnerTrxID = tr.getPartnerTrxID();
			this.transactionID = tr.getId();
			this.responseCode = responseCode;
			this.libelleResponse = null==tr.getExceptionlib() ? "" : tr.getExceptionlib();
			this.message = null==tr.getExceptionCode() ? "" : tr.getExceptionCode().toString();
		}
		
	}
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the partnerTrxID
	 */
	public String getPartnerTrxID() {
		return partnerTrxID;
	}


	/**
	 * @param partnerTrxID the partnerTrxID to set
	 */
	public void setPartnerTrxID(String partnerTrxID) {
		this.partnerTrxID = partnerTrxID;
	}


	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}


	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}


	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}


	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	/**
	 * @return the libelleResponse
	 */
	public String getLibelleResponse() {
		return libelleResponse;
	}


	/**
	 * @param libelleResponse the libelleResponse to set
	 */
	public void setLibelleResponse(String libelleResponse) {
		this.libelleResponse = libelleResponse;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}