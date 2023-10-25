package com.afriland.afbpaypartnerservices.jpa;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe representant la table des evenements de Amplitude
 * @author yves_labo
 * @version 1.0
 */
@Entity
@Table(name = "PAYPART_TRANS_EVE")
public class Bkeve extends BkeveBase implements Serializable {

	/**a
	 * Default Serial UID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Transaction ayant genere l'evenement
	 */
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRX_ID")
	private Transactions transaction;
	
	@Column
	private Boolean bkhlv = Boolean.FALSE;

	/**
	 * Liste des ecritures de la transaction
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(
			name = "PAYPART_EVE_mvti",
			joinColumns = {@JoinColumn(name = "EVE")}
	)
	private List<Bkmvti> ecritures = new ArrayList<Bkmvti>();


	/**
	 * Default Constructor
	 */
	public Bkeve() {}


	/**
	 * @param id
	 * @param agsa
	 * @param age
	 * @param ope
	 * @param eve
	 * @param typ
	 * @param ndos
	 * @param age1
	 * @param dev1
	 * @param ncp1
	 * @param suf1
	 * @param clc1
	 * @param cli1
	 * @param nom1
	 * @param ges1
	 * @param sen1
	 * @param mht1
	 * @param mon1
	 * @param dva1
	 * @param exo1
	 * @param sol1
	 * @param indh1
	 * @param inds1
	 * @param desa1
	 * @param desa2
	 * @param desa3
	 * @param desa4
	 * @param desa5
	 * @param age2
	 * @param dev2
	 * @param ncp2
	 * @param suf2
	 * @param clc2
	 * @param cli2
	 * @param nom2
	 * @param ges2
	 * @param sen2
	 * @param mht2
	 * @param mon2
	 * @param dva2
	 * @param din
	 * @param exo2
	 * @param sol2
	 * @param indh2
	 * @param inds2
	 * @param desc1
	 * @param desc2
	 * @param desc3
	 * @param desc4
	 * @param desc5
	 * @param etab
	 * @param guib
	 * @param nome
	 * @param domi
	 * @param adb1
	 * @param adb2
	 * @param adb3
	 * @param vilb
	 * @param cpob
	 * @param cpay
	 * @param etabr
	 * @param guibr
	 * @param comb
	 * @param cleb
	 * @param nomb
	 * @param mban
	 * @param dvab
	 * @param cai1
	 * @param tyc1
	 * @param dcai1
	 * @param scai1
	 * @param mcai1
	 * @param arrc1
	 * @param cai2
	 * @param tyc2
	 * @param dcai2
	 * @param scai2
	 * @param mcai2
	 * @param arrc2
	 * @param dev
	 * @param mht
	 * @param mnat
	 * @param mbor
	 * @param nbor
	 * @param nblig
	 * @param tcai2
	 * @param tcai3
	 * @param nat
	 * @param nato
	 * @param opeo
	 * @param eveo
	 * @param pieo
	 * @param dou
	 * @param dco
	 * @param eta
	 * @param etap
	 * @param nbv
	 * @param nval
	 * @param uti
	 * @param utf
	 * @param uta
	 * @param moa
	 * @param mof
	 * @param lib1
	 * @param lib2
	 * @param lib3
	 * @param lib4
	 * @param lib5
	 * @param lib6
	 * @param lib7
	 * @param lib8
	 * @param lib9
	 * @param lib10
	 * @param agec
	 * @param agep
	 * @param intr
	 * @param orig
	 * @param rlet
	 * @param catr
	 * @param ceb
	 * @param plb
	 * @param cco
	 * @param dret
	 * @param natp
	 * @param nump
	 * @param datp
	 * @param nomp
	 * @param ad1p
	 * @param ad2p
	 * @param delp
	 * @param serie
	 * @param nche
	 * @param chql
	 * @param chqc
	 * @param cab
	 * @param ncff
	 * @param csa
	 * @param dech
	 * @param tire
	 * @param agti
	 * @param agre
	 * @param nbji
	 * @param ptfc
	 * @param efav
	 * @param navl
	 * @param edom
	 * @param eopp
	 * @param efac
	 * @param moti
	 * @param envacc
	 * @param enom
	 * @param vicl
	 * @param teco
	 * @param tenv
	 * @param exjo
	 * @param org
	 * @param cai3
	 * @param mcai3
	 * @param forc
	 * @param ocai3
	 * @param ncai3
	 * @param csp1
	 * @param csp2
	 * @param csp3
	 * @param csp4
	 * @param csp5
	 * @param ndom
	 * @param cmod
	 * @param devf
	 * @param ncpf
	 * @param suff
	 * @param monf
	 * @param dvaf
	 * @param exof
	 * @param agee
	 * @param deve
	 * @param ncpe
	 * @param sufe
	 * @param clce
	 * @param ncpi
	 * @param sufi
	 * @param mimp
	 * @param dvai
	 * @param ncpp
	 * @param sufp
	 * @param prga
	 * @param mrga
	 * @param term
	 * @param tvar
	 * @param intp
	 * @param cap
	 * @param prll
	 * @param ano
	 * @param etab1
	 * @param guib1
	 * @param com1b
	 * @param etab2
	 * @param guib2
	 * @param com2b
	 * @param tcom1
	 * @param mcom1
	 * @param tcom2
	 * @param mcom2
	 * @param tcom3
	 * @param mcom3
	 * @param frai1
	 * @param frai2
	 * @param frai3
	 * @param ttax1
	 * @param mtax1
	 * @param ttax2
	 * @param mtax2
	 * @param ttax3
	 * @param mtax3
	 * @param mnt1
	 * @param mnt2
	 * @param mnt3
	 * @param mnt4
	 * @param mnt5
	 * @param tyc3
	 * @param dcai3
	 * @param scai3
	 * @param arrc3
	 * @param mhtd
	 * @param tcai4
	 * @param tope
	 * @param img
	 * @param dsai
	 * @param hsai
	 * @param paysp
	 * @param pdelp
	 * @param manda
	 * @param refdos
	 * @param tchfr
	 */
	public Bkeve(BkeveTemplate tmp) {
		super(tmp.agsa, tmp.age, tmp.ope, tmp.eve, tmp.typ, tmp.ndos, tmp.age1, tmp.dev1, tmp.ncp1, tmp.suf1, tmp.clc1, tmp.cli1, tmp.nom1, tmp.ges1, tmp.sen1,tmp.mht1, tmp.mon1, tmp.dva1, tmp.exo1,
				tmp.sol1, tmp.indh1, tmp.inds1, tmp.desa1, tmp.desa2, tmp.desa3, tmp.desa4, tmp.desa5, tmp.age2, tmp.dev2, tmp.ncp2, tmp.suf2, tmp.clc2, tmp.cli2, tmp.nom2, tmp.ges2, tmp.sen2,
				tmp.mht2, tmp.mon2, tmp.dva2, tmp.din, tmp.exo2, tmp.sol2, tmp.indh2, tmp.inds2, tmp.desc1, tmp.desc2, tmp.desc3, tmp.desc4, tmp.desc5, tmp.etab, tmp.guib, tmp.nome, tmp.domi,
				tmp.adb1, tmp.adb2, tmp.adb3, tmp.vilb, tmp.cpob, tmp.cpay, tmp.etabr, tmp.guibr, tmp.comb, tmp.cleb, tmp.nomb, tmp.mban, tmp.dvab, tmp.cai1, tmp.tyc1, tmp.dcai1, tmp.scai1, tmp.mcai1,
				tmp.arrc1, tmp.cai2, tmp.tyc2, tmp.dcai2, tmp.scai2, tmp.mcai2, tmp.arrc2, tmp.dev, tmp.mht, tmp.mnat, tmp.mbor, tmp.nbor, tmp.nblig, tmp.tcai2, tmp.tcai3, tmp.nat, tmp.nato, tmp.opeo,
				tmp.eveo, tmp.pieo, tmp.dou, tmp.dco, tmp.eta, tmp.etap, tmp.nbv, tmp.nval, tmp.uti, tmp.utf, tmp.uta, tmp.moa, tmp.mof, tmp.lib1, tmp.lib2, tmp.lib3, tmp.lib4, tmp.lib5, tmp.lib6, tmp.lib7,
				tmp.lib8, tmp.lib9, tmp.lib10, tmp.agec, tmp.agep, tmp.intr, tmp.orig, tmp.rlet, tmp.catr, tmp.ceb, tmp.plb, tmp.cco, tmp.dret, tmp.natp, tmp.nump, tmp.datp, tmp.nomp, tmp.ad1p, tmp.ad2p,
				tmp.delp, tmp.serie, tmp.nche, tmp.chql, tmp.chqc, tmp.cab, tmp.ncff, tmp.csa, tmp.dech, tmp.tire, tmp.agti, tmp.agre, tmp.nbji, tmp.ptfc, tmp.efav, tmp.navl, tmp.edom, tmp.eopp, tmp.efac,
				tmp.moti, tmp.envacc, tmp.enom, tmp.vicl, tmp.teco, tmp.tenv, tmp.exjo, tmp.org, tmp.cai3, tmp.mcai3, tmp.forc, tmp.ocai3, tmp.ncai3, tmp.csp1, tmp.csp2, tmp.csp3, tmp.csp4, tmp.csp5,
				tmp.ndom, tmp.cmod, tmp.devf, tmp.ncpf, tmp.suff, tmp.monf, tmp.dvaf, tmp.exof, tmp.agee, tmp.deve, tmp.ncpe, tmp.sufe, tmp.clce, tmp.ncpi, tmp.sufi, tmp.mimp, tmp.dvai, tmp.ncpp,
				tmp.sufp, tmp.prga, tmp.mrga, tmp.term, tmp.tvar, tmp.intp, tmp.cap, tmp.prll, tmp.ano, tmp.etab1, tmp.guib1, tmp.com1b, tmp.etab2, tmp.guib2, tmp.com2b, tmp.tcom1, tmp.mcom1,
				tmp.tcom2, tmp.mcom2, tmp.tcom3, tmp.mcom3, tmp.frai1, tmp.frai2, tmp.frai3, tmp.ttax1, tmp.mtax1, tmp.ttax2, tmp.mtax2, tmp.ttax3, tmp.mtax3, tmp.mnt1, tmp.mnt2, tmp.mnt3,
				tmp.mnt4, tmp.mnt5, tmp.tyc3, tmp.dcai3, tmp.scai3, tmp.arrc3, tmp.mhtd, tmp.tcai4, tmp.tope, tmp.img, tmp.dsai, tmp.hsai, tmp.paysp, tmp.pdelp, tmp.manda, tmp.refdos, tmp.tchfr);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @param ope
	 * @param eve
	 * @param dev
	 * @param mht
	 * @param nat
	 * @param dco
	 * @param uti
	 * @param lib1
	 */
	public Bkeve(Transactions transaction, String ope, String eve, String dev, Double mht, String nat, Date dco, String uti, Double tcom1, Double frai1, Double ttax1, Double mnt1, Date dva1, Date dva2) {
		super();
		this.transaction = transaction;
		this.ope = ope;
		this.eve = eve;
		this.dev = dev;
		this.mht = mht;
		this.mnat = mht;
		this.mbor = mht;
		this.nat = nat;
		this.dco = dco;
		this.din = dco;
		this.dou = dco;
		this.dsai = dco;
		this.uti = uti;
		this.tcom1 = tcom1;
		this.frai1 = frai1;
		this.ttax1 = ttax1;
		this.mnt1 = mnt1;
		this.dva1 = new Date();
		this.dva2 = new Date();
	}
	


	@Override
	public String toString() {
		return "Bkeve [agsa=" + agsa + ", age=" + age + ", ope=" + ope + ", eve=" + eve + ", typ=" + typ + ", ndos="
				+ ndos + ", age1=" + age1 + ", dev1=" + dev1 + ", ncp1=" + ncp1 + ", suf1=" + suf1 + ", clc1=" + clc1
				+ ", cli1=" + cli1 + ", nom1=" + nom1 + ", ges1=" + ges1 + ", sen1=" + sen1 + ", mht1=" + mht1
				+ ", mon1=" + mon1 + ", dva1=" + dva1 + ", exo1=" + exo1 + ", sol1=" + sol1 + ", indh1=" + indh1
				+ ", inds1=" + inds1 + ", desa1=" + desa1 + ", desa2=" + desa2 + ", desa3=" + desa3 + ", desa4=" + desa4
				+ ", desa5=" + desa5 + ", age2=" + age2 + ", dev2=" + dev2 + ", ncp2=" + ncp2 + ", suf2=" + suf2
				+ ", clc2=" + clc2 + ", cli2=" + cli2 + ", nom2=" + nom2 + ", ges2=" + ges2 + ", sen2=" + sen2
				+ ", mht2=" + mht2 + ", mon2=" + mon2 + ", dva2=" + dva2 + ", din=" + din + ", exo2=" + exo2 + ", sol2="
				+ sol2 + ", indh2=" + indh2 + ", inds2=" + inds2 + ", desc1=" + desc1 + ", desc2=" + desc2 + ", desc3="
				+ desc3 + ", desc4=" + desc4 + ", desc5=" + desc5 + ", etab=" + etab + ", guib=" + guib + ", nome="
				+ nome + ", domi=" + domi + ", adb1=" + adb1 + ", adb2=" + adb2 + ", adb3=" + adb3 + ", vilb=" + vilb
				+ ", cpob=" + cpob + ", cpay=" + cpay + ", etabr=" + etabr + ", guibr=" + guibr + ", comb=" + comb
				+ ", cleb=" + cleb + ", nomb=" + nomb + ", mban=" + mban + ", dvab=" + dvab + ", cai1=" + cai1
				+ ", tyc1=" + tyc1 + ", dcai1=" + dcai1 + ", scai1=" + scai1 + ", mcai1=" + mcai1 + ", arrc1=" + arrc1
				+ ", cai2=" + cai2 + ", tyc2=" + tyc2 + ", dcai2=" + dcai2 + ", scai2=" + scai2 + ", mcai2=" + mcai2
				+ ", arrc2=" + arrc2 + ", dev=" + dev + ", mht=" + mht + ", mnat=" + mnat + ", mbor=" + mbor + ", nbor="
				+ nbor + ", nblig=" + nblig + ", tcai2=" + tcai2 + ", tcai3=" + tcai3 + ", nat=" + nat + ", nato="
				+ nato + ", opeo=" + opeo + ", eveo=" + eveo + ", pieo=" + pieo + ", dou=" + dou + ", dco=" + dco
				+ ", eta=" + eta + ", etap=" + etap + ", nbv=" + nbv + ", nval=" + nval + ", uti=" + uti + ", utf="
				+ utf + ", uta=" + uta + ", moa=" + moa + ", mof=" + mof + ", lib1=" + lib1 + ", lib2=" + lib2
				+ ", lib3=" + lib3 + ", lib4=" + lib4 + ", lib5=" + lib5 + ", lib6=" + lib6 + ", lib7=" + lib7
				+ ", lib8=" + lib8 + ", lib9=" + lib9 + ", lib10=" + lib10 + ", agec=" + agec + ", agep=" + agep
				+ ", intr=" + intr + ", orig=" + orig + ", rlet=" + rlet + ", catr=" + catr + ", ceb=" + ceb + ", plb="
				+ plb + ", cco=" + cco + ", dret=" + dret + ", natp=" + natp + ", nump=" + nump + ", datp=" + datp
				+ ", nomp=" + nomp + ", ad1p=" + ad1p + ", ad2p=" + ad2p + ", delp=" + delp + ", serie=" + serie
				+ ", nche=" + nche + ", chql=" + chql + ", chqc=" + chqc + ", cab=" + cab + ", ncff=" + ncff + ", csa="
				+ csa + ", dech=" + dech + ", tire=" + tire + ", agti=" + agti + ", agre=" + agre + ", nbji=" + nbji
				+ ", ptfc=" + ptfc + ", efav=" + efav + ", navl=" + navl + ", edom=" + edom + ", eopp=" + eopp
				+ ", efac=" + efac + ", moti=" + moti + ", envacc=" + envacc + ", enom=" + enom + ", vicl=" + vicl
				+ ", teco=" + teco + ", tenv=" + tenv + ", exjo=" + exjo + ", org=" + org + ", cai3=" + cai3
				+ ", mcai3=" + mcai3 + ", forc=" + forc + ", ocai3=" + ocai3 + ", ncai3=" + ncai3 + ", csp1=" + csp1
				+ ", csp2=" + csp2 + ", csp3=" + csp3 + ", csp4=" + csp4 + ", csp5=" + csp5 + ", ndom=" + ndom
				+ ", cmod=" + cmod + ", devf=" + devf + ", ncpf=" + ncpf + ", suff=" + suff + ", monf=" + monf
				+ ", dvaf=" + dvaf + ", exof=" + exof + ", agee=" + agee + ", deve=" + deve + ", ncpe=" + ncpe
				+ ", sufe=" + sufe + ", clce=" + clce + ", ncpi=" + ncpi + ", sufi=" + sufi + ", mimp=" + mimp
				+ ", dvai=" + dvai + ", ncpp=" + ncpp + ", sufp=" + sufp + ", prga=" + prga + ", mrga=" + mrga
				+ ", term=" + term + ", tvar=" + tvar + ", intp=" + intp + ", cap=" + cap + ", prll=" + prll + ", ano="
				+ ano + ", etab1=" + etab1 + ", guib1=" + guib1 + ", com1b=" + com1b + ", etab2=" + etab2 + ", guib2="
				+ guib2 + ", com2b=" + com2b + ", tcom1=" + tcom1 + ", mcom1=" + mcom1 + ", tcom2=" + tcom2 + ", mcom2="
				+ mcom2 + ", tcom3=" + tcom3 + ", mcom3=" + mcom3 + ", frai1=" + frai1 + ", frai2=" + frai2 + ", frai3="
				+ frai3 + ", ttax1=" + ttax1 + ", mtax1=" + mtax1 + ", ttax2=" + ttax2 + ", mtax2=" + mtax2 + ", ttax3="
				+ ttax3 + ", mtax3=" + mtax3 + ", mnt1=" + mnt1 + ", mnt2=" + mnt2 + ", mnt3=" + mnt3 + ", mnt4=" + mnt4
				+ ", mnt5=" + mnt5 + ", tyc3=" + tyc3 + ", dcai3=" + dcai3 + ", scai3=" + scai3 + ", arrc3=" + arrc3
				+ ", mhtd=" + mhtd + ", tcai4=" + tcai4 + ", tope=" + tope + ", img=" + img + ", dsai=" + dsai
				+ ", hsai=" + hsai + ", paysp=" + paysp + ", pdelp=" + pdelp + ", manda=" + manda + ", refdos=" + refdos
				+ ", tchfr=" + tchfr + "]";
	}

	
	/**
	 * @return the ecritures
	 */
	public List<Bkmvti> getEcritures() {
		return ecritures;
	}



	/**
	 * @param ecritures the ecritures to set
	 */
	public void setEcritures(List<Bkmvti> ecritures) {
		this.ecritures = ecritures;
	}


	/**
	 * @return the transaction
	 */
	public Transactions getTransaction() {
		return transaction;
	}


	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transactions transaction) {
		this.transaction = transaction;
	}


}
