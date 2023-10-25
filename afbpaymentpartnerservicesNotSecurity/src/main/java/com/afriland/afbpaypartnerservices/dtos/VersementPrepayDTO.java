package com.afriland.afbpaypartnerservices.dtos;


import java.util.Date;

import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.jpa.Bkeve;


public class VersementPrepayDTO {

	/**
	 * type Operation
	 */
	private String typeOperation;
	/**
	 * date Operation
	 */
	private Date dateOpe;
	/**
	 * date Comptable
	 */
	private Date dateComptable;
	/**
	 * numero d'evenement
	 */
	private String numEve; 
	/**
	 * compte a crediter
	 */
	private String creditAccount; //compte a crediter

	/**
	 * montant donne par le client a la caissiere
	 */
	private double montantPercu = 0d; // montant donne par le client a la caissiere
	/**
	 * montant a verser par le client
	 */
	private double montantVerse = 0d; // montant ttc a payer par le client

	/**
	 * montant a remettre au client
	 */
	private double montantRemb = 0d;
	/**
	 * reference
	 */
	private String reference; //
	/**
	 * status
	 */
	private TransactionStatus status;
	/**
	 * code du partenaire
	 */
	private String partnerCode;
	/**
	 * id Marchand
	 */
	private String referenceOperator;
	/**
	 * nom Marchand
	 */
	private String nomOperator;
	/**
	 * login caissiere
	 */
	private String userCaisse; // login caissiere
	/**
	 * agence caissiere 
	 */
	private String ageCaisse; // agence caissiere 
	
	private String libelleAgence;
	/**
	 * libelle operation
	 */
	private String lib; //libelle operation
	
	private String codeReason;

	/**
	 * raison du versement
	 */
	private String reason; // raison du versement

	/**
	 * nom de la partie versante
	 */
	private String partieVersante; //nom de la partie versante

	/**
	 * numero de telephone de la partie versante
	 */
	private String telephone; // numero de telephone de la partie versante

	/**
	 * reference Commande
	 */
	private String referenceCommande;

	private String dateDebutString;

	private String dateFinString;
	
	private String errorsMsg;
	
	private String usermail ;
	
	private String details;
	
	
	private String segment;
		
	private String typeJustificatif;
	
	private String categorieJustificatif;
	
	private String divisionAdministratif;
	
	private String activity;
	
	private String adresseOperator;
	
	
	
	/**
	 * 
	 */
	public VersementPrepayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param typeOperation
	 * @param dateOpe
	 * @param dateComptable
	 * @param numEve
	 * @param creditAccount
	 * @param montantPercu
	 * @param montantVerse
	 * @param montantRemb
	 * @param reference
	 * @param status
	 * @param partnerCode
	 * @param idMarchand
	 * @param nomMarchand
	 * @param userCaisse
	 * @param ageCaisse
	 * @param lib
	 * @param reason
	 * @param partieVersante
	 * @param telephone
	 * @param referenceCommande
	 */
	public VersementPrepayDTO(String typeOperation, Date dateOpe, Date dateComptable, String numEve,
			String creditAccount, double montantPercu, double montantVerse, double montantRemb, String reference,
			TransactionStatus status, String partnerCode, String idMarchand, String nomMarchand, String userCaisse,
			String ageCaisse, String lib, String reason, String partieVersante, String telephone,
			String referenceCommande) {
		super();
		this.typeOperation = typeOperation;
		this.dateOpe = dateOpe;
		this.dateComptable = dateComptable;
		this.numEve = numEve;
		this.creditAccount = creditAccount;
		this.montantPercu = montantPercu;
		this.montantVerse = montantVerse;
		this.montantRemb = montantRemb;
		this.reference = reference;
		this.status = status;
		this.partnerCode = partnerCode;
		this.referenceOperator = idMarchand;
		this.nomOperator = nomMarchand;
		this.userCaisse = userCaisse;
		this.ageCaisse = ageCaisse;
		this.lib = lib;
		this.reason = reason;
		this.partieVersante = partieVersante;
		this.telephone = telephone;
		this.referenceCommande = referenceCommande;
	}

	
	public VersementPrepayDTO(Bkeve eve) {
		super();
		this.typeOperation = eve.getOpe();
		this.dateOpe = eve.getDsai();
		this.dateComptable = eve.getDco();
		this.numEve = eve.getEve();
		this.creditAccount = eve.getAge2()+"-"+eve.getNcp2()+"-"+eve.getClc2();
		this.montantPercu = eve.getMon2();
		this.montantVerse = eve.getMon2();
		this.montantRemb = 0d;
		this.reference = eve.getEve();
		this.status = TransactionStatus.SUCCESS;
		this.userCaisse = eve.getUti();
		this.ageCaisse = eve.getAge();
		this.lib = eve.getLib1();
		this.reason = eve.getLib1();
		this.partieVersante = eve.getLib1();
		this.telephone = "";
		this.referenceCommande = eve.getEve();
	}


