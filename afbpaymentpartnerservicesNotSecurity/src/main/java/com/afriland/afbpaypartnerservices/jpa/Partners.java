package com.afriland.afbpaypartnerservices.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.RandomStringUtils;

import com.afriland.afbpaypartnerservices.enums.PayPartnerTypeString;
import com.afriland.afbpaypartnerservices.utils.AfbEncryptionDecryption;
import com.afriland.afbpaypartnerservices.utils.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author yves_labo
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYPART_PARTNER")
public class Partners implements Serializable{
	
	@Transient
	final String secretKey = "partenaire";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String makeId(){
		return DateUtil.now()+RandomStringUtils.randomAlphanumeric(25).toUpperCase();
	}
	

	@PostConstruct
	public void PostConstruct() {
		this.id = makeId();
	}

	
	@javax.persistence.PrePersist
	public void PrePersist(){
		if(this.getId() == null) this.setId(DateUtil.now()+RandomStringUtils.randomAlphanumeric(25).toUpperCase());
		this.validfrom = new Date();
	}
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name="partcode",unique=true)
	private String partcode;
	
	@Column
	private String partname;
	
	@Column
	private String account;
		
	@Column
	private String phones;
	
	@Column
	private String emails;
	
	@Column
	private Integer sizeKey = 8;
	
	@Column(name = "typekey")
	@Enumerated(EnumType.STRING)
	private PayPartnerTypeString typekey = PayPartnerTypeString.AN;
	
	
	@Column
	private String pwd = AfbEncryptionDecryption.encrypt("xxxxxx", secretKey);
		
	@Column
	private boolean actif = Boolean.TRUE;
		
	@Column
	private boolean kycPartner = Boolean.TRUE;
	
	@Column
	private boolean comptabilisationTransit = Boolean.TRUE;
	
	@Column
	private boolean confirmTransaction = Boolean.TRUE;
		
        
    @Column(name = "UTI_CREA")
    private String utiCreation;
    
    @Column(name = "Nameuti_CREA")
    private String nameUtiCreation;
    
    
    @Column(name = "uti_Mod")
    private String utiMod;
    
    @Column(name = "Nameuti_Mod")
    private String nameUtiMod;
    
    @Column(name="HOST")
	private String host = "172.21.88.115";
	
	@Column(name="PORT")
	private int port = 80;

	@Column(name="PROTOCOLE")
	private String protocole = "http";
	
	@Column(name="PATH")
	private String path = "/";
	
	@Column(name="API_KEY", length=500)
	private String apiKey;
	
	@Column(name="NCP_VERS")
	private String partnerNcpVersement;// = Encrypter.getInstance().encryptText("");
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTo;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date validfrom;
    

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
	
	/**
	 * @param sizeKey the sizeKey to set
	 */
	public void setSizeKey(Integer sizeKey) {
		this.sizeKey = sizeKey;
	}	

	public String getPartnerNcpVersement() {
		return partnerNcpVersement;
	}

	public void setPartnerNcpVersement(String partnerNcpVersement) {
		this.partnerNcpVersement = partnerNcpVersement;
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
		return AfbEncryptionDecryption.decrypt(pwd, secretKey);
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = AfbEncryptionDecryption.encrypt(pwd, secretKey);
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
	public boolean isKycPartner() {
		return kycPartner;
	}


	/**
	 * @param kycPartner the kycPartner to set
	 */
	public void setKycPartner(boolean kycPartner) {
		this.kycPartner = kycPartner;
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
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
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
		return AfbEncryptionDecryption.decrypt(apiKey, secretKey);
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = AfbEncryptionDecryption.encrypt(apiKey, secretKey);
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
	 * @return the comptabilisationTransit
	 */
	public boolean isComptabilisationTransit() {
		return comptabilisationTransit;
	}


	/**
	 * @param comptabilisationTransit the comptabilisationTransit to set
	 */
	public void setComptabilisationTransit(boolean comptabilisationTransit) {
		this.comptabilisationTransit = comptabilisationTransit;
	}


	/**
	 * @return the confirmTransaction
	 */
	public boolean isConfirmTransaction() {
		return confirmTransaction;
	}


	/**
	 * @param confirmTransaction the confirmTransaction to set
	 */
	public void setConfirmTransaction(boolean confirmTransaction) {
		this.confirmTransaction = confirmTransaction;
	}

	
}
