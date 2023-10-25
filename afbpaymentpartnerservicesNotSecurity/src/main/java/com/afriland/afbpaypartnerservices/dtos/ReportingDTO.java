/**
 * 
 */
package com.afriland.afbpaypartnerservices.dtos;

import java.io.Serializable;
import java.util.Date;



/**
 * Classe representant les Etapes DTO
 * @author Yves LABO
 * @version 2.0
 */
public class ReportingDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String codeUnite = "";
	
	private String unite = "";
	
	private String gfc = "";
	
	private Date dateDebut;
	
	private Date dateFin;
	
	
	private Long totalExtraction = 0L;
	
	private Long totalEnAttente = 0L;
	
	private Long totalTransPrecontentieux = 0L;
	
	private Long totalNonTransPrecontentieux = 0L;
	
	private Long totalGFC = 0L;
	
	private Long totalDADS = 0L;
	
	private Long totalDR = 0L;
	
	private Long totalRespPrecon = 0L;
	
	private Long totalDirCredit = 0L;
	
	private Long totalDG = 0L;
	
	private Long totalChoixGfcPrecon = 0L;
	

	/**
	 * Default Constructor
	 */
	public ReportingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param codeUnite
	 * @param unite
	 * @param gfc
	 * @param dateDebut
	 * @param dateFin
	 * @param totalExtraction
	 * @param totalEnAttente
	 * @param totalTransPrecontentieux
	 * @param totalNonTransPrecontentieux
	 * @param totalGFC
	 * @param totalDADS
	 * @param totalDR
	 * @param totalRespPrecon
	 * @param totalDirCredit
	 * @param totalDG
	 * @param totalChoixGfcPrecon
	 */
	public ReportingDTO(String codeUnite, String unite, String gfc, Date dateDebut, Date dateFin, Long totalExtraction,
			Long totalEnAttente, Long totalTransPrecontentieux, Long totalNonTransPrecontentieux, Long totalGFC,
			Long totalDADS, Long totalDR, Long totalRespPrecon, Long totalDirCredit, Long totalDG,
			Long totalChoixGfcPrecon) {
		super();
		this.codeUnite = codeUnite;
		this.unite = unite;
		this.gfc = gfc;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.totalExtraction = totalExtraction;
		this.totalEnAttente = totalEnAttente;
		this.totalTransPrecontentieux = totalTransPrecontentieux;
		this.totalNonTransPrecontentieux = totalNonTransPrecontentieux;
		this.totalGFC = totalGFC;
		this.totalDADS = totalDADS;
		this.totalDR = totalDR;
		this.totalRespPrecon = totalRespPrecon;
		this.totalDirCredit = totalDirCredit;
		this.totalDG = totalDG;
		this.totalChoixGfcPrecon = totalChoixGfcPrecon;
	}
	
	
	
	/**
	 * @return the codeUnite
	 */
	public String getCodeUnite() {
		return codeUnite;
	}


	/**
	 * @param codeUnite the codeUnite to set
	 */
	public void setCodeUnite(String codeUnite) {
		this.codeUnite = codeUnite;
	}


	/**
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}


	/**
	 * @param unite the unite to set
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}
	
	/**
	 * @return the gfc
	 */
	public String getGfc() {
		return gfc;
	}


	/**
	 * @param gfc the gfc to set
	 */
	public void setGfc(String gfc) {
		this.gfc = gfc;
	}


	/**
	 * @return the dateDebut
	 */
	public Date getDateDebut() {
		return dateDebut;
	}


	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}


	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}


	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	/**
	 * @return the totalExtraction
	 */
	public Long getTotalExtraction() {
		return totalExtraction;
	}


	/**
	 * @param totalExtraction the totalExtraction to set
	 */
	public void setTotalExtraction(Long totalExtraction) {
		this.totalExtraction = totalExtraction;
	}


	/**
	 * @return the totalEnAttente
	 */
	public Long getTotalEnAttente() {
		return totalEnAttente;
	}


	/**
	 * @param totalEnAttente the totalEnAttente to set
	 */
	public void setTotalEnAttente(Long totalEnAttente) {
		this.totalEnAttente = totalEnAttente;
	}


	/**
	 * @return the totalTransPrecontentieux
	 */
	public Long getTotalTransPrecontentieux() {
		return totalTransPrecontentieux;
	}


	/**
	 * @param totalTransPrecontentieux the totalTransPrecontentieux to set
	 */
	public void setTotalTransPrecontentieux(Long totalTransPrecontentieux) {
		this.totalTransPrecontentieux = totalTransPrecontentieux;
	}


	/**
	 * @return the totalNonTransPrecontentieux
	 */
	public Long getTotalNonTransPrecontentieux() {
		return totalNonTransPrecontentieux;
	}


	/**
	 * @param totalNonTransPrecontentieux the totalNonTransPrecontentieux to set
	 */
	public void setTotalNonTransPrecontentieux(Long totalNonTransPrecontentieux) {
		this.totalNonTransPrecontentieux = totalNonTransPrecontentieux;
	}


	/**
	 * @return the totalGFC
	 */
	public Long getTotalGFC() {
		return totalGFC;
	}


	/**
	 * @param totalGFC the totalGFC to set
	 */
	public void setTotalGFC(Long totalGFC) {
		this.totalGFC = totalGFC;
	}


	/**
	 * @return the totalDADS
	 */
	public Long getTotalDADS() {
		return totalDADS;
	}


	/**
	 * @param totalDADS the totalDADS to set
	 */
	public void setTotalDADS(Long totalDADS) {
		this.totalDADS = totalDADS;
	}


	/**
	 * @return the totalDR
	 */
	public Long getTotalDR() {
		return totalDR;
	}


	/**
	 * @param totalDR the totalDR to set
	 */
	public void setTotalDR(Long totalDR) {
		this.totalDR = totalDR;
	}


	/**
	 * @return the totalRespPrecon
	 */
	public Long getTotalRespPrecon() {
		return totalRespPrecon;
	}


	/**
	 * @param totalRespPrecon the totalRespPrecon to set
	 */
	public void setTotalRespPrecon(Long totalRespPrecon) {
		this.totalRespPrecon = totalRespPrecon;
	}


	/**
	 * @return the totalDirCredit
	 */
	public Long getTotalDirCredit() {
		return totalDirCredit;
	}


	/**
	 * @param totalDirCredit the totalDirCredit to set
	 */
	public void setTotalDirCredit(Long totalDirCredit) {
		this.totalDirCredit = totalDirCredit;
	}


	/**
	 * @return the totalDG
	 */
	public Long getTotalDG() {
		return totalDG;
	}


	/**
	 * @param totalDG the totalDG to set
	 */
	public void setTotalDG(Long totalDG) {
		this.totalDG = totalDG;
	}


	/**
	 * @return the totalChoixGfcPrecon
	 */
	public Long getTotalChoixGfcPrecon() {
		return totalChoixGfcPrecon;
	}


	/**
	 * @param totalChoixGfcPrecon the totalChoixGfcPrecon to set
	 */
	public void setTotalChoixGfcPrecon(Long totalChoixGfcPrecon) {
		this.totalChoixGfcPrecon = totalChoixGfcPrecon;
	}
	
}