	/**
	 * @return the usermail
	 */
	public String getUsermail() {
		return usermail;
	}


	/**
	 * @param usermail the usermail to set
	 */
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}


	/**
	 * @param typeOperation
	 * @param dateOpe
	 * @param dateComptable
	 * @param numEve
	 * @param creditAccount
	 * @param montantPercu
	 * @param montantVerse
	 * @param montantRemb
	 * @param reference
	 * @param status
	 * @param partnerCode
	 * @param idMarchand
	 * @param nomMarchand
	 * @param userCaisse
	 * @param ageCaisse
	 * @param lib
	 * @param reason
	 * @param partieVersante
	 * @param telephone
	 * @param referenceCommande
	 * @param dateDebutString
	 * @param dateFinString
	 */
	public VersementPrepayDTO(String typeOperation, Date dateOpe, Date dateComptable, String numEve,
			String creditAccount, double montantPercu, double montantVerse, double montantRemb, String reference,
			TransactionStatus status, String partnerCode, String idMarchand, String nomMarchand, String userCaisse,
			String ageCaisse, String lib, String reason, String partieVersante, String telephone,
			String referenceCommande, String dateDebutString, String dateFinString) {
		super();
		this.typeOperation = typeOperation;
		this.dateOpe = dateOpe;
		this.dateComptable = dateComptable;
		this.numEve = numEve;
		this.creditAccount = creditAccount;
		this.montantPercu = montantPercu;
		this.montantVerse = montantVerse;
		this.montantRemb = montantRemb;
		this.reference = reference;
		this.status = status;
		this.partnerCode = partnerCode;
		this.referenceOperator = idMarchand;
		this.nomOperator = nomMarchand;
		this.userCaisse = userCaisse;
		this.ageCaisse = ageCaisse;
		this.lib = lib;
		this.reason = reason;
		this.partieVersante = partieVersante;
		this.telephone = telephone;
		this.referenceCommande = referenceCommande;
		this.dateDebutString = dateDebutString;
		this.dateFinString = dateFinString;
	}


	/**
	 * @return the typeOperation
	 */
	public String getTypeOperation() {
		return typeOperation;
	}


	/**
	 * @param typeOperation the typeOperation to set
	 */
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}


	/**
	 * @return the dateOpe
	 */
	public Date getDateOpe() {
		return dateOpe;
	}


	/**
	 * @param dateOpe the dateOpe to set
	 */
	public void setDateOpe(Date dateOpe) {
		this.dateOpe = dateOpe;
	}


	/**
	 * @return the dateComptable
	 */
	public Date getDateComptable() {
		return dateComptable;
	}


	/**
	 * @param dateComptable the dateComptable to set
	 */
	public void setDateComptable(Date dateComptable) {
		this.dateComptable = dateComptable;
	}


	/**
	 * @return the numEve
	 */
	public String getNumEve() {
		return numEve;
	}


	/**
	 * @param numEve the numEve to set
	 */
	public void setNumEve(String numEve) {
		this.numEve = numEve;
	}


	/**
	 * @return the creditAccount
	 */
	public String getCreditAccount() {
		return creditAccount;
	}


	/**
	 * @param creditAccount the creditAccount to set
	 */
	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}


	/**
	 * @return the montantPercu
	 */
	public double getMontantPercu() {
		return montantPercu;
	}


	/**
	 * @param montantPercu the montantPercu to set
	 */
	public void setMontantPercu(double montantPercu) {
		this.montantPercu = montantPercu;
	}


	/**
	 * @return the montantVerse
	 */
	public double getMontantVerse() {
		return montantVerse;
	}


	/**
	 * @param montantVerse the montantVerse to set
	 */
	public void setMontantVerse(double montantVerse) {
		this.montantVerse = montantVerse;
	}


	/**
	 * @return the montantRemb
	 */
	public double getMontantRemb() {
		return montantRemb;
	}


	/**
	 * @param montantRemb the montantRemb to set
	 */
	public void setMontantRemb(double montantRemb) {
		this.montantRemb = montantRemb;
	}


	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}


	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}


	/**
	 * @return the status
	 */
	public TransactionStatus getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(TransactionStatus status) {
		this.status = status;
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
	 * @return the referenceOperator
	 */
	public String getReferenceOperator() {
		return referenceOperator;
	}


	/**
	 * @param referenceOperator the referenceOperator to set
	 */
	public void setReferenceOperator(String referenceOperator) {
		this.referenceOperator = referenceOperator;
	}


	/**
	 * @return the nomOperator
	 */
	public String getNomOperator() {
		return nomOperator;
	}


	/**
	 * @param nomOperator the nomOperator to set
	 */
	public void setNomOperator(String nomOperator) {
		this.nomOperator = nomOperator;
	}


	/**
	 * @return the userCaisse
	 */
	public String getUserCaisse() {
		return userCaisse;
	}


	/**
	 * @param userCaisse the userCaisse to set
	 */
	public void setUserCaisse(String userCaisse) {
		this.userCaisse = userCaisse;
	}


	/**
	 * @return the ageCaisse
	 */
	public String getAgeCaisse() {
		return ageCaisse;
	}


	/**
	 * @param ageCaisse the ageCaisse to set
	 */
	public void setAgeCaisse(String ageCaisse) {
		this.ageCaisse = ageCaisse;
	}


	/**
	 * @return the libelleAgence
	 */
	public String getLibelleAgence() {
		return libelleAgence;
	}


	/**
	 * @param libelleAgence the libelleAgence to set
	 */
	public void setLibelleAgence(String libelleAgence) {
		this.libelleAgence = libelleAgence;
	}


	/**
	 * @return the lib
	 */
	public String getLib() {
		return lib;
	}


	/**
	 * @param lib the lib to set
	 */
	public void setLib(String lib) {
		this.lib = lib;
	}

	/**
	 * @return the codeReason
	 */
	public String getCodeReason() {
		return codeReason;
	}

	/**
	 * @param codeReason the codeReason to set
	 */
	public void setCodeReason(String codeReason) {
		this.codeReason = codeReason;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}


	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/**
	 * @return the partieVersante
	 */
	public String getPartieVersante() {
		return partieVersante;
	}


	/**
	 * @param partieVersante the partieVersante to set
	 */
	public void setPartieVersante(String partieVersante) {
		this.partieVersante = partieVersante;
	}


	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}


	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	/**
	 * @return the referenceCommande
	 */
	public String getReferenceCommande() {
		return referenceCommande;
	}


	/**
	 * @param referenceCommande the referenceCommande to set
	 */
	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}


	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}


	/**
	 * @return the segment
	 */
	public String getSegment() {
		return segment;
	}


	/**
	 * @param segment the segment to set
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}


	/**
	 * @return the typeJustificatif
	 */
	public String getTypeJustificatif() {
		return typeJustificatif;
	}


	/**
	 * @param typeJustificatif the typeJustificatif to set
	 */
	public void setTypeJustificatif(String typeJustificatif) {
		this.typeJustificatif = typeJustificatif;
	}


	/**
	 * @return the categorieJustificatif
	 */
	public String getCategorieJustificatif() {
		return categorieJustificatif;
	}


	/**
	 * @param categorieJustificatif the categorieJustificatif to set
	 */
	public void setCategorieJustificatif(String categorieJustificatif) {
		this.categorieJustificatif = categorieJustificatif;
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
	
}


