package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

import com.afriland.afbpaypartnerservices.models.CollectionResponse.StatusEnum;

/**
 * 
 * @author yves_labo
 *
 */
public class ConfirmationTransDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	private String id;
	
	private String partnerCode;
	
	private String userConfirmation;
	
	private String comment;
	
	private StatusEnum statusConfirmation;
	
	

	/**
	 * 
	 */
	public ConfirmationTransDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param idTransaction
	 * @param partnerCode
	 * @param userConfirmation
	 * @param comment
	 * @param statusConfirmation
	 */
	public ConfirmationTransDto(String idTransaction, String partnerCode, String userConfirmation, String comment,
			StatusEnum statusConfirmation) {
		super();
		this.id = idTransaction;
		this.partnerCode = partnerCode;
		this.userConfirmation = userConfirmation;
		this.comment = comment;
		this.statusConfirmation = statusConfirmation;
	}



	/**
	 * @return the idTransaction
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param idTransaction the idTransaction to set
	 */
	public void setId(String idTransaction) {
		this.id = idTransaction;
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
	 * @return the statusConfirmation
	 */
	public StatusEnum getStatusConfirmation() {
		return statusConfirmation;
	}



	/**
	 * @param statusConfirmation the statusConfirmation to set
	 */
	public void setStatusConfirmation(StatusEnum statusConfirmation) {
		this.statusConfirmation = statusConfirmation;
	}
	
}
