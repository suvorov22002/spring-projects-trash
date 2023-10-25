package com.afriland.afbpaypartnerservices.dtos.trusayway;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PaymentTokenResponse {
	
    private int status;
    
    private String client;
    
    private String message;

	
    /**
	 * @param status
	 * @param client
	 * @param message
	 */
	public PaymentTokenResponse(int status, String client, String message) {
		super();
		this.status = status;
		this.client = client;
		this.message = message;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}


	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentTokenResponse [status=" + status + ", client=" + client + ", message=" + message + "]";
	}
    
    
        
}
