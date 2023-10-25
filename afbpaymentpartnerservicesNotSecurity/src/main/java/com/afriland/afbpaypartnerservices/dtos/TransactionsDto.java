package com.afriland.afbpaypartnerservices.dtos;

import java.util.Date;

import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCode;
import com.afriland.afbpaypartnerservices.enums.TransactionMode;
import com.afriland.afbpaypartnerservices.enums.TransactionStatus;
import com.afriland.afbpaypartnerservices.models.CollectionResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author yves_labo
 *
 */
@JsonIgnoreProperties({"traceCollectionResponse", "traceCollectionRequest", "traceQuoteRequest", "traceQuoteResponse", "postPartner", "postComplete", "postComplete","version","tracePaymentStatus","timestamp", "agentBalance", "receiptNumber", "priceSystemCur", "priceLocalCur", "localCur", "systemCur","ptn","veriCode","trid","pin","status","payItemId","payItemDescr"})
public class TransactionsDto extends CollectionResponse{

	private String id;
	
	private String tracePaymentStatus;
	
	private String traceCollectionResponse;

	private String traceCollectionRequest;
	
	private String traceQuoteRequest;
	
	private String traceQuoteResponse;
	
	private String reference;
		
	private String partnerTrxID;
	
	private String typeOperation;
	
	private TransactionStatus statusTrans; 
	
	private TransactionMode transactionMode;
	
	private String ope;

	private Double amount = 0d;
	
	private Double commissions = 0d;

	private Double ttc = 0d;
	
	private String accountDebit;
	
	private String accountCredit;

	private String segment;
		
	private String typeJustificatif;
	
	private String categorieJustificatif;
	
	private String divisionAdministratif;
	
	private String activity;
	
	private String adresseOperator;
	
	private String idOperator;
	
	private String referenceOperator;
		
	private String nomOperator;
	
	private String partieVersante; //nom de la partie versante

	private String telephone;
	
	private String partnerCode;
	
	private String cancelReason; 
	
	private String usercancel; 
		
	private String userCaisse; // login caissiere
	
	private String ageCaisse; // agence caissiere 
	
	private String libelleAgence;
		
	private String referencenumber;
	
	private String referenceBill;
	
	private String eveid;
	
	private String usermail ;
	
	private Boolean postCBS;
	
	private Boolean postPartner;
	
	private Boolean postComplete;
	
	private Date validfrom;
	
	private Date validTo;
		
	private String reason; 
	
	private String details;

	private Date dco;
	
	private Boolean otp;
	
	private String secureOTP;

	private Double amountReceived;
	
	private String errorsMsg;
	
	private String reportdata;
		
	private String exceptionlib;

	private PayPartnerExceptionCode exceptionCode;

	private String comment;

	private String userConfirmation;

	private Date dateConfirmation;


	public TransactionsDto() {
		super();
	}
	
	
	
