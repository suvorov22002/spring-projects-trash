package com.afriland.afbpaypartnerservices.response;

import java.util.ArrayList;
import java.util.List;

import com.afriland.afbpaypartnerservices.dtos.TransactionsDto;
import com.afriland.afbpaypartnerservices.models.CollectionRequest;
import com.afriland.afbpaypartnerservices.models.CollectionResponse;
import com.afriland.afbpaypartnerservices.models.Quote;
import com.afriland.afbpaypartnerservices.models.QuoteRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author yves_labo
 *
 */
@Data
@JsonIgnoreProperties({"collection", "quoteRequest", "quoteResponse", "collectionRequest"})
public class TransactionsResponse extends ResponseBase {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private List<TransactionsDto> datas = new ArrayList<TransactionsDto>();
	
	private CollectionResponse collection;
	
	private QuoteRequest quoteRequest;
	
	private Quote quoteResponse;
	
	private CollectionRequest collectionRequest;
	
	private byte[] reportdata;
	
	
	public TransactionsResponse() {
		super();
	}

	public TransactionsResponse(List<TransactionsDto> datas) {
		super();
		this.datas = datas;
	}

	
	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public TransactionsResponse(String codeResponse, String message, String error, int currentPage, Long totalItems, int totalPages, List<TransactionsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
		
		this.setCurrentPage(currentPage);
		this.setTotalItems(totalItems);
		this.setTotalPages(totalPages);
	}
	
	
	/**
	 * @param codeResponse
	 * @param message
	 * @param error
	 * @param datas
	 */
	public TransactionsResponse(String codeResponse, String message, String error, List<TransactionsDto> datas) {
		super();
		this.datas = datas;
		this.setCodeResponse(codeResponse);
		this.setError(error);
		this.setMessage(message);
	}
	
	
	/**
	 * @return the datas
	 */
	public List<TransactionsDto> getDatas() {
		return datas;
	}

	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<TransactionsDto> datas) {
		this.datas = datas;
	}

	/**
	 * @return the collection
	 */
	public CollectionResponse getCollection() {
		return collection;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(CollectionResponse collection) {
		this.collection = collection;
	}

	/**
	 * @return the quoteRequest
	 */
	public QuoteRequest getQuoteRequest() {
		return quoteRequest;
	}

	/**
	 * @param quoteRequest the quoteRequest to set
	 */
	public void setQuoteRequest(QuoteRequest quoteRequest) {
		this.quoteRequest = quoteRequest;
	}

	/**
	 * @return the quoteResponse
	 */
	public Quote getQuoteResponse() {
		return quoteResponse;
	}

	/**
	 * @param quoteResponse the quoteResponse to set
	 */
	public void setQuoteResponse(Quote quoteResponse) {
		this.quoteResponse = quoteResponse;
	}

	/**
	 * @return the collectionRequest
	 */
	public CollectionRequest getCollectionRequest() {
		return collectionRequest;
	}

	/**
	 * @param collectionRequest the collectionRequest to set
	 */
	public void setCollectionRequest(CollectionRequest collectionRequest) {
		this.collectionRequest = collectionRequest;
	}

	/**
	 * @return the reportdata
	 */
	public byte[] getReportdata() {
		return reportdata;
	}

	/**
	 * @param reportdata the reportdata to set
	 */
	public void setReportdata(byte[] reportdata) {
		this.reportdata = reportdata;
	}
	
}
