package com.afriland.afbpaypartnerservices.dtos;

/**
 * Dto du token d'authentification de la banque chez le partenaire
 * @author alex_jaza
 *
 */
@com.fasterxml.jackson.annotation.JsonAutoDetect
public class BillInfoResponse {
	
	private String operation_id;
    private int type_reason;
    private String bl;
    private String release_date;
    private String final_number;
    private String payment_ref;
    private String payment_mode;
    private String payment_date;
    private Double amount;
    private Double amount_tax;
    private Double amount_total;
    private String nui;
    private String typeFacture;
    private String id_facture;
    
   

    
    /**
	 * 
	 */
	public BillInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param operation_id
	 * @param type_reason
	 * @param bl
	 * @param release_date
	 * @param final_number
	 * @param payment_ref
	 * @param payment_mode
	 * @param payment_date
	 * @param amount
	 * @param amount_tax
	 * @param amount_total
	 */
	public BillInfoResponse(String operation_id, int type_reason, String bl, String release_date, String final_number,
			String payment_ref, String payment_mode, String payment_date, Double amount, Double amount_tax,
			Double amount_total) {
		super();
		this.operation_id = operation_id;
		this.type_reason = type_reason;
		this.bl = bl;
		this.release_date = release_date;
		this.final_number = final_number;
		this.payment_ref = payment_ref;
		this.payment_mode = payment_mode;
		this.payment_date = payment_date;
		this.amount = amount;
		this.amount_tax = amount_tax;
		this.amount_total = amount_total;
	}


	/**
	 * @return the operation_id
	 */
	public String getOperation_id() {
		return operation_id;
	}


	/**
	 * @param operation_id the operation_id to set
	 */
	public void setOperation_id(String operation_id) {
		this.operation_id = operation_id;
	}


	/**
	 * @return the type_reason
	 */
	public int getType_reason() {
		return type_reason;
	}


	/**
	 * @param type_reason the type_reason to set
	 */
	public void setType_reason(int type_reason) {
		this.type_reason = type_reason;
	}


	/**
	 * @return the bl
	 */
	public String getBl() {
		return bl;
	}


	
	
	
	public String getId_facture() {
		return id_facture;
	}


	public void setId_facture(String id_facture) {
		this.id_facture = id_facture;
	}


	/**
	 * @param bl the bl to set
	 */
	public void setBl(String bl) {
		this.bl = bl;
	}


	/**
	 * @return the release_date
	 */
	public String getRelease_date() {
		return release_date;
	}


	/**
	 * @param release_date the release_date to set
	 */
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}


	/**
	 * @return the final_number
	 */
	public String getFinal_number() {
		return final_number;
	}


	/**
	 * @param final_number the final_number to set
	 */
	public void setFinal_number(String final_number) {
		this.final_number = final_number;
	}


	/**
	 * @return the payment_ref
	 */
	public String getPayment_ref() {
		return payment_ref;
	}


	/**
	 * @param payment_ref the payment_ref to set
	 */
	public void setPayment_ref(String payment_ref) {
		this.payment_ref = payment_ref;
	}


	/**
	 * @return the payment_mode
	 */
	public String getPayment_mode() {
		return payment_mode;
	}


	/**
	 * @param payment_mode the payment_mode to set
	 */
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}


	/**
	 * @return the payment_date
	 */
	public String getPayment_date() {
		return payment_date;
	}


	/**
	 * @param payment_date the payment_date to set
	 */
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
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
	 * @return the amount_tax
	 */
	public Double getAmount_tax() {
		return amount_tax;
	}


	/**
	 * @param amount_tax the amount_tax to set
	 */
	public void setAmount_tax(Double amount_tax) {
		this.amount_tax = amount_tax;
	}


	/**
	 * @return the amount_total
	 */
	public Double getAmount_total() {
		return amount_total;
	}


	/**
	 * @param amount_total the amount_total to set
	 */
	public void setAmount_total(Double amount_total) {
		this.amount_total = amount_total;
	}



	public String getNui() {
		return nui;
	}


	public void setNui(String nui) {
		this.nui = nui;
	}


	public String getTypeFacture() {
		return typeFacture;
	}


	public void setTypeFacture(String typeFacture) {
		this.typeFacture = typeFacture;
	}


	/************** SPRINT PAY *************************/
	
//	public BillInfoResponse(BillResponse dto) {
//		if(dto.getRefDeclaration() == null) this.final_number = dto.getNumeroImposition();
//		if(dto.getNumeroImposition() == null) this.final_number = dto.getRefDeclaration();
//		if(dto.getTypeEmission().contentEquals("AVIS")) {
//			this.amount_total = Double.valueOf(dto.getMontantapaye());
//		}
//		else {
//			this.amount_total = Double.valueOf(dto.getMontantImputation());
//		}
//		this.typeFacture = dto.getTypeEmission();
//		this.nui = dto.getNiu();
//		this.release_date = dto.getCreatedDate();
//		this.id_facture = dto.getId();
//		
//	}


	/************************ SPRINT PAY *****************/
        
}