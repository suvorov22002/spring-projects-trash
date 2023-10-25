package com.afb.dpdl.ged.ws.rest.api.service.bank;


public class InfoMission {
	public String nom;
	public String ref;
	public String fonction;
	public String grade;
	public String cellule;
	public String ncp;
	public Integer nbrJours; 
	public String moyenTrans;
	public String unitebudg;
	public String unite;
	public String villeDepart; 
	public String villeDestinataire;
	public String paysAfricain; 
	public String paysEtranger;
	public String codeOpe;
	public double transportReel; 
	public double missionReel;
	public String ncpTransport;
	public String ncpMission; 
	public String cutiAnalComp;
	public String cutiDcompt;
	public String codeConge;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCellule() {
		return cellule;
	}
	public void setCellule(String cellule) {
		this.cellule = cellule;
	}
	public String getNcp() {
		return ncp;
	}
	public void setNcp(String ncp) {
		this.ncp = ncp;
	}
	public String getMoyenTrans() {
		return moyenTrans;
	}
	public void setMoyenTrans(String moyenTrans) {
		this.moyenTrans = moyenTrans;
	}
	public String getUnitebudg() {
		return unitebudg;
	}
	public void setUnitebudg(String unitebudg) {
		this.unitebudg = unitebudg;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public String getVilleDestinataire() {
		return villeDestinataire;
	}
	public void setVilleDestinataire(String villeDestinataire) {
		this.villeDestinataire = villeDestinataire;
	}
	public String getPaysAfricain() {
		return paysAfricain;
	}
	public void setPaysAfricain(String paysAfricain) {
		this.paysAfricain = paysAfricain;
	}
	public String getPaysEtranger() {
		return paysEtranger;
	}
	public void setPaysEtranger(String paysEtranger) {
		this.paysEtranger = paysEtranger;
	}
	public String getCodeOpe() {
		return codeOpe;
	}
	public void setCodeOpe(String codeOpe) {
		this.codeOpe = codeOpe;
	}
	public double getTransportReel() {
		return transportReel;
	}
	public void setTransportReel(double transportReel) {
		this.transportReel = transportReel;
	}
	public double getMissionReel() {
		return missionReel;
	}
	public void setMissionReel(double missionReel) {
		this.missionReel = missionReel;
	}
	public String getNcpTransport() {
		return ncpTransport;
	}
	public void setNcpTransport(String ncpTransport) {
		this.ncpTransport = ncpTransport;
	}
	public String getNcpMission() {
		return ncpMission;
	}
	public void setNcpMission(String ncpMission) {
		this.ncpMission = ncpMission;
	}
	public String getCutiAnalComp() {
		return cutiAnalComp;
	}
	public void setCutiAnalComp(String cutiAnalComp) {
		this.cutiAnalComp = cutiAnalComp;
	}
	public String getCutiDcompt() {
		return cutiDcompt;
	}
	public void setCutiDcompt(String cutiDcompt) {
		this.cutiDcompt = cutiDcompt;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	
	public String getCodeConge() {
		return codeConge;
	}
	public void setCodeConge(String codeConge) {
		this.codeConge = codeConge;
	}
	public void setNbrJours(Integer nbrJours) {
		this.nbrJours = nbrJours;
	}
	@Override
	public String toString() {
		return "InfoMission [nom=" + nom + ", ref=" + ref + ", fonction=" + fonction + ", grade=" + grade + ", cellule="
				+ cellule + ", ncp=" + ncp + ", nbrJours=" + nbrJours + ", moyenTrans=" + moyenTrans + ", unitebudg="
				+ unitebudg + ", unite=" + unite + ", villeDepart=" + villeDepart + ", villeDestinataire="
				+ villeDestinataire + ", paysAfricain=" + paysAfricain + ", paysEtranger=" + paysEtranger + ", codeOpe="
				+ codeOpe + ", transportReel=" + transportReel + ", missionReel=" + missionReel + ", ncpTransport="
				+ ncpTransport + ", ncpMission=" + ncpMission + ", cutiAnalComp=" + cutiAnalComp + ", cutiDcompt="
				+ cutiDcompt + "]";
	}
	
}
