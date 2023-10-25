package com.afb.dpdl.ged.ws.rest.api.factory;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionFactory {
	
	public static final String PORTAL_DB = "PORTAL";
	public static final String GED_DB = "GED";
	public static final String CBS_DB = "CBS";
	public static final String ANNUAIRE_DB = "ANNUAIRE";
	public static final String ALTGRH_DB = "ALTGRH";
	public static final String ACHAT_DB = "ACHAT";
	public static final String SMVISTA_DB = "SMVISTA";
	public static final String FT_DB = "FT";
	public static final String ATFF_DB = "ATFF";
	public static final String GACHA_DB = "GACHA";
	
	/**
	 * 
	 * @param driver
	 * @param connectionString
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection(String driver, String connectionString, String userName, String pwd)  throws ClassNotFoundException, SQLException ;
}
