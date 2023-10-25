package com.afriland.afbpaypartnerservices.dtos;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class PaymentDetails implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// The unique customer key generated during the subscription
    private String key = null;
    
    // The unique customer niu
    private String niu = null;
    
    // The partner transaction ID
    private String trxID = null;
    
    // The payment amount
    private Double amount = 0d;
    
    // The payment label
    private String lib = null;
    
    // The payment reason
    private String reason = null;
    
    // The payment reason
    private Object details = null;
    
    private String partnerCode = null;
    
    
	/**
	 * 
	 */
	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param key
	 * @param trxID
	 * @param amount
	 * @param lib
	 * @param reason
	 * @param details
	 */
	public PaymentDetails(String key, String trxID, Double amount, String lib, String reason, Object details, String partnerCode) {
		super();
		this.key = key;
		this.trxID = trxID;
		this.amount = amount;
		this.lib = lib;
		this.reason = reason;
		this.details = details;
		this.partnerCode = partnerCode;
	}
	
	
	
	/**
	 * @param key
	 * @param niu
	 * @param trxID
	 * @param amount
	 * @param lib
	 * @param reason
	 * @param details
	 * @param partnerCode
	 */
	public PaymentDetails(String key, String niu, String trxID, Double amount, String lib, String reason,
			Object details, String partnerCode) {
		super();
		this.key = key;
		this.niu = niu;
		this.trxID = trxID;
		this.amount = amount;
		this.lib = lib;
		this.reason = reason;
		this.details = details;
		this.partnerCode = partnerCode;
	}
	
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	
	/**
	 * @return the niu
	 */
	public String getNiu() {
		return niu;
	}


	/**
	 * @param niu the niu to set
	 */
	public void setNiu(String niu) {
		this.niu = niu;
	}
	
	
	/**
	 * @return the trxID
	 */
	public String getTrxID() {
		return trxID;
	}
	
	
	/**
	 * @param trxID the trxID to set
	 */
	public void setTrxID(String trxID) {
		this.trxID = trxID;
	}
	
	
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	/**
	 * @return the lib
	 */
	public String getLib() {
		return lib;
	}
	
	
	/**
	 * @param lib the lib to set
	 */
	public void setLib(String lib) {
		this.lib = lib;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * @return the details
	 */
	public Object getDetails() {
		return details;
	}


	/**
	 * @param details the details to set
	 */
	public void setDetails(Object details) {
		this.details = details;
	}


	/**
	 * @return the partnerCode
	 */
	public String getPartnerCode() {
		return partnerCode;
	}


	/**
	 * @param partnerCode the partnerCode to set
	 */
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentDetails [key=" + key + ", niu=" + niu + ", trxID=" + trxID + ", amount=" + amount + ", lib="
				+ lib + ", reason=" + reason + ", details=" + details + ", partnerCode=" + partnerCode + "]";
	} 
        
    
}
