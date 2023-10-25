package com.afriland.afbpaypartnerservices.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PAYPART_VENTILA_DET")
@Data
@NoArgsConstructor
public class DetailsVentilation {
	
	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now()+RandomStringUtils.randomAlphanumeric(10).toUpperCase());
	}
	
	@Id
	private String id;
	/**
	 * reference ventilation
	 */
	@Column(name = "REF_VENTILA")
	private String referenceVentilation ; 
	
	
	/**
	 * reference transaction
	 */
	@Column(name = "REF_TRANS")
	private String referenceTransaction ; 
	
	
	/**
	 * nom beneficiaire
	 */
	@Column(name = "NOM_BENEF")
	private String nomBeneficiaire ; 
	
	
	/**
	 * parent des lignes
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT")
	private Ventilation parent;
	
	
	/**
	 * montant 
	 */
	@Column(name = "MONTANT")
	private Double montant; 
	
	
	/**
	 * devise
	 */
	@Column(name = "DEVISE")
	private String devise ; 
	
	
	/**
	 * Date Comptable
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_COMP")
	private Date dateComptable = new Date();
	
	
	/**
	 * NCP Sender
	 */
	@Column(name = "NCP_SENDER")
	private String ncpSender ;
	
	
	/**
	 * NCP beneficiaire
	 */
	@Column(name = "NCP_BENEF")
	private String ncpBeneficiaire ;	
	
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private FpstatutFileTransaction status = FpstatutFileTransaction.ENCOURS;
	
	
	/**
	 * Nom du Fichier
	 */
	@Column(name = "VENT_LINE", length=512)
	private String ligne ;
		
	/**
	 * Sens de l'operation
	 */
	@Column(name = "SENS", nullable=false)
	private String sens;
	
	/**
	 * motif
	 */
	@Column(name = "MOTIF", length=512)
	private String motif ; 
	
	/**
	 * @param referenceVentilation
	 * @param referenceTransaction
	 * @param nomBeneficiaire
	 * @param parent
	 * @param montant
	 * @param devise
	 * @param dateComptable
	 * @param ncpSender
	 * @param ncpBeneficiaire
	 * @param status
	 * @param ligne
	 */
	public DetailsVentilation(String referenceVentilation, String referenceTransaction, String nomBeneficiaire, Ventilation parent,
			Double montant, String devise, Date dateComptable, String ncpSender, String ncpBeneficiaire,
			FpstatutFileTransaction status, String ligne, String sens, String motif) {
		super();
		this.referenceVentilation = referenceVentilation;
		this.referenceTransaction = referenceTransaction;
		this.nomBeneficiaire = nomBeneficiaire;
		this.parent = parent;
		this.montant = montant;
		this.devise = devise;
		this.dateComptable = dateComptable;
		this.ncpSender = ncpSender;
		this.ncpBeneficiaire = ncpBeneficiaire;
		this.status = status;
		this.ligne = ligne;
		this.sens = sens;
		this.motif = motif;
	}
}
