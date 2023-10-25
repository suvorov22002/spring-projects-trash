package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yves_labo
 *
 */
public class SubscriberPartnerDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String codeClient;

	private String clientName;

	private String customerReferencePartner; 

	private String customerNamePartner; 

	private String customerCniPartner;

	private String customerNiuPartner;

	private String partnerCode;

	private List<String> phones = new ArrayList<String>();

	private List<String> accounts = new ArrayList<String>();

	private List<String> emails = new ArrayList<String>();

	private String uti;

	private String agence;

	
	/**
	 * @param codeClient
	 * @param clientName
	 * @param customerReferencePartner
	 * @param customerNamePartner
	 * @param customerCniPartner
	 * @param customerNiuPartner
	 * @param partnerCode
	 * @param phones
	 * @param accounts
	 * @param emails
	 * @param uti
	 * @param agence
	 */
	public SubscriberPartnerDto(String codeClient, String clientName, String customerReferencePartner,
			String customerNamePartner, String customerCniPartner, String customerNiuPartner, String partnerCode,
			List<String> phones, List<String> accounts, List<String> emails, String uti, String agence) {
		super();
		this.codeClient = codeClient;
		this.clientName = clientName;
		this.customerReferencePartner = customerReferencePartner;
		this.customerNamePartner = customerNamePartner;
		this.customerCniPartner = customerCniPartner;
		this.customerNiuPartner = customerNiuPartner;
		this.partnerCode = partnerCode;
		this.phones = phones;
		this.accounts = accounts;
		this.emails = emails;
		this.uti = uti;
		this.agence = agence;
	}
	
	

	/**
	 * @return the codeClient
	 */
	public String getCodeClient() {
		return codeClient;
	}

	/**
	 * @param codeClient the codeClient to set
	 */
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the customerReferencePartner
	 */
	public String getCustomerReferencePartner() {
		return customerReferencePartner;
	}

	/**
	 * @param customerReferencePartner the customerReferencePartner to set
	 */
	public void setCustomerReferencePartner(String customerReferencePartner) {
		this.customerReferencePartner = customerReferencePartner;
	}

	/**
	 * @return the customerNamePartner
	 */
	public String getCustomerNamePartner() {
		return customerNamePartner;
	}

	/**
	 * @param customerNamePartner the customerNamePartner to set
	 */
	public void setCustomerNamePartner(String customerNamePartner) {
		this.customerNamePartner = customerNamePartner;
	}

	/**
	 * @return the customerCniPartner
	 */
	public String getCustomerCniPartner() {
		return customerCniPartner;
	}

	/**
	 * @param customerCniPartner the customerCniPartner to set
	 */
	public void setCustomerCniPartner(String customerCniPartner) {
		this.customerCniPartner = customerCniPartner;
	}

	/**
	 * @return the customerNiuPartner
	 */
	public String getCustomerNiuPartner() {
		return customerNiuPartner;
	}

	/**
	 * @param customerNiuPartner the customerNiuPartner to set
	 */
	public void setCustomerNiuPartner(String customerNiuPartner) {
		this.customerNiuPartner = customerNiuPartner;
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

	/**
	 * @return the phones
	 */
	public List<String> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	/**
	 * @return the accounts
	 */
	public List<String> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the emails
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	/**
	 * @return the uti
	 */
	public String getUti() {
		return uti;
	}

	/**
	 * @param uti the uti to set
	 */
	public void setUti(String uti) {
		this.uti = uti;
	}

	/**
	 * @return the agence
	 */
	public String getAgence() {
		return agence;
	}

	/**
	 * @param agence the agence to set
	 */
	public void setAgence(String agence) {
		this.agence = agence;
	}

	
}
