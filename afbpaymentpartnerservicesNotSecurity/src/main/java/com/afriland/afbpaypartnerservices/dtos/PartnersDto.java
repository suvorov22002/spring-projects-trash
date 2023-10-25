package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;

import com.afriland.afbpaypartnerservices.enums.PayPartnerTypeString;

/**
 * 
 * @author yves_labo
 *
 */
public class PartnersDto implements Serializable{
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String partcode;
	
	private String partname;
	
	private String account;
		
	private String phones;
	
	private String emails;
	
	private Integer sizeKey;
	
	private PayPartnerTypeString typekey;
		
	private String pwd;
		
	private Boolean actif;
	
	private Boolean kycPartner;
		
	private Boolean comptabilisationTransit;
	
	private Boolean confirmTransaction;
		
    private String utiCreation;
    
    private String nameUtiCreation;
    
    private String utiMod;
    
    private String nameUtiMod;
    
	private String host;
	
	private Integer port;

	private String protocole;
	
	private String path;
	
	private String apiKey;
	
	private String partnerNcpVersement;
	

	/**
	 * @param id
	 * @param partcode
	 * @param partname
	 * @param account
	 * @param phones
	 * @param emails
	 * @param sizeKey
	 * @param typekey
	 * @param pwd
	 * @param actif
	 * @param kycPartner
	 * @param comptabilisationTransit
	 * @param confirmTransaction
	 * @param utiCreation
	 * @param nameUtiCreation
	 * @param utiMod
	 * @param nameUtiMod
	 * @param host
	 * @param port
	 * @param protocole
	 * @param path
	 * @param apiKey
	 * @param partnerNcpVersement
	 */
	public PartnersDto(String id, String partcode, String partname, String account, String phones, String emails,
			Integer sizeKey, PayPartnerTypeString typekey, String pwd, Boolean actif, Boolean kycPartner,
			Boolean comptabilisationTransit, Boolean confirmTransaction, String utiCreation, String nameUtiCreation,
			String utiMod, String nameUtiMod, String host, Integer port, String protocole, String path, String apiKey,
			String partnerNcpVersement) {
		super();
		this.id = id;
		this.partcode = partcode;
		this.partname = partname;
		this.account = account;
		this.phones = phones;
		this.emails = emails;
		this.sizeKey = sizeKey;
		this.typekey = typekey;
		this.pwd = pwd;
		this.actif = actif;
		this.kycPartner = kycPartner;
		this.comptabilisationTransit = comptabilisationTransit;
		this.confirmTransaction = confirmTransaction;
		this.utiCreation = utiCreation;
		this.nameUtiCreation = nameUtiCreation;
		this.utiMod = utiMod;
		this.nameUtiMod = nameUtiMod;
		this.host = host;
		this.port = port;
		this.protocole = protocole;
		this.path = path;
		this.apiKey = apiKey;
		this.partnerNcpVersement = partnerNcpVersement;
	}
	

	/**
	 * 
	 */
	public PartnersDto() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the actif
	 */
	public Boolean getActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}


	/**
	 * @return the partcode
	 */
	public String getPartcode() {
		return partcode;
	}

	/**
	 * @param partcode the partcode to set
	 */
	public void setPartcode(String partcode) {
		this.partcode = partcode;
	}

	/**
	 * @return the partname
	 */
	public String getPartname() {
		return partname;
	}

	/**
	 * @param partname the partname to set
	 */
	public void setPartname(String partname) {
		this.partname = partname;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}
	

	/**
	 * @return the sizeKey
	 */
	public Integer getSizeKey() {
		return sizeKey;
	}
		

	public String getPartnerNcpVersement() {
		return partnerNcpVersement;
	}

	public void setPartnerNcpVersement(String partnerNcpVersement) {
		this.partnerNcpVersement = partnerNcpVersement;
	}

	/**
	 * @param sizeKey the sizeKey to set
	 */
	public void setSizeKey(Integer sizeKey) {
		this.sizeKey = sizeKey;
	}

	/**
	 * @return the typekey
	 */
	public PayPartnerTypeString getTypekey() {
		return typekey;
	}

	/**
	 * @param typekey the typekey to set
	 */
	public void setTypekey(PayPartnerTypeString typekey) {
		this.typekey = typekey;
	}

	
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the kycPartner
	 */
	public Boolean getKycPartner() {
		return kycPartner;
	}

	/**
	 * @param kycPartner the kycPartner to set
	 */
	public void setKycPartner(Boolean kycPartner) {
		this.kycPartner = kycPartner;
	}

	/**
	 * @return the comptabilisationTransit
	 */
	public Boolean getComptabilisationTransit() {
		return comptabilisationTransit;
	}

	/**
	 * @param comptabilisationTransit the comptabilisationTransit to set
	 */
	public void setComptabilisationTransit(Boolean comptabilisationTransit) {
		this.comptabilisationTransit = comptabilisationTransit;
	}

	/**
	 * @return the confirmTransaction
	 */
	public Boolean getConfirmTransaction() {
		return confirmTransaction;
	}


	/**
	 * @param confirmTransaction the confirmTransaction to set
	 */
	public void setConfirmTransaction(Boolean confirmTransaction) {
		this.confirmTransaction = confirmTransaction;
	}


	/**
	 * @return the utiCreation
	 */
	public String getUtiCreation() {
		return utiCreation;
	}

	/**
	 * @param utiCreation the utiCreation to set
	 */
	public void setUtiCreation(String utiCreation) {
		this.utiCreation = utiCreation;
	}

	/**
	 * @return the nameUtiCreation
	 */
	public String getNameUtiCreation() {
		return nameUtiCreation;
	}

	/**
	 * @param nameUtiCreation the nameUtiCreation to set
	 */
	public void setNameUtiCreation(String nameUtiCreation) {
		this.nameUtiCreation = nameUtiCreation;
	}
	

	/**
	 * @return the utiMod
	 */
	public String getUtiMod() {
		return utiMod;
	}

	/**
	 * @param utiMod the utiMod to set
	 */
	public void setUtiMod(String utiMod) {
		this.utiMod = utiMod;
	}

	/**
	 * @return the nameUtiMod
	 */
	public String getNameUtiMod() {
		return nameUtiMod;
	}

	/**
	 * @param nameUtiMod the nameUtiMod to set
	 */
	public void setNameUtiMod(String nameUtiMod) {
		this.nameUtiMod = nameUtiMod;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the protocole
	 */
	public String getProtocole() {
		return protocole;
	}

	/**
	 * @param protocole the protocole to set
	 */
	public void setProtocole(String protocole) {
		this.protocole = protocole;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	
	
}
