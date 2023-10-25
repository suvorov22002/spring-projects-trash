package com.afb.dpdl.ged.ws.rest.api.dao.enga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.afb.dpdl.ged.ws.rest.api.dao.IRunSqlQuery;
import com.afb.dpdl.ged.ws.rest.api.dao.Utils;
import com.afb.dpdl.ged.ws.rest.api.factory.IConnectionFactory;


public class ManageQueryEngagementsImpl implements IManageQueryEngagements{
	
	@Inject
	private IRunSqlQuery runSqlQuery;
	
	// Initialisation de la requete de chargement de la liste des decouverts du client
	private final String REQ_CBS_DEC = "select a.cli,sum(a.sde) as montant,nvl(sum(b.maut),0) as autorisation, sum(a.sde) as encours, nvl(sum(case when substr(a.cha,1,2)='34' then a.sde end),0) as impayes,0 as agios " +
			"from bkcom a left join bkautc b on a.age=b.age and a.dev=b.dev and a.ncp=b.ncp and b.eta in ('VA','VF','FO') and b.fin>=today " +
			"where substr(a.cha,1,2) in ('37','34','56','58') " +
			"and (a.cpro between '100' and '200' or a.cpro between '500' and '510') " +
			"and a.cfe='N' and a.ife='N' " +
			"and a.sde<0 " +
			"and a.cli=? " +
			"group by a.cli";
	
	// Initialisation de la requete de chargement de la liste des credits octroyes au client
	private final String REQ_CBS_CREDIT = "select libe, sum(montant) as montant, sum(autorisation) as autorisation, sum(encours) as encours, sum(impayes) as impayes, sum(agios) as agios from ( " + 
			"select a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation,a.encours,a.impayes,nvl(sum(case when f.sen='C' then f.mon else -f.mon end),0) as agios " + 
			"from  " +
			"(  " +
			"	select a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation,a.encours, nvl(sum(case when e.sen='C' then e.mon else -e.mon end),0) as impayes " + 
			"	from  " +
			"(  " +
			"select a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation, nvl(sum(d.amo_cal),0) as encours " + 
			"from  " +
			"(  " +
			"select a.age, a.eve, a.dmep, a.ave, d.libe, " + 
			"a.cli,sum(a.mdb) as montant,nvl(sum(c.maut),0) as autorisation " + 
			"from bkdosprt a  " +
			"	inner join bkcptprt b on a.age=b.age and a.eve=b.eve and a.ave=b.ave and b.nat='004' " + 
			"    left join bkautc c on b.age=c.age and b.dev=c.dev and b.ncp=c.ncp and c.eta in ('VA','VF','FO') and c.fin>=today " + 
			"    left join bktyprt d on a.typ = d.typ " +
			"where  " +
			"(a.ctr in ('1','3') or (a.ctr='5' and a.mimp>0)) " + 
			"and a.eta='VA'  " +
			"and a.cli=? " + 
			"group by 1,2,3,4,5,6  " +
			") a  " +
			"    left join bkechprt d on a.age=d.age and a.eve=d.eve and a.ave=d.ave and d.eta='VA' and d.ctr in ('0','1','2','7') and d.dva>=today " + 
			"group by a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation  " +
			") a  " +
			"    left join bkauxprt e on a.age=e.age and a.eve=e.eve and a.ave=e.ave and e.nat in ('006','007') " + 
			"group by a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation,a.encours  " +
			") a  " +
			"    left join bkauxprt f on a.age=f.age and a.eve=f.eve and a.ave=f.ave and f.nat in ('008','009','016') " + 
			"group by a.age, a.eve, a.dmep, a.ave, a.libe, a.cli, a.montant, a.autorisation,a.encours,a.impayes order by a.dmep desc  " + 
			") group by libe";
	
	// Initialisation de la requete de chargement de la liste des cautions du client
	private final String REQ_CBS_CAU = "select libe, sum(mon) as mon from (select t.libe, c.age, c.eve, c.mon, c.obj, c.ben, c.dco from bkcau c, bktycau t where c.tpc=t.typ and c.eta in ('VA', 'FO', 'VF') and c.dech>today and c.cli=?) group by libe";
	
	// Initialisation de la requete de chargement de la liste des effets du client
	private final String REQ_CBS_EFF = "select 'EFFET' as typ_eff, sum(mnat) as mnat, sum(agiod) as agiod from (select a.age, a.dou, a.dech, a.tint, a.agiod, a.eve, a.nat, a.nom, a.mnat, a.teff, a.sor from bkefr a where a.vie not in ('9', '2') and a.cli=?) group by 1";
	
	// Initialisation de la requete de chargement de la liste des credoc du client
	private final String REQ_CBS_CREDOC = "select 'CREDOC' as typ_cred, sum(mont) as mont from (select age, typ, ndos, cclid, nomb, mont, dou, refint from bkdoscde where " +
			"vie in ('F','M','L','O') and eta in ('VA','FO','VF') " +
			"and exists (select 1 from bkcom where bkdoscde.age=bkcom.age and bkdoscde.dev=bkcom.dev and bkdoscde.ncpe=bkcom.ncp and bkcom.cfe='N' and bkcom.sde<>0) and cclid=?) group by 1";
	
	
	@Override
	public Map<String, Object> getEngagements(String matricule) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<>();
		// Initialisations JSON
		JSONObject informationObj;
		JSONArray engagements = new JSONArray();
		JSONObject mainObj = new JSONObject();
		
