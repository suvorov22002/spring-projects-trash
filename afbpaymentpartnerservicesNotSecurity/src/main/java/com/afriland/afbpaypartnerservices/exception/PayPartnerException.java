package com.afriland.afbpaypartnerservices.exception;

import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCategory;
import com.afriland.afbpaypartnerservices.enums.PayPartnerExceptionCode;

/**
 * 
 * @author FOKAM/JAZA
 *
 */
public class PayPartnerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constuctor
	 */
	public PayPartnerException() {}
	
	/**
	 * Code du message
	 */
	protected PayPartnerExceptionCode code;
	
	/**
	 * Message de l'exception
	 */
	protected String message;
	
	/**
	 * Categorie de l'exception
	 */
	protected PayPartnerExceptionCategory category;

	/**
	 * @param code
	 * @param message
	 * @param category
	 */
	public PayPartnerException(PayPartnerExceptionCode code, String message, PayPartnerExceptionCategory category) {
		super();
		this.code = code;
		this.message = message;
		this.category = category;
	}

	/**
	 * @return the code
	 */
	public PayPartnerExceptionCode getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(PayPartnerExceptionCode code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the category
	 */
	public PayPartnerExceptionCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(PayPartnerExceptionCategory category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.category + "] : " + (this.message != null ? this.message : this.code);
	}
	
	
}
