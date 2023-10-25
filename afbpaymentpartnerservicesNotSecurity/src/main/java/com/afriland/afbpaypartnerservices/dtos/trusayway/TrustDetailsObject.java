package com.afriland.afbpaypartnerservices.dtos.trusayway;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TrustDetailsObject {
	
    private String intent;
    private List<TrustBill> listBill = new ArrayList<TrustBill>();
	
    
    /**
	 * 
	 */
	public TrustDetailsObject() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param sessionID
	 * @param nom
	 * @param numeroCompte
	 * @param adresse
	 * @param telephone
	 * @param message
	 * @param montant
	 * @param resultData
	 */
	public TrustDetailsObject(String intent, List<TrustBill> listBill) {
		super();
		this.intent = intent;
		this.listBill = listBill;
	}


	/**
	 * @return the intent
	 */
	public String getIntent() {
		return intent;
	}


	/**
	 * @param intent the intent to set
	 */
	public void setIntent(String intent) {
		this.intent = intent;
	}


	/**
	 * @return the listBill
	 */
	public List<TrustBill> getListBill() {
		return listBill;
	}


	/**
	 * @param listBill the listBill to set
	 */
	public void setListBill(List<TrustBill> listBill) {
		this.listBill = listBill;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrustDetailsObject [intent=" + intent + ", listBill=" + listBill + "]";
	}
		
}
