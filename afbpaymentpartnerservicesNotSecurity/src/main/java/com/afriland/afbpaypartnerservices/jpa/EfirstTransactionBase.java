package com.afriland.afbpaypartnerservices.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author yves_labo
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class EfirstTransactionBase {

	@Column
	private Double nord;
	@Column
	private String deva;
	@Column
	private String code;
	@Column
	private String cpro;
	@Column
	private String ncp2;
	@Column
	private String nomc;
	@Column
	private String ncp1;
	@Column
	private String login;
	@Column
	private Double mnt1;
	@Column
	private String dope;
	@Column
	private String comb;
	@Column(name = "txtdreq")
	private String dreq;
	@Column
	private String devn;
	@Column
	private String guib;
	@Column
	private String motif;
	@Column
	private String age;
	@Column
	private String etab;
	
		
		
	
	/**
	 * @return the nord
	 */
	public Double getNord() {
		return nord;
	}
	/**
	 * @param nord the nord to set
	 */
	public void setNord(Double nord) {
		this.nord = nord;
	}
	/**
	 * @return the deva
	 */
	public String getDeva() {
		return deva;
	}
	/**
	 * @param deva the deva to set
	 */
	public void setDeva(String deva) {
		this.deva = deva;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the cpro
	 */
	public String getCpro() {
		return cpro;
	}
	/**
	 * @param cpro the cpro to set
	 */
	public void setCpro(String cpro) {
		this.cpro = cpro;
	}
	/**
	 * @return the ncp2
	 */
	public String getNcp2() {
		return ncp2;
	}
	/**
	 * @param ncp2 the ncp2 to set
	 */
	public void setNcp2(String ncp2) {
		this.ncp2 = ncp2;
	}
	/**
	 * @return the nomc
	 */
	public String getNomc() {
		return nomc;
	}
	/**
	 * @param nomc the nomc to set
	 */
	public void setNomc(String nomc) {
		this.nomc = nomc;
	}
	/**
	 * @return the ncp1
	 */
	public String getNcp1() {
		return ncp1;
	}
	/**
	 * @param ncp1 the ncp1 to set
	 */
	public void setNcp1(String ncp1) {
		this.ncp1 = ncp1;
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
	 * @return the mnt1
	 */
	public Double getMnt1() {
		return mnt1;
	}
	/**
	 * @param mnt1 the mnt1 to set
	 */
	public void setMnt1(Double mnt1) {
		this.mnt1 = mnt1;
	}
	/**
	 * @return the dope
	 */
	public String getDope() {
		return dope;
	}
	/**
	 * @param dope the dope to set
	 */
	public void setDope(String dope) {
		this.dope = dope;
	}
	/**
	 * @return the comb
	 */
	public String getComb() {
		return comb;
	}
	/**
	 * @param comb the comb to set
	 */
	public void setComb(String comb) {
		this.comb = comb;
	}
	/**
	 * @return the dreq
	 */
	public String getDreq() {
		return dreq;
	}
	/**
	 * @param dreq the dreq to set
	 */
	public void setDreq(String dreq) {
		this.dreq = dreq;
	}
	/**
	 * @return the devn
	 */
	public String getDevn() {
		return devn;
	}
	/**
	 * @param devn the devn to set
	 */
	public void setDevn(String devn) {
		this.devn = devn;
	}
	/**
	 * @return the guib
	 */
	public String getGuib() {
		return guib;
	}
	/**
	 * @param guib the guib to set
	 */
	public void setGuib(String guib) {
		this.guib = guib;
	}
	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}
	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the etab
	 */
	public String getEtab() {
		return etab;
	}
	/**
	 * @param etab the etab to set
	 */
	public void setEtab(String etab) {
		this.etab = etab;
	}

}
