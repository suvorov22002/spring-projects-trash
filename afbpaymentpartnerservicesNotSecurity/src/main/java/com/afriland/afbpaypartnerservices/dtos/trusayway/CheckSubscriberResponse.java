package com.afriland.afbpaypartnerservices.dtos.trusayway;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CheckSubscriberResponse {
	
    private int status;
    
    private String client;
    
    private String activation_key;
    
    private String expiration_date;
    
    private String message;

	
    
	/**
	 * @param status
	 * @param client
	 * @param activation_key
	 * @param expiration_date
	 * @param message
	 */
	public CheckSubscriberResponse(int status, String client, String activation_key, String expiration_date,
			String message) {
		super();
		this.status = status;
		this.client = client;
		this.activation_key = activation_key;
		this.expiration_date = expiration_date;
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
	

	/**
	 * @return the activation_key
	 */
	public String getActivation_key() {
		return activation_key;
	}


	/**
	 * @param activation_key the activation_key to set
	 */
	public void setActivation_key(String activation_key) {
		this.activation_key = activation_key;
	}


	/**
	 * @return the expiration_date
	 */
	public String getExpiration_date() {
		return expiration_date;
	}


	/**
	 * @param expiration_date the expiration_date to set
	 */
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentTokenResponse [status=" + status + ", client=" + client + ", message=" + message + "]";
	}
    
    
        
}
