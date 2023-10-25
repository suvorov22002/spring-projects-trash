package com.afriland.afbpaypartnerservices.dtos.trusayway;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PaymentConfirmationResponse {

    private String id;
    private String status;

        
    public PaymentConfirmationResponse(String id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

    
	public String getId() {
        return id;
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
	
}
