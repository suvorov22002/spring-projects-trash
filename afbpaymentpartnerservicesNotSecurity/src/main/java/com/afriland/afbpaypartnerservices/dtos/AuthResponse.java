package com.afriland.afbpaypartnerservices.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class AuthResponse {
	
    private String sessionID;
    private String nom;
    private String numeroCompte;
    private String adresse;
    private String telephone;
    private String message;
    private BigDecimal montant = new BigDecimal(0);
    private List<UnpaidNotice> resultData = new ArrayList<UnpaidNotice>();
	
    
    /**
	 * 
	 */
	public AuthResponse() {
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
	public AuthResponse(String sessionID, String nom, String numeroCompte, String adresse, String telephone,
			String message, BigDecimal montant, List<UnpaidNotice> resultData) {
		super();
		this.sessionID = sessionID;
		this.nom = nom;
		this.numeroCompte = numeroCompte;
		this.adresse = adresse;
		this.telephone = telephone;
		this.message = message;
		this.montant = montant;
		this.resultData = resultData;
	}


	/**
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}


	/**
	 * @param sessionID the sessionID to set
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the numeroCompte
	 */
	public String getNumeroCompte() {
		return numeroCompte;
	}


	/**
	 * @param numeroCompte the numeroCompte to set
	 */
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}


	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
	 * @return the montant
	 */
	public BigDecimal getMontant() {
		return montant;
	}


	/**
	 * @param montant the montant to set
	 */
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}


	/**
	 * @return the resultData
	 */
	public List<UnpaidNotice> getResultData() {
		return resultData;
	}


	/**
	 * @param resultData the resultData to set
	 */
	public void setResultData(List<UnpaidNotice> resultData) {
		this.resultData = resultData;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthResponse [sessionID=" + sessionID + ", nom=" + nom + ", numeroCompte=" + numeroCompte + ", adresse="
				+ adresse + ", telephone=" + telephone + ", message=" + message + ", montant=" + montant
				+ ", resultData=" + resultData + "]";
	}
		
}
