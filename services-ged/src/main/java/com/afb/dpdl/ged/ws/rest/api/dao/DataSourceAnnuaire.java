package com.afb.dpdl.ged.ws.rest.api.dao;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Gère le pull de connexion vers la BD de l'Annuaire
 * @author narcisse_ekoumlong
 *
 */
public class DataSourceAnnuaire  implements IDataSource{
	private static DataSourceAnnuaire ds;
	private BasicDataSource basicDS = new BasicDataSource();

	 //private constructor
	 private DataSourceAnnuaire(String driver, String cnxString, String user, String pwd){
		  //BasicDataSource basicDS = new BasicDataSource();
		  basicDS.setDriverClassName(driver);
		  basicDS.setUrl(cnxString);
		  basicDS.setUsername(user);
		  basicDS.setPassword(pwd);
		  
		  // Parameters for connection pooling
		  basicDS.setInitialSize(1);
		  basicDS.setMaxTotal(2); 
	 }
	 
	 /**
	   *static method for getting instance.
	 */
	 public static DataSourceAnnuaire getInstance(String driver, String cnxString, String user, String pwd){
	    if(ds == null){
	        ds = new DataSourceAnnuaire(driver, cnxString, user, pwd); 
	    }
	    return ds;
	 }

	 public BasicDataSource getBasicDS() {
		 return basicDS;
	 }

	 public void setBasicDS(BasicDataSource basicDS) {
		 this.basicDS = basicDS;
	 }
	 
	 public void resetDs () {
		 ds = null;
	 }
	 
	 public static void setDs () {
		 ds = null;
	 }
}
