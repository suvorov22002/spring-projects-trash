package com.afriland.afbpaypartnerservices.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class UnpaidNotice {
	
    private String noticeId;
    private String noticeNumber;
    private String notificationDate;
    private String noticeType;
    private String referenceNumber;
    private String declarationType;
    private String taxPayerNumber;
    private String taxPayerName;
    private String taxPayerRepresentativeNumber;
    private String taxPayerRepresentativeCode;
    private String taxPayerRepresentativeName;
    private String issuerOffice;
    private String dueDate;
    private BigDecimal noticeAmount;
    private List<Beneficiaire> beneficiaryList;
	
    
    /**
	 * 
	 */
	public UnpaidNotice() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param noticeId
	 * @param noticeNumber
	 * @param notificationDate
	 * @param noticeType
	 * @param referenceNumber
	 * @param declarationType
	 * @param taxPayerNumber
	 * @param taxPayerName
	 * @param taxPayerRepresentativeNumber
	 * @param taxPayerRepresentativeCode
	 * @param taxPayerRepresentativeName
	 * @param issuerOffice
	 * @param dueDate
	 * @param noticeAmount
	 * @param beneficiaryList
	 */
	public UnpaidNotice(String noticeId, String noticeNumber, String notificationDate, String noticeType, String referenceNumber,
			String declarationType, String taxPayerNumber, String taxPayerName, String taxPayerRepresentativeNumber,
			String taxPayerRepresentativeCode, String taxPayerRepresentativeName, String issuerOffice, String dueDate,
			BigDecimal noticeAmount, List<Beneficiaire> beneficiaryList) {
		super();
		this.noticeId = noticeId;
		this.noticeNumber = noticeNumber;
		this.notificationDate = notificationDate;
		this.noticeType = noticeType;
		this.referenceNumber = referenceNumber;
		this.declarationType = declarationType;
		this.taxPayerNumber = taxPayerNumber;
		this.taxPayerName = taxPayerName;
		this.taxPayerRepresentativeNumber = taxPayerRepresentativeNumber;
		this.taxPayerRepresentativeCode = taxPayerRepresentativeCode;
		this.taxPayerRepresentativeName = taxPayerRepresentativeName;
		this.issuerOffice = issuerOffice;
		this.dueDate = dueDate;
		this.noticeAmount = noticeAmount;
		this.beneficiaryList = beneficiaryList;
	}
	/**
	 * @return the noticeId
	 */
	public String getNoticeId() {
		return noticeId;
	}
	/**
	 * @param noticeId the noticeId to set
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * @return the noticeNumber
	 */
	public String getNoticeNumber() {
		return noticeNumber;
	}
	/**
	 * @param noticeNumber the noticeNumber to set
	 */
	public void setNoticeNumber(String noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	/**
	 * @return the notificationDate
	 */
	public String getNotificationDate() {
		return notificationDate;
	}
	/**
	 * @param notificationDate the notificationDate to set
	 */
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
	/**
	 * @return the noticeType
	 */
	public String getNoticeType() {
		return noticeType;
	}
	/**
	 * @param noticeType the noticeType to set
	 */
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	/**
	 * @return the referenceNumber
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
	/**
	 * @param referenceNumber the referenceNumber to set
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	/**
	 * @return the declarationType
	 */
	public String getDeclarationType() {
		return declarationType;
	}
	/**
	 * @param declarationType the declarationType to set
	 */
	public void setDeclarationType(String declarationType) {
		this.declarationType = declarationType;
	}
	/**
	 * @return the taxPayerNumber
	 */
	public String getTaxPayerNumber() {
		return taxPayerNumber;
	}
	/**
	 * @param taxPayerNumber the taxPayerNumber to set
	 */
	public void setTaxPayerNumber(String taxPayerNumber) {
		this.taxPayerNumber = taxPayerNumber;
	}
	/**
	 * @return the taxPayerName
	 */
	public String getTaxPayerName() {
		return taxPayerName;
	}
	/**
	 * @param taxPayerName the taxPayerName to set
	 */
	public void setTaxPayerName(String taxPayerName) {
		this.taxPayerName = taxPayerName;
	}
	/**
	 * @return the taxPayerRepresentativeNumber
	 */
	public String getTaxPayerRepresentativeNumber() {
		return taxPayerRepresentativeNumber;
	}
	/**
	 * @param taxPayerRepresentativeNumber the taxPayerRepresentativeNumber to set
	 */
	public void setTaxPayerRepresentativeNumber(String taxPayerRepresentativeNumber) {
		this.taxPayerRepresentativeNumber = taxPayerRepresentativeNumber;
	}
	/**
	 * @return the taxPayerRepresentativeCode
	 */
	public String getTaxPayerRepresentativeCode() {
		return taxPayerRepresentativeCode;
	}
	/**
	 * @param taxPayerRepresentativeCode the taxPayerRepresentativeCode to set
	 */
	public void setTaxPayerRepresentativeCode(String taxPayerRepresentativeCode) {
		this.taxPayerRepresentativeCode = taxPayerRepresentativeCode;
	}
	/**
	 * @return the taxPayerRepresentativeName
	 */
	public String getTaxPayerRepresentativeName() {
		return taxPayerRepresentativeName;
	}
	/**
	 * @param taxPayerRepresentativeName the taxPayerRepresentativeName to set
	 */
	public void setTaxPayerRepresentativeName(String taxPayerRepresentativeName) {
		this.taxPayerRepresentativeName = taxPayerRepresentativeName;
	}
	/**
	 * @return the issuerOffice
	 */
	public String getIssuerOffice() {
		return issuerOffice;
	}
	/**
	 * @param issuerOffice the issuerOffice to set
	 */
	public void setIssuerOffice(String issuerOffice) {
		this.issuerOffice = issuerOffice;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the noticeAmount
	 */
	public BigDecimal getNoticeAmount() {
		return noticeAmount;
	}
	/**
	 * @param noticeAmount the noticeAmount to set
	 */
	public void setNoticeAmount(BigDecimal noticeAmount) {
		this.noticeAmount = noticeAmount;
	}
	/**
	 * @return the beneficiaryList
	 */
	public List<Beneficiaire> getBeneficiaryList() {
		return beneficiaryList;
	}
	/**
	 * @param beneficiaryList the beneficiaryList to set
	 */
	public void setBeneficiaryList(List<Beneficiaire> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AP [noticeId=" + noticeId + ", noticeNumber=" + noticeNumber + ", notificationDate=" + notificationDate
				+ ", noticeType=" + noticeType + ", referenceNumber=" + referenceNumber + ", declarationType="
				+ declarationType + ", taxPayerNumber=" + taxPayerNumber + ", taxPayerName=" + taxPayerName
				+ ", taxPayerRepresentativeNumber=" + taxPayerRepresentativeNumber + ", taxPayerRepresentativeCode="
				+ taxPayerRepresentativeCode + ", taxPayerRepresentativeName=" + taxPayerRepresentativeName
				+ ", issuerOffice=" + issuerOffice + ", dueDate=" + dueDate + ", noticeAmount=" + noticeAmount
				+ ", beneficiaryList=" + beneficiaryList + "]";
	}
    
    
}
