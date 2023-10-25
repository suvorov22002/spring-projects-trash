package com.afb.dpdl.ged.ws.rest.api.dao;

import org.apache.commons.dbcp2.BasicDataSource;
/**
 * Gère le pull de connexion vers la BD de la GED
 * @author narcisse_ekoumlong
 *
 */
public class DataSourceGed  implements IDataSource{
	private static DataSourceGed ds;
	private BasicDataSource basicDS = new BasicDataSource();

	 //private constructor
	 private DataSourceGed(String driver, String cnxString, String user, String pwd){
		  //BasicDataSource basicDS = new BasicDataSource();
		  basicDS.setDriverClassName(driver);
		  basicDS.setUrl(cnxString);
		  basicDS.setUsername(user);
		  basicDS.setPassword(pwd);
		  
		  // Parameters for connection pooling
		  basicDS.setInitialSize(1);
		  basicDS.setMaxTotal(5); 
	 }
	 
	 /**
	   *static method for getting instance.
	 */
	 public static DataSourceGed getInstance(String driver, String cnxString, String user, String pwd){
	    if(ds == null){
	        ds = new DataSourceGed(driver, cnxString, user, pwd); 
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