	/**
	 * @param id
	 * @param tracePaymentStatus
	 * @param traceCollectionResponse
	 * @param traceCollectionRequest
	 * @param traceQuoteRequest
	 * @param traceQuoteResponse
	 * @param reference
	 * @param partnerTrxID
	 * @param typeOperation
	 * @param statusTrans
	 * @param transactionMode
	 * @param ope
	 * @param amount
	 * @param commissions
	 * @param ttc
	 * @param accountDebit
	 * @param accountCredit
	 * @param segment
	 * @param typeJustificatif
	 * @param categorieJustificatif
	 * @param divisionAdministratif
	 * @param activity
	 * @param adresseOperator
	 * @param idOperator
	 * @param referenceOperator
	 * @param nomOperator
	 * @param partieVersante
	 * @param telephone
	 * @param partnerCode
	 * @param cancelReason
	 * @param usercancel
	 * @param userCaisse
	 * @param ageCaisse
	 * @param referencenumber
	 * @param referenceBill
	 * @param eveid
	 * @param usermail
	 * @param postCBS
	 * @param postPartner
	 * @param postComplete
	 * @param validfrom
	 * @param validTo
	 * @param reason
	 * @param details
	 * @param dco
	 * @param otp
	 * @param secureOTP
	 * @param amountReceived
	 * @param errorsMsg
	 * @param reportdata
	 * @param exceptionlib
	 * @param exceptionCode
	 * @param comment
	 * @param userConfirmation
	 * @param dateConfirmation
	 */
	public TransactionsDto(String id, String tracePaymentStatus, String traceCollectionResponse,
			String traceCollectionRequest, String traceQuoteRequest, String traceQuoteResponse, String reference,
			String partnerTrxID, String typeOperation, TransactionStatus statusTrans, TransactionMode transactionMode,
			String ope, Double amount, Double commissions, Double ttc, String accountDebit, String accountCredit,
			String segment, String typeJustificatif, String categorieJustificatif, String divisionAdministratif,
			String activity, String adresseOperator, String idOperator, String referenceOperator, String nomOperator,
			String partieVersante, String telephone, String partnerCode, String cancelReason, String usercancel,
			String userCaisse, String ageCaisse, String referencenumber, String referenceBill, String eveid,
			String usermail, Boolean postCBS, Boolean postPartner, Boolean postComplete, Date validfrom, Date validTo,
			String reason, String details, Date dco, Boolean otp, String secureOTP, Double amountReceived,
			String errorsMsg, String reportdata, String exceptionlib, PayPartnerExceptionCode exceptionCode,
			String comment, String userConfirmation, Date dateConfirmation) {
		super();
		this.id = id;
		this.tracePaymentStatus = tracePaymentStatus;
		this.traceCollectionResponse = traceCollectionResponse;
		this.traceCollectionRequest = traceCollectionRequest;
		this.traceQuoteRequest = traceQuoteRequest;
		this.traceQuoteResponse = traceQuoteResponse;
		this.reference = reference;
		this.partnerTrxID = partnerTrxID;
		this.typeOperation = typeOperation;
		this.statusTrans = statusTrans;
		this.transactionMode = transactionMode;
		this.ope = ope;
		this.amount = amount;
		this.commissions = commissions;
		this.ttc = ttc;
		this.accountDebit = accountDebit;
		this.accountCredit = accountCredit;
		this.segment = segment;
		this.typeJustificatif = typeJustificatif;
		this.categorieJustificatif = categorieJustificatif;
		this.divisionAdministratif = divisionAdministratif;
		this.activity = activity;
		this.adresseOperator = adresseOperator;
		this.idOperator = idOperator;
		this.referenceOperator = referenceOperator;
		this.nomOperator = nomOperator;
		this.partieVersante = partieVersante;
		this.telephone = telephone;
		this.partnerCode = partnerCode;
		this.cancelReason = cancelReason;
		this.usercancel = usercancel;
		this.userCaisse = userCaisse;
		this.ageCaisse = ageCaisse;
		this.referencenumber = referencenumber;
		this.referenceBill = referenceBill;
		this.eveid = eveid;
		this.usermail = usermail;
		this.postCBS = postCBS;
		this.postPartner = postPartner;
		this.postComplete = postComplete;
		this.validfrom = validfrom;
		this.validTo = validTo;
		this.reason = reason;
		this.details = details;
		this.dco = dco;
		this.otp = otp;
		this.secureOTP = secureOTP;
		this.amountReceived = amountReceived;
		this.errorsMsg = errorsMsg;
		this.reportdata = reportdata;
		this.exceptionlib = exceptionlib;
		this.exceptionCode = exceptionCode;
		this.comment = comment;
		this.userConfirmation = userConfirmation;
		this.dateConfirmation = dateConfirmation;
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
		return secureOTP;
	}
	/**
	 * @param secureOTP the secureOTP to set
	 */
	public void setSecureOTP(String secureOTP) {
		this.secureOTP = secureOTP;
	}

}