		ResultSet rs;
		Double totAuto = 0d, totEnc = 0d, totImp = 0d, totAgios = 0d;
		DecimalFormat df = new DecimalFormat("###0");

		// Execution de la req de Chargement de la liste des AUTO du client
		Object[] parameters = {matricule};
		
		Connection con = runSqlQuery.getConnection(IConnectionFactory.CBS_DB);
		PreparedStatement st = con.prepareStatement(REQ_CBS_DEC);

		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null) while(rs.next()){
			informationObj = new JSONObject();
			informationObj.put("Type", "LIGNE DE DECOUVERT");
			informationObj.put("Auto", df.format(rs.getDouble("autorisation")) );
			informationObj.put("Encours", df.format(rs.getDouble("encours")));
			informationObj.put("Impayes", df.format(rs.getDouble("impayes")));
			informationObj.put("Agios", df.format(rs.getDouble("agios")));
			engagements.put(informationObj);
			
			// Cacul des totaux
			totAuto += rs.getDouble("autorisation");
			totEnc += rs.getDouble("encours");
			totImp += rs.getDouble("impayes");
			totAgios += rs.getDouble("agios");
		}
		Utils.closeResulSet(null, st);

		// Chargement de la liste des CREDITS du client
		st = con.prepareStatement(REQ_CBS_CREDIT);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null) while(rs.next()){
			informationObj = new JSONObject();
			informationObj.put("Type", rs.getString("libe").trim().toUpperCase() ); 
			informationObj.put("Auto", df.format(rs.getDouble("montant")) );
			informationObj.put("Encours", df.format(rs.getDouble("encours")));
			informationObj.put("Impayes", df.format(rs.getDouble("impayes")));
			informationObj.put("Agios", df.format(rs.getDouble("agios")));
			engagements.put(informationObj);
			
			// Cacul des totaux
			totAuto += rs.getDouble("montant");
			totEnc += rs.getDouble("encours");
			totImp += rs.getDouble("impayes");
			totAgios += rs.getDouble("agios");
		}
		Utils.closeResulSet(null, st);

		// Chargement de la liste des EFFETS du client
		
		st = con.prepareStatement(REQ_CBS_EFF);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null) while(rs.next()){
			informationObj = new JSONObject();
			informationObj.put("Type", "EFFET");
			informationObj.put("Auto", "0" );
			informationObj.put("Encours", df.format(rs.getDouble("mnat")));
			informationObj.put("Impayes", df.format(rs.getString("sor").equals("I") ? rs.getDouble("mnat") : 0d));
			informationObj.put("Agios", df.format(rs.getDouble("agiod") ) );
			engagements.put(informationObj);
			
			// Cacul des totaux
			totAuto += 0;
			totEnc += rs.getDouble("mnat");
			totImp += rs.getString("sor").equals("I") ? rs.getDouble("mnat") : 0d;
			totAgios += rs.getDouble("agiod");
		}
		Utils.closeResulSet(null, st);

		// Chargement de la liste des CAUTIONS du client
		
		st = con.prepareStatement(REQ_CBS_CAU);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st , parameters);
		if(rs != null) while(rs.next()){
			informationObj = new JSONObject();
			informationObj.put("Type", rs.getString("libe").trim().toUpperCase() );
			informationObj.put("Auto", "0" );
			informationObj.put("Encours", df.format(rs.getDouble("mon")));
			informationObj.put("Impayes", "0");
			informationObj.put("Agios", "0");
			engagements.put(informationObj);
			
			totEnc += rs.getDouble("mon");
		}
		Utils.closeResulSet(null, st);
		
		// Chargement de la liste des CREDOC du client
		st = con.prepareStatement(REQ_CBS_CREDOC);
		con.createStatement().executeUpdate("SET ISOLATION TO DIRTY READ");
		rs = runSqlQuery.selectData(st, parameters);
		if(rs != null) while(rs.next()){
			informationObj = new JSONObject();
			informationObj.put("Type", "CREDIT DOCUMENTAIRE" );
			informationObj.put("Auto", "0" );
			informationObj.put("Encours", df.format(rs.getDouble("mont")));
			informationObj.put("Impayes", "0");
			informationObj.put("Agios", "0");
			engagements.put(informationObj);
			
			totEnc += rs.getDouble("mont");
		}
		Utils.closeResulSet(con, st);

		// Ajout des engagements dans la Map
		mainObj.put("engagements", engagements);
		data.put("informations",mainObj);
		data.put("totAuto",df.format(totAuto) );
		data.put("totEnc",df.format(totEnc));
		data.put("totImp",df.format(totImp));
		data.put("totAgios",df.format(totAgios));
		return data;
	}
		
}
