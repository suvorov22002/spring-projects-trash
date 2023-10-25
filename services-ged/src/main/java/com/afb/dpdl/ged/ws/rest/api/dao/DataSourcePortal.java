package com.afb.dpdl.ged.ws.rest.api.dao;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Gère le pull de connexion vers la BD de Portal
 * @author narcisse_ekoumlong
 *
 */
public class DataSourcePortal  implements IDataSource{
	private static DataSourcePortal ds;
	private BasicDataSource basicDS = new BasicDataSource();

	 //private constructor
	 private DataSourcePortal(String driver, String cnxString, String user, String pwd){
		  //BasicDataSource basicDS = new BasicDataSource();
		  basicDS.setDriverClassName(driver);
		  basicDS.setUrl(cnxString);
		  basicDS.setUsername(user);
		  basicDS.setPassword(pwd);
		  
		  // Parameters for connection pooling
		  basicDS.setInitialSize(15);
		  basicDS.setMaxTotal(30); 
	 }
	 
	 /**
	   *static method for getting instance.
	 */
	 public static DataSourcePortal getInstance(String driver, String cnxString, String user, String pwd){
	    if(ds == null){
	        ds = new DataSourcePortal(driver, cnxString, user, pwd); 
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
