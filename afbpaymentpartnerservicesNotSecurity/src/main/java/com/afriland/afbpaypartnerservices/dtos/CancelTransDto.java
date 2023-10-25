package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

/**
 * 
 * @author yves_labo
 *
 */
public class CancelTransDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	private String id;
	
	private String partnerCode;
	
	private String usercancel;
	
	private String cancelReason;
	
	
	

	/**
	 * 
	 */
	public CancelTransDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param id
	 * @param partnerCode
	 * @param usercancel
	 * @param cancelReason
	 */
	public CancelTransDto(String id, String partnerCode, String usercancel, String cancelReason) {
		super();
		this.id = id;
		this.partnerCode = partnerCode;
		this.usercancel = usercancel;
		this.cancelReason = cancelReason;
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

	
}
