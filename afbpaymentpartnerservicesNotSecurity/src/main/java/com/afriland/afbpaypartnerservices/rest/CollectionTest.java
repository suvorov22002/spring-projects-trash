package com.afriland.afbpaypartnerservices.rest;

import com.afriland.afbpaypartnerservices.enums.TransactionStatus;

import lombok.Data;

@Data
public class CollectionTest {

	private String customerNumber;
	private String trid;
	private String customerPhonenumber;
	private String customerEmailaddress;
	private TransactionStatus transactionType;
	private Double amount;
	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	/**
	 * @return the trid
	 */
	public String getTrid() {
		return trid;
	}
	/**
	 * @param trid the trid to set
	 */
	public void setTrid(String trid) {
		this.trid = trid;
	}
	/**
	 * @return the customerPhonenumber
	 */
	public String getCustomerPhonenumber() {
		return customerPhonenumber;
	}
	/**
	 * @param customerPhonenumber the customerPhonenumber to set
	 */
	public void setCustomerPhonenumber(String customerPhonenumber) {
		this.customerPhonenumber = customerPhonenumber;
	}
	/**
	 * @return the customerEmailaddress
	 */
	public String getCustomerEmailaddress() {
		return customerEmailaddress;
	}
	/**
	 * @param customerEmailaddress the customerEmailaddress to set
	 */
	public void setCustomerEmailaddress(String customerEmailaddress) {
		this.customerEmailaddress = customerEmailaddress;
	}
	/**
	 * @return the transactionType
	 */
	public TransactionStatus getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(TransactionStatus transactionType) {
		this.transactionType = transactionType;
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

}
