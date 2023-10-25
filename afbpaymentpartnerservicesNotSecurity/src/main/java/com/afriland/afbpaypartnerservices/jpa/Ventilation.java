package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

@Entity
@Table(name="PAYPART_VENTILA")
public class Ventilation implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now()+RandomStringUtils.randomAlphanumeric(10).toUpperCase());
	}
	
	@Id
	private String id;
	
	/**
	 * reference
	 */
	@Column(name = "REFERENCE", unique = true)
	private String reference ; 
	
	
	/**
	 * partenaire
	 */
	@Column(name = "parte")
	private String partenaire;
	
	
	/**
	 * Nom du Fichier
	 */
	@Column(name = "FILENAME")
	private String fileName ; 
		
	
	/**
	 * Date de creation du Fichier
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATECREATE")
	private Date dateCreation = new Date();
	
	/**
	 * Date Comptable
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_COMP")
	private Date dateComptable;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private FpstatutFileTransaction status = FpstatutFileTransaction.ENCOURS;
	
	/**
	 * Determine si le fichier est definitevement valide
	 */
	@Column(name = "INTEGRATION")
	private Boolean integration = Boolean.FALSE;
	
	/**
	 * Nombre de ligne
	 */
	@Column(name = "NBRELIGNE")
	private Integer nbrLigne ;
	
	/**
	 * Total Debit
	 */
	@Column(name = "TOTAL_DEBIT")
	private Double totalDebit = 0d;  
	
	/**
	 * Total Credit 
	 */
	@Column(name = "TOTAL_CREDIT")
	private Double totalCredit = 0d; 
	
	/**
	 * Message d'erreur
	 */
	@Column(name = "ERROR_MSG")
	private String errorMsg ;
	
	@Column(name = "USER_TRAITEMENT")
	private String user;
	
	

	public Ventilation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ventilation(String reference, String partenaire, String fileName, Date dateCreation, Date dateComptable,
			FpstatutFileTransaction status, Boolean integration, Integer nbrLigne, Double totalDebit,
			Double totalCredit, String errorMsg, String user) {
		super();
		this.reference = reference;
		this.partenaire = partenaire;
		this.fileName = fileName;
		this.dateCreation = dateCreation;
		this.dateComptable = dateComptable;
		this.status = status;
		this.integration = integration;
		this.nbrLigne = nbrLigne;
		this.totalDebit = totalDebit;
		this.totalCredit = totalCredit;
		this.errorMsg = errorMsg;
		this.user = user;
		
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}
