package com.afriland.afbpaypartnerservices.dtos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author yves_bertrand
 *
 */
@Data
@ToString
public class CustomerKeyDto {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private String matricule;
	private String accountNo;
	private String accountName;
	private String customerName;
	private String sexe;
	private String adresse;
	private String dataNaissance;
	private String lieuNaissance;
	private String departNaissance;
	private String situation;
	private String typePiece;
	private String numCNI;
	private String niu;
	private String dateDelivrance;
	private String lieuDelivrance;
	private String dateExpiration;
	private Object ville;
	private String codeGestionnaire;
	private Object loginGestionnaire;
	private ArrayList<String> comptes;
	private ArrayList<String> telephones;
	private ArrayList<String> adresseMails;
	
	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the sexe
	 */
	public String getSexe() {
		return sexe;
	}
	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
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
	 * @return the dataNaissance
	 */
	public String getDataNaissance() {
		return dataNaissance;
	}
	/**
	 * @param dataNaissance the dataNaissance to set
	 */
	public void setDataNaissance(String dataNaissance) {
		this.dataNaissance = dataNaissance;
	}
	/**
	 * @return the lieuNaissance
	 */
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	/**
	 * @param lieuNaissance the lieuNaissance to set
	 */
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	/**
	 * @return the departNaissance
	 */
	public String getDepartNaissance() {
		return departNaissance;
	}
	/**
	 * @param departNaissance the departNaissance to set
	 */
	public void setDepartNaissance(String departNaissance) {
		this.departNaissance = departNaissance;
	}
	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}
	/**
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}
	/**
	 * @return the typePiece
	 */
	public String getTypePiece() {
		return typePiece;
	}
	/**
	 * @param typePiece the typePiece to set
	 */
	public void setTypePiece(String typePiece) {
		this.typePiece = typePiece;
	}
	/**
	 * @return the numCNI
	 */
	public String getNumCNI() {
		return numCNI;
	}
	/**
	 * @param numCNI the numCNI to set
	 */
	public void setNumCNI(String numCNI) {
		this.numCNI = numCNI;
	}
	/**
	 * @return the niu
	 */
	public String getNiu() {
		return niu;
	}
	/**
	 * @param niu the niu to set
	 */
	public void setNiu(String niu) {
		this.niu = niu;
	}
	/**
	 * @return the dateDelivrance
	 */
	public String getDateDelivrance() {
		return dateDelivrance;
	}
	/**
	 * @param dateDelivrance the dateDelivrance to set
	 */
	public void setDateDelivrance(String dateDelivrance) {
		this.dateDelivrance = dateDelivrance;
	}
	/**
	 * @return the lieuDelivrance
	 */
	public String getLieuDelivrance() {
		return lieuDelivrance;
	}
	/**
	 * @param lieuDelivrance the lieuDelivrance to set
	 */
	public void setLieuDelivrance(String lieuDelivrance) {
		this.lieuDelivrance = lieuDelivrance;
	}
	/**
	 * @return the dateExpiration
	 */
	public String getDateExpiration() {
		return dateExpiration;
	}
	/**
	 * @param dateExpiration the dateExpiration to set
	 */
	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	/**
	 * @return the ville
	 */
	public Object getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(Object ville) {
		this.ville = ville;
	}
	/**
	 * @return the codeGestionnaire
	 */
	public String getCodeGestionnaire() {
		return codeGestionnaire;
	}
	/**
	 * @param codeGestionnaire the codeGestionnaire to set
	 */
	public void setCodeGestionnaire(String codeGestionnaire) {
		this.codeGestionnaire = codeGestionnaire;
	}
	/**
	 * @return the loginGestionnaire
	 */
	public Object getLoginGestionnaire() {
		return loginGestionnaire;
	}
	/**
	 * @param loginGestionnaire the loginGestionnaire to set
	 */
	public void setLoginGestionnaire(Object loginGestionnaire) {
		this.loginGestionnaire = loginGestionnaire;
	}
	/**
	 * @return the comptes
	 */
	public ArrayList<String> getComptes() {
		return comptes;
	}
	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(ArrayList<String> comptes) {
		this.comptes = comptes;
	}
	/**
	 * @return the telephones
	 */
	public ArrayList<String> getTelephones() {
		return telephones;
	}
	/**
	 * @param telephones the telephones to set
	 */
	public void setTelephones(ArrayList<String> telephones) {
		this.telephones = telephones;
	}
	/**
	 * @return the adresseMails
	 */
	public ArrayList<String> getAdresseMails() {
		return adresseMails;
	}
	/**
	 * @param adresseMails the adresseMails to set
	 */
	public void setAdresseMails(ArrayList<String> adresseMails) {
		this.adresseMails = adresseMails;
	}
	
	
	
    
}
