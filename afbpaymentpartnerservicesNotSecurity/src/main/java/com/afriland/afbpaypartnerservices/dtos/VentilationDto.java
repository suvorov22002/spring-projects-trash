package com.afriland.afbpaypartnerservices.dtos;

import java.util.Date;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;

public class VentilationDto {
	
	private String reference ; 
	private String partenaire;
	private String fileName; 
	private Date dateCreation = new Date();
	private Date dateComptable;
	private FpstatutFileTransaction status = FpstatutFileTransaction.ENCOURS;
	private Boolean integration = Boolean.FALSE;
	private Integer nbrLigne ;
	private Double totalDebit = 0d;  
	private Double totalCredit = 0d; 
	private String errorMsg ;
	private String user;
	
	
	public VentilationDto() {
		super();
	}
	
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getPartenaire() {
		return partenaire;
	}
	public void setPartenaire(String partenaire) {
		this.partenaire = partenaire;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateComptable() {
		return dateComptable;
	}
	public void setDateComptable(Date dateComptable) {
		this.dateComptable = dateComptable;
	}
	public FpstatutFileTransaction getStatus() {
		return status;
	}
	public void setStatus(FpstatutFileTransaction status) {
		this.status = status;
	}
	public Boolean getIntegration() {
		return integration;
	}
	public void setIntegration(Boolean integration) {
		this.integration = integration;
	}
	public Integer getNbrLigne() {
		return nbrLigne;
	}
	public void setNbrLigne(Integer nbrLigne) {
		this.nbrLigne = nbrLigne;
	}
	public Double getTotalDebit() {
		return totalDebit;
	}
	public void setTotalDebit(Double totalDebit) {
		this.totalDebit = totalDebit;
	}
	public Double getTotalCredit() {
		return totalCredit;
	}
	public void setTotalCredit(Double totalCredit) {
		this.totalCredit = totalCredit;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
