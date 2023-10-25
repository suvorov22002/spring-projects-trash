package com.afriland.afbpaypartnerservices.jpa;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCode;
import com.afriland.afbpaypartnerservices.enums.TransactionMode;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.models.CollectionResponse;
import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;
import com.afriland.afbpaypartnerservices.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author yves_labo
 *
 */
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "PAYPART_TRANS")
@JsonIgnoreProperties({"traceCollectionResponse", "traceCollectionRequest", "traceQuoteRequest", "traceQuoteResponse", "postPartner", "postComplete", "postComplete","validTo","version","tracePaymentStatus","timestamp", "agentBalance", "receiptNumber", "priceSystemCur", "priceLocalCur", "localCur", "systemCur","ptn","veriCode","trid","pin","status","payItemId","payItemDescr"})
public class Transactions extends CollectionResponse{
	
	@Transient
	final String secretKey = "payment";
	
	public String makeId(){
		return DateUtil.now() + this.partnerCode + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
	}
	
	public void generateNewTri(){
		setTrid(makeId());
	}

	@PostConstruct
	public void PostConstruct() {
		this.id = makeId();
		this.validfrom = new Date();
	}
	
	
	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now() + RandomStringUtils.randomAlphanumeric(15).toUpperCase());
		this.validfrom = new Date();
	}
	
	
	@Id
	private String id;
	
	@Column(length = 2000)
	private String tracePaymentStatus;
	@Column(length = 2000)
	private String traceCollectionResponse;
	@Column(length = 2000)
	private String traceCollectionRequest;
	@Column(length = 2000)
	private String traceQuoteRequest;
	@Column(length = 2000)
	private String traceQuoteResponse;
	
	@Column
	private String reference;
		
	@Column
	private String partnerTrxID;
	
	/**
	 * Operation
	 */
	@Column(name = "OP", nullable = false)
	private String typeOperation;
	
	
	@Column(name = "opestatusTrans")
	@Enumerated(EnumType.STRING)
	private TransactionStatus statusTrans; 
	
	@Column(name = "transactionMode")
	@Enumerated(EnumType.STRING)
	private TransactionMode transactionMode;
	
	/**
	 * code operation
	 */
	@Column(name = "OPE")
	private String ope;

	
	/**
	 * Montant
	 */
	@Column(name = "AMOUNT", nullable = false)
	private Double amount = 0d;
	
	
	/**
	 * Total des commissions de la transaction
	 */
	@Column(name = "COMMISSIONS", nullable = false)
	private Double commissions = 0d;

	/**
	 * Montant TTC
	 */
	@Column(name = "TTC", nullable = false)
	private Double ttc = 0d;
	
	/**
	 * Numero de compte
	 */
	@Column(name = "ACCOUNTD", nullable = false)
	private String accountDebit;
	

	/**
	 * Numero de compte
	 */
	@Column(name = "ACCOUNTC", nullable = false)
	private String accountCredit;

	/**
	 * segment
	 */
	@Column
	private String segment;
		
	/**
	 * type Justificatif
	 */
	@Column
	private String typeJustificatif;
	
	/**
	 * categorie Justificatif
	 */
	@Column
	private String categorieJustificatif;
	
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
	
		
	
	/**
	 * id Operator
	 */
	@Column
	private String idOperator;
	
	/**
	 * reference Operator
	 */
	@Column
	private String referenceOperator;
		
	/**
	 * nom Operator
	 */
	@Column
	private String nomOperator;
	
	
	/**
	 * nom de la partie versante
	 */
	@Column
	private String partieVersante; //nom de la partie versante

	/**
	 * numero de telephone de la partie versante
	 */
	@Column
	private String telephone;
	
	/**
	 * code du partenaire
	 */
	@Column
	private String partnerCode;
	
	/**
	* raison du d'annulation
	*/
	@Column(length = 500)
	private String cancelReason; 
	
	/**
	* raison du d'annulation
	*/
	@Column
	private String usercancel;
	
	@Column
	private String ressourcecancel;
	
	@Column(name = "date_cancel")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCancel;
		
	/**
	 * login caissiere
	 */
	@Column
	private String userCaisse; // login caissiere
	
	/**
	 * agence caissiere 
	 */
	@Column
	private String ageCaisse; // agence caissiere 
	
	@Column
	private String libelleAgence;
		
	@Column(name = "rfnb")
	private String referencenumber;
	
	@Column(name = "rfnbill")
	private String referenceBill;
	
	@Column(name = "eveid")
	private String eveid;
	
	@Column(name = "usermail")
	private String usermail ;
	
	@Column(name = "postCBS")
	private Boolean postCBS = Boolean.FALSE;
	
	@Column(name = "postPartner")
	private Boolean postPartner = Boolean.FALSE;
	
	@Column(name = "postComplete")
	private Boolean postComplete = Boolean.FALSE;
	
	@Column
	private Date validTo;
	
	@Column
	private Date validfrom;
	
	/*@Version
	private Integer version;
	*/
	@Column
	private String codeReason; 
	
	@Column
	private String reason; 
	
	/**
	 * Details de l'operation
	 */
	@Column(name = "DETAILS", nullable = true, length=5000)
	private String details;

	@Column
	private Date dco;
	
	/** secureOTP */
	@Column(name = "otp")
	private Boolean otp = Boolean.FALSE;
	
	@Column(name = "secureOTP")
	private String secureOTP;

	@Column
	private Double amountReceived = 0d;
	
	//*** @Column(length = 2000)
	//*** private String errorsMsg;
			
	@Column(name = "exceptionlib")
	private String exceptionlib;

	@Column(name = "execeptionCode")
	@Enumerated(EnumType.STRING)
	private PayPartnerExceptionCode exceptionCode;
	
	
	@Column(name = "comment", length = 1000)
	private String comment;
	
	@Column(name = "user_confirm")
	private String userConfirmation;
	
	@Column(name = "date_confirm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateConfirmation;
	
	
	@Transient
	private String reportdata;
	
	@Transient
	private Double taxes;
	
	

	public Transactions() {
		super();
		PostConstruct();
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
	 * @return the tracePaymentStatus
	 */
	public String getTracePaymentStatus() {
		return tracePaymentStatus;
	}

	/**
	 * @param tracePaymentStatus the tracePaymentStatus to set
	 */
	public void setTracePaymentStatus(String tracePaymentStatus) {
		this.tracePaymentStatus = tracePaymentStatus;
	}

	/**
	 * @return the traceCollectionResponse
	 */
	public String getTraceCollectionResponse() {
		return traceCollectionResponse;
	}

	/**
	 * @param traceCollectionResponse the traceCollectionResponse to set
	 */
	public void setTraceCollectionResponse(String traceCollectionResponse) {
		this.traceCollectionResponse = traceCollectionResponse;
	}

	/**
	 * @return the traceCollectionRequest
	 */
	public String getTraceCollectionRequest() {
		return traceCollectionRequest;
	}

	/**
	 * @param traceCollectionRequest the traceCollectionRequest to set
	 */
	public void setTraceCollectionRequest(String traceCollectionRequest) {
		this.traceCollectionRequest = traceCollectionRequest;
	}

	/**
	 * @return the traceQuoteRequest
	 */
	public String getTraceQuoteRequest() {
		return traceQuoteRequest;
	}

	/**
	 * @param traceQuoteRequest the traceQuoteRequest to set
	 */
	public void setTraceQuoteRequest(String traceQuoteRequest) {
		this.traceQuoteRequest = traceQuoteRequest;
	}

	/**
	 * @return the traceQuoteResponse
	 */
	public String getTraceQuoteResponse() {
		return traceQuoteResponse;
	}

	/**
	 * @param traceQuoteResponse the traceQuoteResponse to set
	 */
	public void setTraceQuoteResponse(String traceQuoteResponse) {
		this.traceQuoteResponse = traceQuoteResponse;
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
	 * @return the partnerTrxID
	 */
	public String getPartnerTrxID() {
		return partnerTrxID;
	}

	/**
	 * @param partnerTrxID the partnerTrxID to set
	 */
	public void setPartnerTrxID(String partnerTrxID) {
		this.partnerTrxID = partnerTrxID;
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
	 * @return the statusTrans
	 */
	public TransactionStatus getStatusTrans() {
		return statusTrans;
	}

	/**
	 * @param statusTrans the statusTrans to set
	 */
	public void setStatusTrans(TransactionStatus statusTrans) {
		this.statusTrans = statusTrans;
	}

	/**
	 * @return the transactionMode
	 */
	public TransactionMode getTransactionMode() {
		return transactionMode;
	}

	/**
	 * @param transactionMode the transactionMode to set
	 */
	public void setTransactionMode(TransactionMode transactionMode) {
		this.transactionMode = transactionMode;
	}

	/**
	 * @return the ope
	 */
	public String getOpe() {
		return ope;
	}

	/**
	 * @param ope the ope to set
	 */
	public void setOpe(String ope) {
		this.ope = ope;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the commissions
	 */
	public Double getCommissions() {
		return commissions;
	}

	/**
	 * @param commissions the commissions to set
	 */
	public void setCommissions(Double commissions) {
		this.commissions = commissions;
	}

	/**
	 * @return the ttc
	 */
	public Double getTtc() {
		return ttc;
	}

	/**
	 * @param ttc the ttc to set
	 */
	public void setTtc(Double ttc) {
		this.ttc = ttc;
	}

	/**
	 * @return the accountDebit
	 */
	public String getAccountDebit() {
		return accountDebit;
	}

	/**
	 * @param accountDebit the accountDebit to set
	 */
	public void setAccountDebit(String accountDebit) {
		this.accountDebit = accountDebit;
	}

	/**
	 * @return the accountCredit
	 */
	public String getAccountCredit() {
		return accountCredit;
	}

	/**
	 * @param accountCredit the accountCredit to set
	 */
	public void setAccountCredit(String accountCredit) {
		this.accountCredit = accountCredit;
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

	/**
	 * @return the idOperator
	 */
	public String getIdOperator() {
		return idOperator;
	}

	/**
	 * @param idOperator the idOperator to set
	 */
	public void setIdOperator(String idOperator) {
		this.idOperator = idOperator;
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
	 * @return the cancelReason
	 */
	public String getCancelReason() {
		return cancelReason;
	}

	/**
	 * @param cancelReason the cancelReason to set
	 */
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * @return the usercancel
	 */
	public String getUsercancel() {
		return usercancel;
	}

	/**
	 * @param usercancel the usercancel to set
	 */
	public void setUsercancel(String usercancel) {
		this.usercancel = usercancel;
	}

	/**
	 * @return the ressourcecancel
	 */
	public String getRessourcecancel() {
		return ressourcecancel;
	}

	/**
	 * @param ressourcecancel the ressourcecancel to set
	 */
	public void setRessourcecancel(String ressourcecancel) {
		this.ressourcecancel = ressourcecancel;
	}

	/**
	 * @return the dateCancel
	 */
	public Date getDateCancel() {
		return dateCancel;
	}

	/**
	 * @param dateCancel the dateCancel to set
	 */
	public void setDateCancel(Date dateCancel) {
		this.dateCancel = dateCancel;
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
		return StringUtils.isNotBlank(libelleAgence) ? libelleAgence : "Hippodrome";
	}

	/**
	 * @param libelleAgence the libelleAgence to set
	 */
	public void setLibelleAgence(String libelleAgence) {
		this.libelleAgence = libelleAgence;
	}

	/**
	 * @return the referencenumber
	 */
	public String getReferencenumber() {
		return referencenumber;
	}

	/**
	 * @param referencenumber the referencenumber to set
	 */
	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber;
	}

	/**
	 * @return the referenceBill
	 */
	public String getReferenceBill() {
		return referenceBill;
	}

	/**
	 * @param referenceBill the referenceBill to set
	 */
	public void setReferenceBill(String referenceBill) {
		this.referenceBill = referenceBill;
	}

	/**
	 * @return the eveid
	 */
	public String getEveid() {
		return eveid;
	}

	/**
	 * @param eveid the eveid to set
	 */
	public void setEveid(String eveid) {
		this.eveid = eveid;
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
	 * @return the postCBS
	 */
	public Boolean getPostCBS() {
		return postCBS;
	}

	/**
	 * @param postCBS the postCBS to set
	 */
	public void setPostCBS(Boolean postCBS) {
		this.postCBS = postCBS;
	}

	/**
	 * @return the postPartner
	 */
	public Boolean getPostPartner() {
		return postPartner;
	}

	/**
	 * @param postPartner the postPartner to set
	 */
	public void setPostPartner(Boolean postPartner) {
		this.postPartner = postPartner;
	}

	/**
	 * @return the postComplete
	 */
	public Boolean getPostComplete() {
		return postComplete;
	}

	/**
	 * @param postComplete the postComplete to set
	 */
	public void setPostComplete(Boolean postComplete) {
		this.postComplete = postComplete;
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
	public Date getValidfrom() {
		return validfrom;
	}

	/**
	 * @param validfrom the validfrom to set
	 */
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
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
	 * @return the dco
	 */
	public Date getDco() {
		return dco;
	}

	/**
	 * @param dco the dco to set
	 */
	public void setDco(Date dco) {
		this.dco = dco;
	}

	/**
	 * @return the otp
	 */
	public Boolean getOtp() {
		return otp;
	}

	/**
	 * @param otp the otp to set
	 */
	public void setOtp(Boolean otp) {
		this.otp = otp;
	}

	/**
	 * @return the amountReceived
	 */
	public Double getAmountReceived() {
		return amountReceived;
	}

	/**
	 * @param amountReceived the amountReceived to set
	 */
	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}
	
	/*

	public String getErrorsMsg() {
		return errorsMsg;
	}

	public void setErrorsMsg(String errorsMsg) {
		this.errorsMsg = errorsMsg;
	}
	*/

	/**
	 * @return the reportdata
	 */
	public String getReportdata() {
		return reportdata;
	}

	/**
	 * @param reportdata the reportdata to set
	 */
	public void setReportdata(String reportdata) {
		this.reportdata = reportdata;
	}

	/**
	 * @return the exceptionlib
	 */
	public String getExceptionlib() {
		return exceptionlib;
	}

	/**
	 * @param exceptionlib the exceptionlib to set
	 */
	public void setExceptionlib(String exceptionlib) {
		this.exceptionlib = exceptionlib;
	}

	/**
	 * @return the exceptionCode
	 */
	public PayPartnerExceptionCode getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * @param exceptionCode the exceptionCode to set
	 */
	public void setExceptionCode(PayPartnerExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the userConfirmation
	 */
	public String getUserConfirmation() {
		return userConfirmation;
	}

	/**
	 * @param userConfirmation the userConfirmation to set
	 */
	public void setUserConfirmation(String userConfirmation) {
		this.userConfirmation = userConfirmation;
	}

	/**
	 * @return the dateConfirmation
	 */
	public Date getDateConfirmation() {
		return dateConfirmation;
	}

	/**
	 * @param dateConfirmation the dateConfirmation to set
	 */
	public void setDateConfirmation(Date dateConfirmation) {
		this.dateConfirmation = dateConfirmation;
	}
	
	/**
	 * @return the secureOTP
	 */
	public String getSecureOTP() {
		return secureOTP!=null ? AfbEncryptionDecryption.decrypt(secureOTP, secretKey) : secureOTP;
	}


	/**
	 * @param secureOTP the secureOTP to set
	 */
	public void setSecureOTP(String secureOTP) {
		this.secureOTP = secureOTP!=null ? AfbEncryptionDecryption.encrypt(secureOTP, secretKey) : secureOTP;
	}

	/**
	 * @return the taxes
	 */
	public Double getTaxes() {
		return taxes;
	}

	/**
	 * @param taxes the taxes to set
	 */
	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

}
