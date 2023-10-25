package com.afb.dpdl.ged.ws.rest.api.dao.infosemploye;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryInfosEmployeImpl implements IManageQueryInfosEmploye{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	// Initialisation de la requete de chargement des informations de l'employe
	private final String REQ_ALTGRH_INFOS_EMPL = "select emp.MATRICULE as matricule, emp.NOM, emp.PRENOM, fn.NOMFONCTION, gr.NOMGRADE, cel.NOMCELLULE, emp.CODECLASSEACTUELLE, age.NOMAGENCE, emp1.NOM as manager1, emp2.NOM as manager2, emp3.NOM as manager3 " + 
			"from employe emp left join employe emp1 on  emp.MANAGEREVAL1 = emp1.MATRICULE  left join employe emp2 on emp.MANAGEREVAL2 = emp2.MATRICULE left join employe emp3 " + 
			"on emp.MANAGEREVAL3 = emp3.MATRICULE  left join fonction fn on emp.CODEFONCTIONCOURANTE = fn.CODEFONCTION left join grade gr on emp.CODEGRADEACT = gr.CODEGRADE  left join cellule cel on emp.CODECELLULECOURANTE = cel.CODECELLULE  left join agence age " + 
			"on emp.CODEAGENCECOURANTE = age.CODEAGENCE where emp.MATRICULE = ?";
	
	// Initialisation de la requete de chargement des informations du compte de l'employe
//	private final String REQ_ALTGRH_INFOS_CPT = "select cpt.matricule as matricule, emp.AGENCE_VIRE, cpt.numerocompte, cpt.cle from compteemploye cpt, employe emp, agence ag where emp.AGENCE_VIRE = ag.CODEAGENCE " + 
//			"and cpt.MATRICULE = emp.MATRICULE and cpt.numerocompte like '0%%%%%%105%' and cpt.matricule = ?";
	private final String REQ_ALTGRH_INFOS_CPT = "select cpt.matricule as matricule, emp.AGENCE_VIRE, cpt.numerocompte, cpt.cle from compteemploye cpt, employe emp, agence ag where emp.AGENCE_VIRE = ag.CODEAGENCE " + 
			"and cpt.MATRICULE = emp.MATRICULE and emp.radie=0 and cpt.matricule = ?";
	
	// Initialisation de la requete de recuperation du nombre de jours 
	private final String REQ_ALTGRH_NBRE_JRS = "select NOMBREJOURS from ACCORDERCONGECLASSE where codeclasse= ?";
	
	// V�rifier si l'employ� a d�j� contract� un ou plusieurs cong�s
//    private final String REQ_ALTGRH_VERIF_CONGE = "select JOURSREST, NBJRS from OBTENIRCONGE where MATRICULE=? and codeconge <> ? and to_char(DATEDEPARTCONGE,'YYYY')=?";
    private final String REQ_ALTGRH_VERIF_CONGE = "select JOURSREST, NBJRS from OBTENIRCONGE where MATRICULE=? and codeconge in ('C001','C003') and to_char(DATEDEPARTCONGE,'YYYY')=?";
    
    // V�rifier si l'employ� a d�j� contract� une ou plusieurs permissions
//    private final String REQ_ALTGRH_VERIF_PERM = "select JOURSREST, NBJRS from OBTENIRCONGE where MATRICULE=? and codeconge = ? and to_char(DATEDEPARTCONGE,'YYYY')=?";
    private final String REQ_ALTGRH_VERIF_PERM = "select JOURSREST, NBJRS from OBTENIRCONGE where MATRICULE=? and codeconge in ('C002','C006') and to_char(DATEDEPARTCONGE,'YYYY')=?";
    
    private final String REQ_PORTAL_VEHICULE = "SELECT cartegrise FROM mis_vehicule where matricule = ? and activer = true";
	
	@Override
	public Map<String, Object> getInfosEmploye(String matricule) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<>();
		ResultSet rs;

		// Chargement des informations de l'employe
		Object[] parameters = new Object[] {matricule};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.ALTGRH_DB);
		PreparedStatement st = con.prepareStatement(REQ_ALTGRH_INFOS_EMPL);
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null) while(rs.next()){
			data.put("nom", rs.getString("NOM").trim() + (rs.getString("PRENOM") != null ? " " + rs.getString("PRENOM").trim() : "")  );
			data.put("fonction", rs.getString("NOMFONCTION") != null ? rs.getString("NOMFONCTION").trim() : "");
			data.put("grade", rs.getString("NOMGRADE") != null ? rs.getString("NOMGRADE").trim() : "");
			data.put("cellule", rs.getString("NOMCELLULE") != null ? rs.getString("NOMCELLULE").trim() : "");
			data.put("unite", rs.getString("NOMAGENCE") != null ? rs.getString("NOMAGENCE").trim() : "");
			data.put("classe", rs.getString("CODECLASSEACTUELLE") != null ? rs.getString("CODECLASSEACTUELLE") : "");
			data.put("manager1", rs.getString("manager1"));
			data.put("manager2", rs.getString("manager2"));
			data.put("manager3", rs.getString("manager3"));
		}
		Utils.closeResulSet(null, st);

		// Chargement des informations du compte de l'employe
		parameters = new Object[] {matricule};
		st = con.prepareStatement(REQ_ALTGRH_INFOS_CPT);
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null) while(rs.next()){
			data.put("ncp", "000" + rs.getString("AGENCE_VIRE")+"-"+ rs.getString("numerocompte")+"-"+rs.getString("cle"));
		}
		Utils.closeResulSet(null, st);

		// Recuperation du nombre de jours de conge correspondant a la classe de l'employe
		parameters = new Object[] {data.get("classe")};
		st = con.prepareStatement(REQ_ALTGRH_NBRE_JRS);
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null) while(rs.next()){
			data.put("nbjours", rs.getString("NOMBREJOURS") != null ? rs.getString("NOMBREJOURS").trim() : "");
		}
		Utils.closeResulSet(null, st);

		// Recuperation du nombre de jours de conge accorde
	    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
	    String dateStr = simpleDate.format(new Date());
