package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.afriland.afbpaypartnerservices.enums.PayPartnerLangue;
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
@JsonIgnoreProperties({"valid", "uticancel", "txtphones", "txtaccounts", "txtemails", "datecancel", "errorsMsg", "version","serviceNumber", "serviceid", "operator", "payItemId", "payItemDescr", "amountType", "localCur", "amountLocalCur", "customerReference", "startDate", "dueDate", "endDate", "optStrg", "optNmb"})
public class Operators implements Serializable{
	
	@Transient
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
	private String cni;
	
	@Column
	private String clientName;
	
	@Column(name = "CUST_REF_PART")
	private String customerReferencePartner; 

	@Column(name = "CUST_NAME_PART")
	private String customerNamePartner;
	
	@Column(name = "CUST_CNI_PART")
	private String customerCniPartner;
	
	@Column(name = "CUST_PHONE_PART")
	private String customerPhonePartner;
	
	@Column(name = "CUST_NIU_PART")
	private String customerNiuPartner;
	
	@Column
	private String keyOperator;
		
	@Column
	private String emails;

	@Column
	private String agenceSous;
	
	@Column
	private String libelleAgenceSous;
	
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
	private String nameUtiunitiate;
	
	@Column
	private String utimodif;

	@Column
	private String nameUtimodif;

	@Column
	private String utivalidate;
	
	@Column
	private String nameUtivalidate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datevalidate;

	@Column
	@Enumerated(EnumType.STRING)
	private PayPartnerStatutSubscriber operatorStatus;
	
	@Column
	@Enumerated(EnumType.STRING)
	private PayPartnerLangue langue = PayPartnerLangue.FR;
	
	@Column
	private String uticancel;
	
	@Column
	private String nameUticancel;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecancel;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validfrom;
	
	@Column
	private String phones;
	
	@Column
	private String accounts;
	
	/**
	 * division Administratif
	 */
	@Column
	private String divisionAdministratif;
	
	/**
	 * activite
	 */
	@Column
	private String activity;
	
	@Column(length = 2000)
	private String adresseOperator;
	

	@Version
	private Integer version;
	
	
	
	@Transient
	private String errorsMsg;
	
	@Transient
	private String accountPartner;
	
	@Transient
	private String selectedAccount = "";

	@Transient
	private String validToString;

	@Transient
	private String validFromString;
	
	
	@Transient
	private List<String> txtPhones = new ArrayList<String>();

	@Transient
	private List<String> txtAccounts = new ArrayList<String>();


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
	 * @return the cni
	 */
	public String getCni() {
		return cni;
	}


