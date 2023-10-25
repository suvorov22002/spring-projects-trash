package com.afriland.afbpaypartnerservices.dtos.trusayway;

import java.io.Serializable;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.PayerInfo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TrustPaymentResponse implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// The short description of error
    private String error = "";
    
 // The long description of error
    private String error_description = "";
	
	// The payment creation time
    private Long createtime = 0L;
    
    // The partner transaction ID
    private String intent = null;
    
    // The payment partner transaction reference
    private String acquirertrxref = null;
    
    // The payment biller reference
    private String tomember = null;
    
    // The payment total amount for all selected bills
    private Long amount  = 0L;
    
    // The payment currency
    private String currency = null;
    
 // The payment payer id info
    private PayerInfo payerinfo = null;
    
    // The payment bill list
    private List<TrustBill> billList = null;
    
    // The payment description
    private String description  = null;
    
    // The state of processing
    private String state  = null;
    
    
	/**
	 * 
	 */
	public TrustPaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param createtime
	 * @param intent
	 * @param acquirertrxref
	 * @param tomember
	 * @param amount
	 * @param currency
	 * @param payerinfo
	 * @param billList
	 * @param description
	 */
	public TrustPaymentResponse(Long createtime, String intent, String acquirertrxref, String tomember, Long amount,
			String currency, PayerInfo payerinfo, List<TrustBill> billList, String description) {
		super();
		this.createtime = createtime;
		this.intent = intent;
		this.acquirertrxref = acquirertrxref;
		this.tomember = tomember;
		this.amount = amount;
		this.currency = currency;
		this.payerinfo = payerinfo;
		this.billList = billList;
		this.description = description;
	}


	/**
	 * @param error
	 * @param error_description
	 * @param createtime
	 * @param intent
	 * @param acquirertrxref
	 * @param tomember
	 * @param amount
	 * @param currency
	 * @param payerinfo
	 * @param billList
	 * @param description
	 * @param state
	 */
	public TrustPaymentResponse(String error, String error_description, Long createtime, String intent,
			String acquirertrxref, String tomember, Long amount, String currency, PayerInfo payerinfo,
			List<TrustBill> billList, String description, String state) {
		super();
		this.error = error;
		this.error_description = error_description;
		this.createtime = createtime;
		this.intent = intent;
		this.acquirertrxref = acquirertrxref;
		this.tomember = tomember;
		this.amount = amount;
		this.currency = currency;
		this.payerinfo = payerinfo;
		this.billList = billList;
		this.description = description;
		this.state = state;
	}


	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}


	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}


	/**
	 * @return the error_description
	 */
	public String getError_description() {
		return error_description;
	}


	/**
	 * @param error_description the error_description to set
	 */
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}


	/**
	 * @return the createtime
	 */
	public Long getCreatetime() {
		return createtime;
	}


	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}


	/**
	 * @return the intent
	 */
	public String getIntent() {
		return intent;
	}


	/**
	 * @param intent the intent to set
	 */
	public void setIntent(String intent) {
		this.intent = intent;
	}


	/**
	 * @return the acquirertrxref
	 */
	public String getAcquirertrxref() {
		return acquirertrxref;
	}


	/**
	 * @param acquirertrxref the acquirertrxref to set
	 */
	public void setAcquirertrxref(String acquirertrxref) {
		this.acquirertrxref = acquirertrxref;
	}


	/**
	 * @return the tomember
	 */
	public String getTomember() {
		return tomember;
	}


	/**
	 * @param tomember the tomember to set
	 */
	public void setTomember(String tomember) {
		this.tomember = tomember;
	}


	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
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
	 * @return the payerinfo
	 */
	public PayerInfo getPayerinfo() {
		return payerinfo;
	}


	/**
	 * @param payerinfo the payerinfo to set
	 */
	public void setPayerinfo(PayerInfo payerinfo) {
		this.payerinfo = payerinfo;
	}


	/**
	 * @return the billList
	 */
	public List<TrustBill> getBillList() {
		return billList;
	}


	/**
	 * @param billList the billList to set
	 */
	public void setBillList(List<TrustBill> billList) {
		this.billList = billList;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}


	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrustPaymentResponse [error=" + error + ", error_description=" + error_description + ", createtime="
				+ createtime + ", intent=" + intent + ", acquirertrxref=" + acquirertrxref + ", tomember=" + tomember
				+ ", amount=" + amount + ", currency=" + currency + ", payerinfo=" + payerinfo + ", billList="
				+ billList + ", description=" + description + ", state=" + state + "]";
	}
    
}