//	    String codeConge = "C002";
		parameters = new Object[] {matricule, dateStr.substring(6)};
		st = con.prepareStatement(REQ_ALTGRH_VERIF_CONGE);
		rs = runSqlQuery.selectData(st, parameters);
		int nbrej=0;
        if(rs != null){
        	while(rs.next()){
        		nbrej= nbrej + rs.getInt("NBJRS");
            }
        }
        data.put("joursEff", nbrej);
		Utils.closeResulSet(null, st);
		
		// Recuperation du nombre de jours de permission accorde
		parameters = new Object[] {matricule, dateStr.substring(6)};
		st = con.prepareStatement(REQ_ALTGRH_VERIF_PERM);
		rs = runSqlQuery.selectData(st , parameters);
		int nbrejrs=0;
        if(rs != null){
        	while(rs.next()){
        		nbrejrs= nbrejrs + rs.getInt(2);
            }
        }
        data.put("permAccord", nbrejrs);
		Utils.closeResulSet(con, st);
		
		return data;
	}
	
	public List<String> getMatVehiculeEmploye(String matricule) throws ClassNotFoundException, SQLException {
		Object[] parameters = new Object[] {matricule};
		Connection con = runSqlQuery.getConnection(IConnectionFactory.PORTAL_DB);
		PreparedStatement st = con.prepareStatement(REQ_PORTAL_VEHICULE);
		ResultSet rs = runSqlQuery.selectData(st, parameters);
		List<String> data = new ArrayList<>();
		if(rs != null) while(rs.next()){
			data.add(rs.getString("cartegrise"));
		}
		Utils.closeResulSet(con, st);
		
		return data;
	}
		
}
