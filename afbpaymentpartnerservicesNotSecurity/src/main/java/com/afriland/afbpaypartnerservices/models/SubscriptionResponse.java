package com.afriland.afbpaypartnerservices.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
//@JsonIgnoreProperties({"serviceNumber", "serviceid", "operator", "payItemId", "payItemDescr", "amountType", "localCur", "amountLocalCur", "customerReference", "startDate", "dueDate", "endDate", "optStrg", "optNmb"})
public class SubscriptionResponse {
	@Column
	private String serviceNumber;
	@Column
	private String serviceid;
	@Column
	private String operator;
	@Column
	private String payItemId;
	@Column
	private String payItemDescr;
	@Column
	private String amountType;
	@Column
	private String name;
	@Column
	private String localCur;
	@Column
	private Integer amountLocalCur;
	@Column
	private String customerReference;
	@Column
	private String customerName;
	@Column
	private String customerNumber;
	@Column
	private String startDate;
	@Column
	private String dueDate;
	@Column
	private String endDate;
	@Column
	private String optStrg;
	@Column
	private Integer optNmb;


	public SubscriptionResponse(SubscriptionResponse s) {
		super();
		if(s.getServiceNumber() != null) this.serviceNumber = s.getServiceNumber().trim();
		if(s.getServiceid() != null) this.serviceid = s.getServiceid().trim();
		this.operator = s.getOperator();
		this.payItemId = s.getPayItemId();
		this.payItemDescr = s.getPayItemDescr();
		this.amountType = s.getAmountType();
		this.name = s.getName();
		this.localCur = s.getLocalCur();
		this.amountLocalCur = s.getAmountLocalCur();
		this.customerReference = s.getCustomerReference();
		this.customerName = s.getCustomerName();
		if(s.getCustomerNumber() != null) this.customerNumber = s.getCustomerNumber().trim();
		this.startDate = s.getStartDate();
		this.dueDate = s.getDueDate();
		this.endDate = s.getEndDate();
		this.optStrg = s.getOptStrg();
		this.optNmb = s.getOptNmb();
	}
    
	
	public void initSubscriptionResponse(SubscriptionResponse s) {
		if(s.getServiceNumber() != null) this.serviceNumber = s.getServiceNumber().trim();
		if(s.getServiceid() != null) this.serviceid = s.getServiceid().trim();
		this.operator = s.getOperator();
		this.payItemId = s.getPayItemId();
		this.payItemDescr = s.getPayItemDescr();
		this.amountType = s.getAmountType();
		this.name = s.getName();
		this.localCur = s.getLocalCur();
		this.amountLocalCur = s.getAmountLocalCur();
		this.customerReference = s.getCustomerReference();
		this.customerName = s.getCustomerName();
		if(s.getCustomerNumber() != null) this.customerNumber = s.getCustomerNumber().trim();
		this.startDate = s.getStartDate();
		this.dueDate = s.getDueDate();
		this.endDate = s.getEndDate();
		this.optStrg = s.getOptStrg();
		this.optNmb = s.getOptNmb();
	}


	/**
	 * @return the serviceNumber
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}


	/**
	 * @param serviceNumber the serviceNumber to set
	 */
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}


	/**
	 * @return the serviceid
	 */
	public String getServiceid() {
		return serviceid;
	}


	/**
	 * @param serviceid the serviceid to set
	 */
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}


	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}


	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}


	/**
	 * @return the payItemId
	 */
	public String getPayItemId() {
		return payItemId;
	}


	/**
	 * @param payItemId the payItemId to set
	 */
	public void setPayItemId(String payItemId) {
		this.payItemId = payItemId;
	}


	/**
	 * @return the payItemDescr
	 */
	public String getPayItemDescr() {
		return payItemDescr;
	}


	/**
	 * @param payItemDescr the payItemDescr to set
	 */
	public void setPayItemDescr(String payItemDescr) {
		this.payItemDescr = payItemDescr;
	}


	/**
	 * @return the amountType
	 */
	public String getAmountType() {
		return amountType;
	}


	/**
	 * @param amountType the amountType to set
	 */
	public void setAmountType(String amountType) {
		this.amountType = amountType;
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


	/**
	 * @return the localCur
	 */
	public String getLocalCur() {
		return localCur;
	}


	/**
	 * @param localCur the localCur to set
	 */
	public void setLocalCur(String localCur) {
		this.localCur = localCur;
	}


	/**
	 * @return the amountLocalCur
	 */
	public Integer getAmountLocalCur() {
		return amountLocalCur;
	}


	/**
	 * @param amountLocalCur the amountLocalCur to set
	 */
	public void setAmountLocalCur(Integer amountLocalCur) {
		this.amountLocalCur = amountLocalCur;
	}


	/**
	 * @return the customerReference
	 */
	public String getCustomerReference() {
		return customerReference;
	}


	/**
	 * @param customerReference the customerReference to set
	 */
	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}


	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}


	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	/**
	 * @return the optStrg
	 */
	public String getOptStrg() {
		return optStrg;
	}


	/**
	 * @param optStrg the optStrg to set
	 */
	public void setOptStrg(String optStrg) {
		this.optStrg = optStrg;
	}


	/**
	 * @return the optNmb
	 */
	public Integer getOptNmb() {
		return optNmb;
	}


	/**
	 * @param optNmb the optNmb to set
	 */
	public void setOptNmb(Integer optNmb) {
		this.optNmb = optNmb;
	}
	
	
}
