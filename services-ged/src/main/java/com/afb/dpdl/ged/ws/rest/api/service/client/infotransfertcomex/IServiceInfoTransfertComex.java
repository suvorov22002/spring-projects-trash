package com.afb.dpdl.ged.ws.rest.api.service.client.infotransfertcomex;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IServiceInfoTransfertComex {
	
	
	/**
	 * Recuperation des informations d'un transfert effectue a l'international
	 * @param reftrans Reference du transfert
	 * @return Objet JSON contenant les informations du transfert effectue
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	JSONObject getInfoTransfertComexJSON(String reftrans) throws ClassNotFoundException, SQLException;

	
	/**
	 * Recup�ration de la liste des natures des op�rations pour les op�rations de tranfert du COMEX
	 * @param nataureOpe la nature de l'op�ration
	 * @return tableau d'objet JSon (JSONArray). Chaque objet contient les infos label et value
	 */
	JSONArray getListeDocTrftComexByNatureOpeJSON(String nataureOpe);

	
	/**
	 * Recup�ration de la liste des documents exig�es pour une nature d'op�rations pour les op�rations de tranfert du COMEX
	 * @return tableau d'objet JSon (JSONArray). Chaque objet contient les infos label et value
	 */
	JSONArray getNatureOpeTrftComexJSON();


}
