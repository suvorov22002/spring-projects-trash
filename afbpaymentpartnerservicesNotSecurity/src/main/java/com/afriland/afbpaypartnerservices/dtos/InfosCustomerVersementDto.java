package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

public class InfosCustomerVersementDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String partnerCode;
	
	private String referenceOperator;
	
	private String nomOperator;	
	
	private String segment;
		
	private String typeJustificatif;
	
	private String categorieJustificatif;
	
	private String divisionAdministratif;
	
	private String activity;
	
	private String adresseOperator;
	
	
	
	/**
	 * 
	 */
	public InfosCustomerVersementDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param partnerCode
	 * @param referenceOperator
	 * @param nomOperator
	 * @param segment
	 * @param typeJustificatif
	 * @param categorieJustificatif
	 * @param divisionAdministratif
	 * @param activite
	 * @param adresseOperator
	 */
	public InfosCustomerVersementDto(String partnerCode, String referenceOperator, String nomOperator, String segment,
			String typeJustificatif, String categorieJustificatif, String divisionAdministratif, String activite,
			String adresseOperator) {
		super();
		this.partnerCode = partnerCode;
		this.referenceOperator = referenceOperator;
		this.nomOperator = nomOperator;
		this.segment = segment;
		this.typeJustificatif = typeJustificatif;
		this.categorieJustificatif = categorieJustificatif;
		this.divisionAdministratif = divisionAdministratif;
		this.activity = activite;
		this.adresseOperator = adresseOperator;
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


