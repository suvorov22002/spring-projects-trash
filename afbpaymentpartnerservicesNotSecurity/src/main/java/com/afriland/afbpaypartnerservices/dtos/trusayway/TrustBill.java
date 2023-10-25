package com.afriland.afbpaypartnerservices.dtos.trusayway;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TrustBill implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// The bill reference
    private String billRef = "";
    
    // The bill creation time
    private Long createTime = 0L;
    
    // The bill due time
    private Long dueTime = 0L;
    
    // The bill due amount
    private Long dueAmount = 0L;
    
    // The bill currency
    private String currency = null;
    
    // The bill status
    private String status = null;
    
    
	/**
	 * 
	 */
	public TrustBill() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param billRef
	 * @param createTime
	 * @param dueTime
	 * @param dueAmount
	 * @param currency
	 * @param status
	 */
	public TrustBill(String billRef, Long createTime, Long dueTime, Long dueAmount, String currency, String status) {
		super();
		this.billRef = billRef;
		this.createTime = createTime;
		this.dueTime = dueTime;
		this.dueAmount = dueAmount;
		this.currency = currency;
		this.status = status;
	}


	/**
	 * @return the billRef
	 */
	public String getBillRef() {
		return billRef;
	}


	/**
	 * @param billRef the billRef to set
	 */
	public void setBillRef(String billRef) {
		this.billRef = billRef;
	}


	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}


	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	/**
	 * @return the dueTime
	 */
	public Long getDueTime() {
		return dueTime;
	}


	/**
	 * @param dueTime the dueTime to set
	 */
	public void setDueTime(Long dueTime) {
		this.dueTime = dueTime;
	}


	/**
	 * @return the dueAmount
	 */
	public Long getDueAmount() {
		return dueAmount;
	}


	/**
	 * @param dueAmount the dueAmount to set
	 */
	public void setDueAmount(Long dueAmount) {
		this.dueAmount = dueAmount;
	}


	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bill [billRef=" + billRef + ", createTime=" + createTime + ", dueTime=" + dueTime + ", dueAmount="
				+ dueAmount + ", currency=" + currency + ", status=" + status + "]";
	}       
    
}
