package com.afriland.afbpaypartnerservices.models;

public class SubscriptionResponseDto {

	private String customerName;
	private String customerNumber;
	private String name;
	
	
	public SubscriptionResponseDto(SubscriptionResponse s) {
		super();
		if(s.getCustomerName() != null) this.customerName = s.getCustomerName().trim();
		if(s.getCustomerNumber() != null)this.customerNumber = s.getCustomerNumber().trim();
		if(s.getName() != null) this.name = s.getName().trim();
	}


	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}


	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