	/**
	 * @param cni the cni to set
	 */
	public void setCni(String cni) {
		this.cni = cni;
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
	 * @return the customerPhonePartner
	 */
	public String getCustomerPhonePartner() {
		return customerPhonePartner;
	}


	/**
	 * @param customerPhonePartner the customerPhonePartner to set
	 */
	public void setCustomerPhonePartner(String customerPhonePartner) {
		this.customerPhonePartner = customerPhonePartner;
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
	 * @return the emails
	 */
	public String getEmails() {
		return emails;
	}


	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String emails) {
		this.emails = emails;
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
	 * @return the libelleAgenceSous
	 */
	public String getLibelleAgenceSous() {
		return libelleAgenceSous;
	}


	/**
	 * @param libelleAgenceSous the libelleAgenceSous to set
	 */
	public void setLibelleAgenceSous(String libelleAgenceSous) {
		this.libelleAgenceSous = libelleAgenceSous;
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
	 * @return the partnerName
	 */
	public String getPartnerName() {
		return partnerName;
	}


	/**
	 * @param partnerName the partnerName to set
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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
	 * @return the nameUtiunitiate
	 */
	public String getNameUtiunitiate() {
		return nameUtiunitiate;
	}


	/**
	 * @param nameUtiunitiate the nameUtiunitiate to set
	 */
	public void setNameUtiunitiate(String nameUtiunitiate) {
		this.nameUtiunitiate = nameUtiunitiate;
	}


	/**
	 * @return the utimodif
	 */
	public String getUtimodif() {
		return utimodif;
	}


	/**
	 * @param utimodif the utimodif to set
	 */
	public void setUtimodif(String utimodif) {
		this.utimodif = utimodif;
	}


	/**
	 * @return the nameUtimodif
	 */
	public String getNameUtimodif() {
		return nameUtimodif;
	}


	/**
	 * @param nameUtimodif the nameUtimodif to set
	 */
	public void setNameUtimodif(String nameUtimodif) {
		this.nameUtimodif = nameUtimodif;
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
	 * @return the nameUtivalidate
	 */
	public String getNameUtivalidate() {
		return nameUtivalidate;
	}


	/**
	 * @param nameUtivalidate the nameUtivalidate to set
	 */
	public void setNameUtivalidate(String nameUtivalidate) {
		this.nameUtivalidate = nameUtivalidate;
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
	 * @param operatorStatus the operatorStatus to set
	 */
	public void setOperatorStatus(PayPartnerStatutSubscriber operatorStatus) {
		this.operatorStatus = operatorStatus;
	}


	/**
	 * @return the langue
	 */
	public PayPartnerLangue getLangue() {
		return langue;
	}


	/**
	 * @param langue the langue to set
	 */
	public void setLangue(PayPartnerLangue langue) {
		this.langue = langue;
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
	 * @return the nameUticancel
	 */
	public String getNameUticancel() {
		return nameUticancel;
	}


	/**
	 * @param nameUticancel the nameUticancel to set
	 */
	public void setNameUticancel(String nameUticancel) {
		this.nameUticancel = nameUticancel;
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
	 * @return the validFrom
	 */
	public Date getValidfrom() {
		return validfrom;
	}


	/**
	 * @param validFrom the validFrom to set
	 */
	public void setValidfrom(Date validFrom) {
		this.validfrom = validFrom;
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
	 * @return the phones
	 */
	public String getPhones() {
		return phones;
	}


	/**
	 * @param phones the phones to set
	 */
	public void setPhones(String phones) {
		this.phones = phones;
	}


	/**
	 * @return the accounts
	 */
	public String getAccounts() {
		return accounts;
	}


	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	
	/**
	 * @return the divisionAdministratif
	 */
	public String getDivisionAdministratif() {
		return divisionAdministratif;
	}

	/**
	 * @param divisionAdministratif the divisionAdministratif to set
	 */
	public void setDivisionAdministratif(String divisionAdministratif) {
		this.divisionAdministratif = divisionAdministratif;
	}

	/**
	 * @return the activite
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activite the activite to set
	 */
	public void setActivity(String activite) {
		this.activity = activite;
	}

	/**
	 * @return the adresseOperator
	 */
	public String getAdresseOperator() {
		return adresseOperator;
	}

	/**
	 * @param adresseOperator the adresseOperator to set
	 */
	public void setAdresseOperator(String adresseOperator) {
		this.adresseOperator = adresseOperator;
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


	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}
	
	

	/**
	 * @return the accountPartner
	 */
	public String getAccountPartner() {
		return accountPartner;
	}


	/**
	 * @param accountPartner the accountPartner to set
	 */
	public void setAccountPartner(String accountPartner) {
		this.accountPartner = accountPartner;
	}
	
	
	/**
	 * @return the selectedAccount
	 */
	public String getSelectedAccount() {
		return selectedAccount;
	}


	/**
	 * @param selectedAccount the selectedAccount to set
	 */
	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}


	/**
	 * @return the validToString
	 */
	public String getValidToString() {
		return validToString;
	}


	/**
	 * @param validToString the validToString to set
	 */
	public void setValidToString(String validToString) {
		this.validToString = this.validFromString = validTo != null ? new SimpleDateFormat("ddMMyyyy").format(validTo) : "";
	}


	/**
	 * @return the validfromString
	 */
	public String getValidFromString() {
		return validFromString;
	}


	/**
	 * @param validfromString the validfromString to set
	 */
	public void setValidFromString(String validfromString) {
		this.validFromString = validfrom != null ? new SimpleDateFormat("ddMMyyyy").format(validfrom) : "";
	}


	/**
	 * @return the txtPhones
	 */
	public List<String> getTxtPhones() {
		List<String> phoneCustomers = new ArrayList<String>();
		if(StringUtils.isNotBlank(this.phones)) {
			for(String s : this.phones.split("/")) {
				phoneCustomers.add(s);
			}
		}		
		return phoneCustomers;
	}


	/**
	 * @param txtPhones the txtPhones to set
	 */
	public void setTxtPhones(List<String> txtPhones) {
		this.txtPhones = txtPhones;
	}


	/**
	 * @return the txtAccounts
	 */
	public List<String> getTxtAccounts() {
		List<String> accountCustomers = new ArrayList<String>();
		if(StringUtils.isNotBlank(this.phones)) {
			for(String s : this.accounts.split("/")) {
				accountCustomers.add(s);
			}
		}
		return accountCustomers;
	}


	/**
	 * @param txtAccounts the txtAccounts to set
	 */
	public void setTxtAccounts(List<String> txtAccounts) {
		this.txtAccounts = txtAccounts;
	}
	
}
