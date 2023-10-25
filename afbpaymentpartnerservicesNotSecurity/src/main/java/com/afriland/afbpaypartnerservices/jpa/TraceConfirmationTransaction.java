package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;
import com.afriland.afbpaypartnerservices.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author yves_labo
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYPART_OPERATOR")
@JsonIgnoreProperties({"valid", "uticancel", "txtphones", "txtaccounts", "txtemails", "datecancel", "validTo", "phones", "accounts", "errorsMsg", "version","serviceNumber", "serviceid", "operator", "payItemId", "payItemDescr", "amountType", "localCur", "amountLocalCur", "customerReference", "startDate", "dueDate", "endDate", "optStrg", "optNmb"})
public class TraceConfirmationTransaction implements Serializable{
	
	final String secretKey = "suscriber";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now()+RandomStringUtils.randomAlphanumeric(10).toUpperCase());
		this.code = this.codeClient+"/"+this.partnerCode;		
		this.validfrom = new Date();
	}

	@Id
	private String id;
	
	@Column
	private String code;
	
	@Column
	private String codeClient;
	
	@Column
	private String clientName;
	
	@Column(name = "CUST_REF_PART")
	private String customerReferencePartner; 

	@Column(name = "CUST_NAME_PART")
	private String customerNamePartner;
	
	@Column(name = "CUST_CNI_PART")
	private String customerCniPartner;
	
	@Column(name = "CUST_NIU_PART")
	private String customerNiuPartner;
	
	@Column
	private String keyOperator;
		
	@Column
	private String emails;

	@Column
	private String agenceSous;
	
	@Column
	private String partnerCode;

	@Column
	private String partnerName;

	@Column
	private String customerAddress;

	@Column
	private String customerRegion;

	@Column
	private String customerCountry;

	@Column
	private Boolean valid = Boolean.FALSE;

	@Column
	private String utiunitiate;

	@Column
	private String utivalidate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datevalidate;

	@Column
	@Enumerated(EnumType.STRING)
	private PayPartnerStatutSubscriber operatorStatus;

	@Column
	private String uticancel;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecancel;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validfrom;
	
	@OneToMany( targetEntity=OperatorPhones.class, mappedBy="operator",fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private List<OperatorPhones> phones = new ArrayList<OperatorPhones>();

	@OneToMany( targetEntity=OperatorAccounts.class, mappedBy="operator",fetch = FetchType.LAZY,cascade = CascadeType.ALL )
	private List<OperatorAccounts> accounts = new ArrayList<OperatorAccounts>();

	@Transient
	private String errorsMsg;

	@Version
	private Integer version;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the txtemails
	 */
	public String getEmails() {
		return emails;
	}

	/**
	 * @param txtemails the txtemails to set
	 */
	public void setEmails(String txtemails) {
		this.emails = txtemails;
	}

	/**
	 * @return the agenceSous
	 */
	public String getAgenceSous() {
		return agenceSous;
	}

	/**
	 * @param agenceSous the agenceSous to set
	 */
	public void setAgenceSous(String agenceSous) {
		this.agenceSous = agenceSous;
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
	 * @return the keyOperator
	 */
	public String getKeyOperator() {
		return AfbEncryptionDecryption.decrypt(keyOperator, secretKey);
	}
	
	
	/**
	 * @param keyOperator the keyOperator to set
	 */
	public void setKeyOperator(String keyOperator) {
		this.keyOperator = AfbEncryptionDecryption.encrypt(keyOperator, secretKey);
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
	 * @return the operatorName
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * @param operatorName the operatorName to set
	 */
	public void setPartnerName(String operatorName) {
		this.partnerName = operatorName;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the customerRegion
	 */
	public String getCustomerRegion() {
		return customerRegion;
	}

	/**
	 * @param customerRegion the customerRegion to set
	 */
	public void setCustomerRegion(String customerRegion) {
		this.customerRegion = customerRegion;
	}

	/**
	 * @return the customerCountry
	 */
	public String getCustomerCountry() {
		return customerCountry;
	}

	/**
	 * @param customerCountry the customerCountry to set
	 */
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the utiunitiate
	 */
	public String getUtiunitiate() {
		return utiunitiate;
	}

	/**
	 * @param utiunitiate the utiunitiate to set
	 */
	public void setUtiunitiate(String utiunitiate) {
		this.utiunitiate = utiunitiate;
	}

	/**
	 * @return the utivalidate
	 */
	public String getUtivalidate() {
		return utivalidate;
	}

	/**
	 * @param utivalidate the utivalidate to set
	 */
	public void setUtivalidate(String utivalidate) {
		this.utivalidate = utivalidate;
	}

	/**
	 * @return the datevalidate
	 */
	public Date getDatevalidate() {
		return datevalidate;
	}

	/**
	 * @param datevalidate the datevalidate to set
	 */
	public void setDatevalidate(Date datevalidate) {
		this.datevalidate = datevalidate;
	}

	/**
	 * @return the operatorStatus
	 */
	public PayPartnerStatutSubscriber getOperatorStatus() {
		return operatorStatus;
	}

	/**
	 * @param merStatus the operatorStatus to set
	 */
	public void setOperatorStatus(PayPartnerStatutSubscriber merStatus) {
		this.operatorStatus = merStatus;
	}

	/**
	 * @return the uticancel
	 */
	public String getUticancel() {
		return uticancel;
	}

	/**
	 * @param uticancel the uticancel to set
	 */
	public void setUticancel(String uticancel) {
		this.uticancel = uticancel;
	}

	/**
	 * @return the datecancel
	 */
	public Date getDatecancel() {
		return datecancel;
	}

	/**
	 * @param datecancel the datecancel to set
	 */
	public void setDatecancel(Date datecancel) {
		this.datecancel = datecancel;
	}

	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	/**
	 * @return the validfrom
	 */
	public Date getValidfrom() {
		return validfrom;
	}

	/**
	 * @param validfrom the validfrom to set
	 */
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}

	/**
	 * @return the phones
	 */
	public List<OperatorPhones> getPhones() {
		return phones;
	}

	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<OperatorPhones> phones) {
		this.phones = phones;
	}

	/**
	 * @return the accounts
	 */
	public List<OperatorAccounts> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<OperatorAccounts> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the errorsMsg
	 */
	public String getErrorsMsg() {
		return errorsMsg;
	}

	/**
	 * @param errorsMsg the errorsMsg to set
	 */
	public void setErrorsMsg(String errorsMsg) {
		this.errorsMsg = errorsMsg;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
}
