package com.afriland.afbpaypartnerservices.dtos.trusayway;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class PaymentToken {
	
    private String activation_key;
    private String payment_key;
	
    
    
    /**
	 * 
	 */
	public PaymentToken() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param activation_key
	 * @param payment_key
	 */
	public PaymentToken(String activation_key, String payment_key) {
		super();
		this.activation_key = activation_key;
		this.payment_key = payment_key;
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
	 * @return the payment_key
	 */
	public String getPayment_key() {
		return payment_key;
	}


	/**
	 * @param payment_key the payment_key to set
	 */
	public void setPayment_key(String payment_key) {
		this.payment_key = payment_key;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentToken [activation_key=" + activation_key + ", payment_key=" + payment_key + "]";
	}

        
}
