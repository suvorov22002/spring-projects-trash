package com.afriland.afbpaypartnerservices.bill;


import java.io.Serializable;
import java.util.Date;

public class BillModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateOper;

	private String codeClient;

	private String nomClient;

	private String adressClient;

	private String pays;

	private String region;

	private String tellerNumber;

	private String transid;

	private String compCode;

	private String payDoc;

	private String fiscalYear;

	private String status;

	private Double montant;

	private String currency;

	private String parentATCDoc;

	private String orderStatus;

	private String codeBar;

	private String paycode;

	public void setDateOper(Date dateOper) {
		this.dateOper = dateOper;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public void setAdressClient(String adressClient) {
		this.adressClient = adressClient;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setTellerNumber(String tellerNumber) {
		this.tellerNumber = tellerNumber;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public void setPayDoc(String payDoc) {
		this.payDoc = payDoc;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setParentATCDoc(String parentATCDoc) {
		this.parentATCDoc = parentATCDoc;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setCodeBar(String codeBar) {
		this.codeBar = codeBar;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public String toString() {
		return "BillModel(dateOper=" + getDateOper() + ", codeClient=" + getCodeClient() + ", nomClient=" + getNomClient() + ", adressClient=" + getAdressClient() + ", pays=" + getPays() + ", region=" + getRegion() + ", tellerNumber=" + getTellerNumber() + ", transid=" + getTransid() + ", compCode=" + getCompCode() + ", payDoc=" + getPayDoc() + ", fiscalYear=" + getFiscalYear() + ", status=" + getStatus() + ", montant=" + getMontant() + ", currency=" + getCurrency() + ", parentATCDoc=" + getParentATCDoc() + ", orderStatus=" + getOrderStatus() + ", codeBar=" + getCodeBar() + ", paycode=" + getPaycode() + ")";
	}

	public BillModel() {}

	public BillModel(Date dateOper, String codeClient, String nomClient, String adressClient, String pays, String region, String tellerNumber, String transid, String compCode, String payDoc, String fiscalYear, String status, Double montant, String currency, String parentATCDoc, String orderStatus, String codeBar, String paycode) {
		this.dateOper = dateOper;
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.adressClient = adressClient;
		this.pays = pays;
		this.region = region;
		this.tellerNumber = tellerNumber;
		this.transid = transid;
		this.compCode = compCode;
		this.payDoc = payDoc;
		this.fiscalYear = fiscalYear;
		this.status = status;
		this.montant = montant;
		this.currency = currency;
		this.parentATCDoc = parentATCDoc;
		this.orderStatus = orderStatus;
		this.codeBar = codeBar;
		this.paycode = paycode;
	}


	public Date getDateOper() {
		return this.dateOper;
	}

	public String getCodeClient() {
		return this.codeClient;
	}

	public String getNomClient() {
		return this.nomClient;
	}

	public String getAdressClient() {
		return this.adressClient;
	}

	public String getPays() {
		return this.pays;
	}

	public String getRegion() {
		return this.region;
	}

	public String getTellerNumber() {
		return this.tellerNumber;
	}

	public String getTransid() {
		return this.transid;
	}

	public String getCompCode() {
		return this.compCode;
	}

	public String getPayDoc() {
		return this.payDoc;
	}

	public String getFiscalYear() {
		return this.fiscalYear;
	}

	public String getStatus() {
		return this.status;
	}

	public Double getMontant() {
		return this.montant;
	}

	public String getCurrency() {
		return this.currency;
	}

	public String getParentATCDoc() {
		return this.parentATCDoc;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public String getCodeBar() {
		return this.codeBar;
	}

	public String getPaycode() {
		return this.paycode;
	}
}
