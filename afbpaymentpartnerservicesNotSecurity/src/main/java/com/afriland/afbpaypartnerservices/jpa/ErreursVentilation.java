package com.afriland.afbpaypartnerservices.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.FpstatutFileTransaction;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PAYPART_VENTILA_ERR")
@Data
public class ErreursVentilation {
	
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
	 * parent des lignes
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT")
	private Ventilation parent;
	
	/**
	 * Nom du Fichier
	 */
	@Column(name = "VENT_LINE", length=512)
	private String ligne ;
	
	
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
	 * nom beneficiaire
	 */
	@Column(name = "NOM_BENEF")
	private String nomBeneficiaire ; 
	
	
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
	private FpstatutFileTransaction status;
	
	
	/**
	 * motif
	 */
	@Column(name = "MOTIF", length=512)
	private String motif ; 
	
	/**
	 * Sens de l'operation
	 */
	@Column(name = "SENS", nullable=false)
	private String sens;
	
	
	public ErreursVentilation() {
		super();
	}
	
	public ErreursVentilation(String referenceVentilation, Ventilation parent, Double montant, String devise,
			String nomBeneficiaire, String ncpSender, String ncpBeneficiaire, FpstatutFileTransaction status,
			String motif, String ligne, String sens) {
		super();
		this.referenceVentilation = referenceVentilation;
		this.parent = parent;
		this.montant = montant;
		this.devise = devise;
		this.nomBeneficiaire = nomBeneficiaire;
		this.ncpSender = ncpSender;
		this.ncpBeneficiaire = ncpBeneficiaire;
		this.status = status;
		this.motif = motif;
		this.ligne = ligne;
		this.sens = sens;
	}
	
}
