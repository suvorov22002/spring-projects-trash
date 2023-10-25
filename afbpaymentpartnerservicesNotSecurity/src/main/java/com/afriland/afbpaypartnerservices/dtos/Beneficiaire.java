package com.afriland.afbpaypartnerservices.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Beneficiaire {
	
    private String beneficiaryName;
    private String beneficiaryCode;
    private String bankCode;
    private String accountNumber;
    private BigDecimal amount;

    
    /**
	 * 
	 */
	public Beneficiaire() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param beneficiaryName
	 * @param beneficiaryCode
	 * @param bankCode
	 * @param accountNumber
	 * @param amount
	 */
	public Beneficiaire(String beneficiaryName, String beneficiaryCode, String bankCode, String accountNumber,
			BigDecimal amount) {
		super();
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryCode = beneficiaryCode;
		this.bankCode = bankCode;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}


	/**
	 * @return the beneficiaryName
	 */
	public String getBeneficiaryName() {
		return beneficiaryName;
	}


	/**
	 * @param beneficiaryName the beneficiaryName to set
	 */
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}


	/**
	 * @return the beneficiaryCode
	 */
	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}


	/**
	 * @param beneficiaryCode the beneficiaryCode to set
	 */
	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
	}


	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}


	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}


	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Beneficiaire [beneficiaryName=" + beneficiaryName + ", beneficiaryCode=" + beneficiaryCode
				+ ", bankCode=" + bankCode + ", accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
    
}
