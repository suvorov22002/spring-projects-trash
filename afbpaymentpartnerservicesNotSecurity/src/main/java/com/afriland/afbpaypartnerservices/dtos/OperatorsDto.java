package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.persistence.Version;

import com.afriland.afbpaypartnerservices.enums.PayPartnerStatutSubscriber;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author yves_labo
 *
 */
public class OperatorsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String code;

	private String codeClient;

	private String cni;

	private String clientName;

	private String customerReferencePartner; 

	private String customerNamePartner;

	private String customerCniPartner;

	private String customerPhonePartner;

	private String customerNiuPartner;

	private String keyOperator;

	private String emails;

	private String agenceSous;

	private String libelleAgenceSous;

	private String partnerCode;

	private String partnerName;

	private String customerAddress;

	private String customerRegion;

	private String customerCountry;

	private Boolean valid;

	private String utiunitiate;

	private String nameUtiunitiate;
	
	private String utimodif;

	private String nameUtimodif;

	private String utivalidate;

	private String nameUtivalidate;

	private Date datevalidate;

	private PayPartnerStatutSubscriber operatorStatus;

	private String uticancel;

	private String nameUticancel;

	private Date datecancel;

	private Date validTo;

	private Date validFrom;

	private String phones;

	private String accounts;

	private String divisionAdministratif;

	private String activity;

	private String adresseOperator;

	private List<String> txtPhones = new ArrayList<String>();

	private List<String> txtAccounts = new ArrayList<String>();

	private String bordereauSouscription;


	@Transient
	private String errorsMsg;

	@Version
	private Integer version;

	@JsonIgnore
	private String dateDebutString;
	@JsonIgnore
	private String dateFinString;


	/**
	 * @param id
	 * @param code
	 * @param codeClient
	 * @param cni
	 * @param clientName
	 * @param customerReferencePartner
	 * @param customerNamePartner
	 * @param customerCniPartner
	 * @param customerPhonePartner
	 * @param customerNiuPartner
	 * @param keyOperator
	 * @param emails
	 * @param agenceSous
	 * @param libelleAgenceSous
	 * @param partnerCode
	 * @param partnerName
	 * @param customerAddress
	 * @param customerRegion
	 * @param customerCountry
	 * @param valid
	 * @param utiunitiate
	 * @param nameUtiunitiate
	 * @param utimodif
	 * @param nameUtimodif
	 * @param utivalidate
	 * @param nameUtivalidate
	 * @param datevalidate
	 * @param operatorStatus
	 * @param uticancel
	 * @param nameUticancel
	 * @param datecancel
	 * @param validTo
	 * @param validFrom
	 * @param phones
	 * @param accounts
	 * @param divisionAdministratif
	 * @param activity
	 * @param adresseOperator
	 * @param txtPhones
	 * @param txtAccounts
	 * @param bordereauSouscription
	 * @param errorsMsg
	 * @param version
	 * @param dateDebutString
	 * @param dateFinString
	 */
	public OperatorsDto(String id, String code, String codeClient, String cni, String clientName,
			String customerReferencePartner, String customerNamePartner, String customerCniPartner,
			String customerPhonePartner, String customerNiuPartner, String keyOperator, String emails,
			String agenceSous, String libelleAgenceSous, String partnerCode, String partnerName, String customerAddress,
			String customerRegion, String customerCountry, Boolean valid, String utiunitiate, String nameUtiunitiate,
			String utimodif, String nameUtimodif, String utivalidate, String nameUtivalidate, Date datevalidate,
			PayPartnerStatutSubscriber operatorStatus, String uticancel, String nameUticancel, Date datecancel,
			Date validTo, Date validFrom, String phones, String accounts, String divisionAdministratif, String activity,
			String adresseOperator, List<String> txtPhones, List<String> txtAccounts, String bordereauSouscription,
			String errorsMsg, Integer version, String dateDebutString, String dateFinString) {
		super();
		this.id = id;
		this.code = code;
		this.codeClient = codeClient;
		this.cni = cni;
		this.clientName = clientName;
		this.customerReferencePartner = customerReferencePartner;
		this.customerNamePartner = customerNamePartner;
		this.customerCniPartner = customerCniPartner;
		this.customerPhonePartner = customerPhonePartner;
		this.customerNiuPartner = customerNiuPartner;
		this.keyOperator = keyOperator;
		this.emails = emails;
		this.agenceSous = agenceSous;
		this.libelleAgenceSous = libelleAgenceSous;
		this.partnerCode = partnerCode;
		this.partnerName = partnerName;
		this.customerAddress = customerAddress;
		this.customerRegion = customerRegion;
		this.customerCountry = customerCountry;
		this.valid = valid;
		this.utiunitiate = utiunitiate;
		this.nameUtiunitiate = nameUtiunitiate;
		this.utimodif = utimodif;
		this.nameUtimodif = nameUtimodif;
		this.utivalidate = utivalidate;
		this.nameUtivalidate = nameUtivalidate;
		this.datevalidate = datevalidate;
		this.operatorStatus = operatorStatus;
		this.uticancel = uticancel;
		this.nameUticancel = nameUticancel;
		this.datecancel = datecancel;
		this.validTo = validTo;
		this.validFrom = validFrom;
		this.phones = phones;
		this.accounts = accounts;
		this.divisionAdministratif = divisionAdministratif;
		this.activity = activity;
		this.adresseOperator = adresseOperator;
		this.txtPhones = txtPhones;
		this.txtAccounts = txtAccounts;
		this.bordereauSouscription = bordereauSouscription;
		this.errorsMsg = errorsMsg;
		this.version = version;
		this.dateDebutString = dateDebutString;
		this.dateFinString = dateFinString;
	}


	/**
	 * 
	 */
	public OperatorsDto() {
		super();
		// TODO Auto-generated constructor stub
	}


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
		return keyOperator;
	}


	/**
	 * @param keyOperator the keyOperator to set
	 */
	public void setKeyOperator(String keyOperator) {
		this.keyOperator = keyOperator;
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
	public Date getValidFrom() {
		return validFrom;
	}


	/**
	 * @param validfrom the validfrom to set
	 */
	public void setValidFrom(Date validfrom) {
		this.validFrom = validfrom;
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
	 * @return the txtPhones
	 */
	public List<String> getTxtPhones() {
		return txtPhones;
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
		return txtAccounts;
	}


	/**
	 * @param txtAccounts the txtAccounts to set
	 */
	public void setTxtAccounts(List<String> txtAccounts) {
		this.txtAccounts = txtAccounts;
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
	 * @return the dateDebutString
	 */
	public String getDateDebutString() {
		return dateDebutString;
	}


	/**
	 * @param dateDebutString the dateDebutString to set
	 */
	public void setDateDebutString(String dateDebutString) {
		this.dateDebutString = dateDebutString;
	}


	/**
	 * @return the dateFinString
	 */
	public String getDateFinString() {
		return dateFinString;
	}


	/**
	 * @param dateFinString the dateFinString to set
	 */
	public void setDateFinString(String dateFinString) {
		this.dateFinString = dateFinString;
	}


	/**
	 * @return the bordereauSouscription
	 */
	public String getBordereauSouscription() {
		return bordereauSouscription;
	}


	/**
	 * @param bordereauSouscription the bordereauSouscription to set
	 */
	public void setBordereauSouscription(String bordereauSouscription) {
		this.bordereauSouscription = bordereauSouscription;
	}


}
