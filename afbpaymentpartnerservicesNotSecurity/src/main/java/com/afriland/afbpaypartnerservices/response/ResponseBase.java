package com.afriland.afbpaypartnerservices.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseBase implements Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private String codeResponse; 

	private String error;
	
	private String message;
	
	public int currentPage;
	
	public Long totalItems;
	
	public int totalPages;
	
	
	/**
	 * @param codeResponse
	 * @param error
	 */
	public ResponseBase(String codeResponse, String error) {
		super();
		this.setCodeResponse(codeResponse);
		this.setError(error);
	}


	/**
	 * 
	 */
	public ResponseBase() {
		super();
	}

	/**
	 * @return the codeResponse
	 */
	public String getCodeResponse() {
		return codeResponse;
	}

	/**
	 * @param codeResponse the code to set
	 */
	public void setCodeResponse(String codeResponse) {
		this.codeResponse = codeResponse;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
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
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	/**
	 * @return the totalItems
	 */
	public Long getTotalItems() {
		return totalItems;
	}


	/**
	 * @param totalItems the totalItems to set
	 */
	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}


	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}


	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
