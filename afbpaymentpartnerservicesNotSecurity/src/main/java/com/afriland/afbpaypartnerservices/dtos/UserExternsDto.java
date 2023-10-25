package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.afriland.afbpaypartnerservices.jpa.Roles;

public class UserExternsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String login;
	
	private String nom;

	private String email;
		
	private String password;
	
	private String partnerCode;
	
	private String uticreation;

	private Date validTo;

	private Date validfrom;	
	
	private boolean actif;

	private Set<Roles> roles = new HashSet<>();

	
	public UserExternsDto() {
	}

	
	public UserExternsDto(String username, String email, String password) {
		this.login = username;
		this.email = email;
		this.password = password;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the uticreation
	 */
	public String getUticreation() {
		return uticreation;
	}


	/**
	 * @param uticreation the uticreation to set
	 */
	public void setUticreation(String uticreation) {
		this.uticreation = uticreation;
	}


	/**
	 * @return the validTo
	 */
	public Date getValidTo() {
		return validTo;
	}


	/**
	 * @param validTo the validTo to set
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}


	/**
	 * @return the validfrom
	 */
	public Date getValidfrom() {
		return validfrom;
	}


	/**
	 * @param validfrom the validfrom to set
	 */
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}


	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}


	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}


	/**
	 * @return the roles
	 */
	public Set<Roles> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
}
